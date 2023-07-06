package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.TankStars;
import com.mygdx.game.resources.Tank;

import org.graalvm.compiler.nodes.PauseNode;

public class fight extends State implements InputProcessor {
    private Bezier<Vector2> path1,path2;
    private Vector2 out,out2;
    private Vector2 agle=new Vector2();
    private Vector2 agle2=new Vector2();
    int fire=1;
    int turn=1;
    float power1,power2;
    float hforce1=1,hforce2=1;
    float vforce1=1,vforce2=1;
    private Texture tank1,tank2,pause,bg,bomb;
    float speed = 0.15f;
    float current = 0;
    float current2=1;
    float angle,angle2;
    private World world;
    private Body bullet,platform,missle;
    private Box2DDebugRenderer b2dr;
    int state=0;
    public fight(GameStateManager gsm) {
        super(gsm);
        out=new Vector2();
        out2=new Vector2();
        cam.setToOrtho(false,TankStars.WIDTH,TankStars.HEIGHT);
        Gdx.input.setInputProcessor(this);
        bomb=new Texture(("screens/bomb.png"));



        // ------------------------------ tank object creating ----------------------------//
        Tank tk1 = new Tank(100 , new Vector3(0,0,0) , null);
        Tank tk2 = new Tank(100 , new Vector3(0,0,0) , null);
        TankStars.player1.setTank(tk1);
        TankStars.player2.setTank(tk1);


        // -----------------------------------  Texture initialising -------------------------------//
        if(TankStars.p1_choice==0)TankStars.player1.setTexture(new Texture("screens/tank1_paper.png"));
        else if(TankStars.p1_choice==1)TankStars.player1.setTexture(new Texture("screens/tank2_paper.png"));
        else if(TankStars.p1_choice==2)TankStars.player1.setTexture(new Texture("screens/tank3_paper.png"));
        tank1 = TankStars.player1.getTank().getImage();

        if(TankStars.p2_choice==0)TankStars.player2.setTexture(new Texture("screens/tank1_paper.png"));
        else if(TankStars.p2_choice==1)TankStars.player2.setTexture(new Texture("screens/tank2_paper.png"));
        else if(TankStars.p2_choice==2)TankStars.player2.setTexture(new Texture("screens/tank3_paper.png"));
        tank2 = TankStars.player2.getTank().getImage();


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
    }
    public void inputUpdate(float dt)  {
        if(state==0)
        { //float power=0.1f;
            if (turn == 1) {
                hforce2 = 1;
                vforce2 = 1;
                power2 = 9.8f;
            }
            if (turn == 2) {
                hforce1 = 1;
                vforce1 = 1;
                power1 = 9.8f;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && turn == 1) {
                //vforce1+=power;
                power1 += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && turn == 1) {
                //vforce1-=power;
                power1 -= 1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP) && turn == 2) {
                //vforce2+=power;
                power2 += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && turn == 2) {
                //vforce2-=power;
                power2 += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && fire == 0 && turn == 1) {
                fire = 1;
                float angle = calc_angle(out.x, out.y, Gdx.input.getX(), Gdx.input.getY());
                System.out.println(angle + " " + (9.8 * vforce1) * MathUtils.cosDeg(angle));
                missle = create_body((int) out.x - TankStars.WIDTH / 2, (int) out.y - TankStars.HEIGHT / 2, 32, 32, false);
                missle.setLinearVelocity((float) power1 * MathUtils.cosDeg(angle), (float) power1 * MathUtils.sinDeg(angle));
                turn = 2;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && fire == 0 && turn == 2) {
                fire = 1;
                float angle = calc_angle(out2.x, out2.y, Gdx.input.getX(), Gdx.input.getY());
                missle = create_body((int) out2.x - TankStars.WIDTH / 2, (int) out2.y - TankStars.HEIGHT / 2, 32, 32, false);
                missle.setLinearVelocity((float) power2 * MathUtils.cosDeg(angle), power2 * MathUtils.sinDeg(angle));
                turn = 1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                fire = 0;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                state = 1;
            }
        }
        else if(state==1){
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
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

            sb.begin();
            sb.draw(bg, 0, 47.5f, TankStars.WIDTH, TankStars.HEIGHT);
            if (TankStars.p1_choice == 0)
                sb.draw(tank1, out.x - 25, out.y - 25, 25, 25, 172, 100, 1, 1, angle, 0, 0, tank1.getWidth(), tank1.getHeight(), true, false);
            else
                sb.draw(tank1, out.x - 25, out.y - 25, 25, 25, 172, 100, 1, 1, angle, 0, 0, tank1.getWidth(), tank1.getHeight(), true, false);

            if (TankStars.p2_choice == 1 || TankStars.p2_choice == 2)
                sb.draw(tank2, out2.x - 25, out2.y - 25, 25, 25, 172, 100, 1, 1, angle2, 0, 0, tank2.getWidth(), tank2.getHeight(), false, false);
            else
                sb.draw(tank2, out2.x - 25, out2.y - 25, 25, 25, 172, 100, 1, 1, angle2, 0, 0, tank2.getWidth(), tank2.getHeight(), true, false);
            if (fire == 1) {
                sb.draw(bomb, 100, 500, 50, 50);
            }
            sb.end();

            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.RED);
            if (turn == 1)
                sr.line(out, new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            else if (turn == 2)
                sr.line(out, new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            sr.end();
            b2dr.render(world, cam.combined.scl(32f));
            break;
        case 1:
            sb.begin();
            sb.draw(pause, 0, 0, TankStars.WIDTH, TankStars.HEIGHT);
            sb.end();
            break;

    }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    public void dispose () {
        tank1.dispose();
        tank2.dispose();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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