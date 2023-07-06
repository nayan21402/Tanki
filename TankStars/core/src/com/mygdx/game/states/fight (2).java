package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.mygdx.game.TankStars;
import com.mygdx.game.resources.Drop;
import com.mygdx.game.resources.Player;
import com.mygdx.game.resources.Tank;
import com.mygdx.game.resources.missle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class fight extends State implements InputProcessor {
    private Bezier<Vector2> path1,path2;
    private Vector2 out,out2;
    private Vector2 agle=new Vector2();
    private Vector2 agle2=new Vector2();
    int fire=0;
    int turn=1;
    float power1,power2;
    private Texture tank1,tank2,pause,bg,bomb,save,p1win,p2win;
    float speed = 0.15f;
    float current = 0.1f;
    float current2=0.9f;
    float angle,angle2;
    private World world;
    private Body platform;
    private Box2DDebugRenderer b2dr;
    int state=0;
    Music fight_music;
    ArrayList<missle> bullets;
    ArrayList<Drop> drops;
    ArrayList<missle> bullets_remove;
    ArrayList<Drop> drops_remove;
    int number_of_turns=0;
    int drop_added=0;
    BitmapFont font;

    public fight(GameStateManager gsm) {
            super(gsm);
            font = new BitmapFont();

            fight_music=Gdx.audio.newMusic(Gdx.files.internal("screens/fight_music.OGG"));
            fight_music.setLooping(true);
            fight_music.play();
            p1win=new Texture("screens/p1win.png");
            p2win=new Texture("screens/p2win.png");

        drops = new ArrayList<Drop>();
            bullets=new ArrayList<missle>();
            bullets_remove=new ArrayList<missle>();
            drops_remove=new ArrayList<Drop>();
            out=new Vector2();
            out2=new Vector2();

            cam.setToOrtho(false,TankStars.WIDTH,TankStars.HEIGHT);
            Gdx.input.setInputProcessor(this);
            save = new Texture("screens/Save.png");

            if(TankStars.load == 0) {
                // ------------------------------ tank object creating ----------------------------//

                Tank tk1 = new Tank(100, new Vector3(0, 0, 0), null , 0);
                Tank tk2 = new Tank(100, new Vector3(0, 0, 0), null , 0);
                TankStars.player1.setTank(tk1);
                TankStars.player2.setTank(tk2);

                // -----------------------------------  Texture initialising -------------------------------//
                if (TankStars.p1_choice == 0)
                    TankStars.player1.setTexture(new Texture("screens/tank1_paper.png"));
                else if (TankStars.p1_choice == 1)
                    TankStars.player1.setTexture(new Texture("screens/tank2_paper.png"));
                else if (TankStars.p1_choice == 2)
                    TankStars.player1.setTexture(new Texture("screens/tank3_paper.png"));


                if (TankStars.p2_choice == 0)
                    TankStars.player2.setTexture(new Texture("screens/tank1_paper.png"));
                else if (TankStars.p2_choice == 1)
                    TankStars.player2.setTexture(new Texture("screens/tank2_paper.png"));
                else if (TankStars.p2_choice == 2)
                    TankStars.player2.setTexture(new Texture("screens/tank3_paper.png"));
            }

            tank1 = TankStars.player1.getTank().getImage();
            tank2 = TankStars.player2.getTank().getImage();

            if(TankStars.load == 1){
                current = TankStars.player1.getTank().getCurrent();
                current2 = TankStars.player2.getTank().getCurrent();
            }
            if(TankStars.load == 1){
                out.x = TankStars.player1.getTank().getLocation().x;
                out.y = TankStars.player1.getTank().getLocation().y;
                out2.x = TankStars.player2.getTank().getLocation().x;
                out2.y = TankStars.player2.getTank().getLocation().y;
            }

            pause=new Texture("screens/pause_state.png");
            bg=new Texture("screens/fight.png");
            path1 = new Bezier<Vector2>(new Vector2(0,TankStars.HEIGHT/2 - 300),new Vector2(548,TankStars.HEIGHT-300),new Vector2(1096,TankStars.HEIGHT/30 -300),new Vector2(TankStars.WIDTH,TankStars.HEIGHT/2 -300));
            //path2=new Bezier<Vector2>(new Vector2(822,TankStars.HEIGHT/3),new Vector2(1096,TankStars.HEIGHT/2 ),new Vector2(1370,TankStars.HEIGHT/5),new Vector2(TankStars.WIDTH,TankStars.HEIGHT/3));
            world=new World(new Vector2(0,-9.8f),false);
            //bullet=create_body(0,10,32,32,false);
            platform=create_body(0,0,0,0,true);
            b2dr=new Box2DDebugRenderer();

        }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(1/60f,6,2);
        //System.out.println(angle);
        inputUpdate(dt);
        cameraupdate(dt);
        TankStars.player1.setCurrent(current);
        TankStars.player2.setCurrent(current2);
    }
    public void inputUpdate(float dt)  {
        if(state==0)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                fire = 0;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                state = 1;
            }
        }
        else if(state==1){
            if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))
                state=0;
        }
    }
    public float calc_angle(float x1,float y1,float x2,float y2){
        float x=x2-x1;
        float y=y2-y1;
        float angle= MathUtils.radiansToDegrees*MathUtils.atan2(y,x);
        return angle;
    }
    public void create () {



    }
    public void cameraupdate(float delta){
        Vector3 pos= cam.position;
        pos.x=platform.getPosition().x * 32f;
        pos.y=platform.getPosition().y * 32f;
        cam.position.set(pos);
        cam.update();
    }
    public Body create_body(int x,int y,int width,int height, boolean isStatic){
        Body pBody;
        BodyDef def = new BodyDef();
        if(!isStatic)
            def.type=BodyDef.BodyType.DynamicBody;
        else
            def.type=BodyDef.BodyType.StaticBody;

        def.position.set(x/32f,y/32f);
        def.fixedRotation=true;
        pBody=world.createBody(def);

        PolygonShape shape=new PolygonShape();
        shape.setAsBox(width/2 /32f,height/2 /32f);

        pBody.createFixture(shape,1f); //density
        shape.dispose();
        return pBody;
    }

    public void render(SpriteBatch sb,ShapeRenderer sr) {
        switch (state) {

            case 0:

                Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                if (turn == 1) {
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                        current -= speed * Gdx.graphics.getDeltaTime();
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                        current += speed * Gdx.graphics.getDeltaTime();
                }

                if (turn == 2) {
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                        current2 -= speed * Gdx.graphics.getDeltaTime();
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                        current2 += speed * Gdx.graphics.getDeltaTime();
                }
                if (current >= 1 || current < 0) {
                    if (current > 1)
                        current = 1;
                    if (current < 0)
                        current = 0;
                }
                if (current2 >= 1 || current2 < 0) {
                    if (current2 > 1)
                        current2 = 1;
                    if (current2 < 0)
                        current2 = 0;
                }
                path1.valueAt(out, current);
                path1.valueAt(out2, current2);
                path1.derivativeAt(agle, current);
                path1.derivativeAt(agle2, current2);

                angle = agle.angleDeg();
                angle2 = agle2.angleDeg();
                Vector2 drop_pos=new Vector2();
                path1.valueAt(drop_pos,(current+current2)/2);
                if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) && turn == 1) {
                    bullets.add(new missle(out.x, out.y,1));
                    turn = 2;
                    number_of_turns+=1;
                    drop_added=0;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.NUM_2) && turn == 2) {
                    //float angle = calc_angle(out2.x, out2.y, Gdx.input.getX(), Gdx.input.getY());
                    bullets.add(new missle(out2.x, out2.y,-1));
                    turn = 1;
                    number_of_turns+=1;
                    drop_added=0;
                }
                for(missle m: bullets){
                    m.update(Gdx.graphics.getDeltaTime());
                    if(m.remove)
                        bullets_remove.add(m);
                }
                bullets.removeAll(bullets_remove);
                if(number_of_turns%5==0 && number_of_turns!=0 && drop_added==0){
                    drops.add(new Drop(drop_pos.x,TankStars.HEIGHT,drop_pos));
                    drop_added=1;
                }
                for(Drop d:drops){
                    d.update(Gdx.graphics.getDeltaTime());
                }
                sb.begin();
                    sb.draw(bg, 0, 47.5f, TankStars.WIDTH, TankStars.HEIGHT);
                    sb.draw(tank1, out.x - 25, out.y - 25, 25, 25, 172, 100, 1, 1, angle, 0, 0, tank1.getWidth(), tank1.getHeight(), false, false);
                    sb.draw(tank2, out2.x - 25, out2.y - 25, 25, 25, 172, 100, 1, 1, angle2, 0, 0, tank2.getWidth(), tank2.getHeight(), true, false);
                    for(missle m: bullets){
                        m.render(sb);
                        //TankStars.player2.setHealth(TankStars.player2.getHealth()-50);
                        System.out.println(m.x+" "+m.y);
                        if(out2.x-86 <m.x && m.x<out2.x+86 && out2.y<=m.y &&m.y<out2.y+100 && m.direction==1){
                            bullets_remove.add(m);
                            TankStars.player2.setHealth(TankStars.player2.getHealth()-50);

                        }
                        if(out.x-86 <=m.x && m.x<=out.x+86 && out.y-50<=m.y &&m.y<out.y+50 && m.direction==-1){
                            bullets_remove.add(m);
                            TankStars.player1.setHealth(TankStars.player1.getHealth()-50);
                        }
                    }
                    for(Drop d:drops){
                        d.render(sb);
                        if(out.x-25<d.final_pos.x && d.final_pos.x<out.x+25 && out.y-25<d.final_pos.y && d.final_pos.y<out.y+25){
                            if(TankStars.player1.getHealth()<360)
                            TankStars.player1.setHealth(TankStars.player1.getHealth()+100);
                            drops_remove.add(d);
                        }
                        if(out2.x-25<d.final_pos.x && d.final_pos.x<out2.x+25 && out2.y-25<d.final_pos.y && d.final_pos.y<out2.y+25){
                            if(TankStars.player2.getHealth()<360)
                                TankStars.player2.setHealth(TankStars.player2.getHealth()+100);
                            drops_remove.add(d);
                        }
                    }
                    drops.removeAll((drops_remove));
                if(TankStars.player2.getHealth()==0 ||  TankStars.player1.getHealth()==0 ){
                    state=3;
                }

                    sb.end();

                    // healthbar
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(Color.WHITE);
                sr.rect(TankStars.WIDTH/2-810,TankStars.HEIGHT/2+390,320,70);
                sr.rect(TankStars.WIDTH/2+490,TankStars.HEIGHT/2+390,320,70);


                sr.setColor(Color.BLACK);
                sr.rect(TankStars.WIDTH/2-802.5f,TankStars.HEIGHT/2+397f,306,56);
                sr.rect(TankStars.WIDTH/2+497.5f,TankStars.HEIGHT/2+397,306,56);


                sr.setColor(Color.RED);
                sr.rect((float) (TankStars.WIDTH/2-800), (float) (TankStars.HEIGHT/2+400), (float) TankStars.player1.getHealth(),50);
                sr.rect(TankStars.WIDTH/2+500,TankStars.HEIGHT/2+400,(float) TankStars.player2.getHealth(),50);

                sr.end();
                
                sb.begin(); // Assuming you have a SpriteBatch called 'batch'
                font.draw(sb, "Player 1", TankStars.WIDTH/2-800, TankStars.HEIGHT/2+400 - 20); // Render text for Player 1
                font.draw(sb, "Player 2", TankStars.WIDTH/2+500, TankStars.HEIGHT/2+400 - 20); // Render text for Player 2
                sb.end();

                b2dr.render(world, cam.combined.scl(32f));
                break;
            case 1:
                sb.begin();
                sb.draw(pause, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
                sb.end();
                break;

            case 2:
                sb.begin();
                sb.draw(save, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
                sb.end();
                break;

            case 3:
                sb.begin();
                sb.draw(bg, 0, 47.5f, TankStars.WIDTH, TankStars.HEIGHT);
                sb.draw(tank1, out.x - 25, out.y - 25, 25, 25, 172, 100, 1, 1, angle, 0, 0, tank1.getWidth(), tank1.getHeight(), false, false);
                sb.draw(tank2, out2.x - 25, out2.y - 25, 25, 25, 172, 100, 1, 1, angle2, 0, 0, tank2.getWidth(), tank2.getHeight(), true, false);
                if(TankStars.player1.getHealth()==0){
                    sb.draw(p2win,TankStars.WIDTH/2 - p2win.getWidth()/2,TankStars.HEIGHT-400);
                }
                if(TankStars.player2.getHealth()==0){
                    sb.draw(p1win,TankStars.WIDTH/2 - p1win.getWidth()/2,TankStars.HEIGHT-400);
                }
                if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                    fight_music.dispose();
                    TankStars.menu_music.play();
                    gsm.push(new StartingState1(gsm));
                    state=0;
                    TankStars.player1.setHealth(300);
                    TankStars.player2.setHealth(300);
                }
                sb.end();

        }
    }
    public static void serialize(String outPlayer1 , Player p) throws IOException {

        ObjectOutputStream outp = null;
        ObjectOutputStream outp2 = null;
        try {
            outp = new ObjectOutputStream (new FileOutputStream(outPlayer1));
            outp.writeObject(p);
        }
        finally {
            outp.close();
        }
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(state == 1){
            boolean isX = screenX >= 750 && screenX<=1120;
            boolean isYResume = screenY <= (TankStars.HEIGHT-(800)) && screenY>=(TankStars.HEIGHT-(880));
            boolean isYRestart = screenY <= (TankStars.HEIGHT-(530)) && screenY>=(TankStars.HEIGHT-(685));
            boolean isYSave = screenY <= (TankStars.HEIGHT-(150 + 245)) && screenY>=(TankStars.HEIGHT-(150 + 310));
            boolean isYExit = screenY <= (TankStars.HEIGHT-(90)) && screenY>=(TankStars.HEIGHT-(300));
            if(isX && isYResume){
                state = 0;
            }
            else if(isX && isYRestart){
                TankStars.load = 0;
                fight_music.dispose();
                //TankStars.menu_music.play();

                TankStars.player1.setHealth(300);
                TankStars.player2.setHealth(300);
                gsm.set(new fight(gsm));
            }
            else if(isX && isYSave){
                state = 2;
            }
            else if(isX && isYExit){
                fight_music.dispose();
                TankStars.menu_music.play();

                gsm.set(new StartingState1(gsm));
            }
        }
        else if(state == 2){
            boolean isY = screenY <= (TankStars.HEIGHT-(70)) && screenY>=(TankStars.HEIGHT-(750+80));
            boolean isXSlot1 = screenX >= 150 && screenX<=510;
            boolean isXSlot2 = screenX >= 800 && screenX<=1000;
            boolean isXSlot3 = screenX >= 1400 && screenX<=1720;
            if(isXSlot1 && isY){
                try {
                    serialize("Slot1Player1.txt" , TankStars.player1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {

                }

                try {
                    serialize("Slot1Player2.txt", TankStars.player2);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    state = 1;
                }


            }
            if(isXSlot2 && isY){
                try {
                    serialize("Slot2Player1.txt" , TankStars.player1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {

                }

                try {
                    serialize("Slot2Player2.txt", TankStars.player2);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    state = 1;
                }
            }
            if(isXSlot3 && isY){
                try {
                    serialize("Slot3Player1.txt" , TankStars.player1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {

                }

                try {
                    serialize("Slot3Player2.txt", TankStars.player2);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    state = 1;
                }
            }
        }
        return false;
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    public void dispose () {
        tank1.dispose();
        tank2.dispose();
        bg.dispose();
        world.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}