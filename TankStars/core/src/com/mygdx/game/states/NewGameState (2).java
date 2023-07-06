package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TankStars;

import java.util.ArrayList;

public class NewGameState extends State implements InputProcessor {
    private Texture background ;
    int tanksAvailable=3;
    private int isPlayer;
    float et=0;
    float ET=0;
    static int beg;
    public static TextureRegion currentframe,rewframe;
    int  w,h,x,y;
    int W,H,X,Y;
    int currentTankIdx,lastTankIdx;

    //-------------------------- InputProcessor Methods------------------------------
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean isY = screenY <= (TankStars.HEIGHT - 400) && screenY>=(TankStars.HEIGHT - 560);
        boolean isXLeft = screenX >= 100 && screenX<=275;
        boolean isXRight = screenX >= 1625 && screenX<=1790;
        if(isY && isXLeft){
            beg=0;
           if(currentTankIdx == 0) {
            lastTankIdx=currentTankIdx;
            currentTankIdx = tanksAvailable-1;}
           else {
            lastTankIdx=currentTankIdx;
            currentTankIdx--;}
           et=0;
           ET=0;
        }
        if(isY && isXRight){
            beg=0;
           if(currentTankIdx == tanksAvailable -1){ 
            lastTankIdx=currentTankIdx;
            currentTankIdx = 0;
        }
           else{ 
            lastTankIdx=currentTankIdx;
            currentTankIdx++;}
           et=0;
           ET=0;
       
        }
        boolean isTankX = screenX >= 390 && screenX<=1410;;
        boolean isTankY = screenY <= (TankStars.HEIGHT - 100) && screenY>=(TankStars.HEIGHT - 780);
        if(isTankX && isTankY){
            beg=0;
            if(isPlayer == 1){
                TankStars.p1_choice=currentTankIdx;
                beg=1;
                isPlayer = 2;
                et=0;
                ET=0;
                currentTankIdx = 0;
                background = new Texture("screens/ChooseTank/p2.png");
                System.out.println(TankStars.p1_choice);

            }
            else{
                TankStars.p2_choice=currentTankIdx;
                System.out.println(TankStars.p2_choice);

                //gsm.set(new fight(gsm));
                TankStars.menu_music.stop();
                gsm.push(new fight(gsm));

            }
        }
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

    //-------------------------------------------------------------------------------------------------------

    public NewGameState(GameStateManager gsm) {
        super(gsm);
        beg=1;
        Gdx.input.setInputProcessor(this);
        currentTankIdx = 0;
        isPlayer = 1;
        background = new Texture("screens/ChooseTank/p1.png");
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.begin();
        sb.draw(background , 0 , 0 , TankStars.WIDTH,  TankStars.HEIGHT);

        if (currentTankIdx == 0) {
            w = 2160;
            h = 1600;
            x = -100;
            y = -300;
        } else if (currentTankIdx == 1) {
            w = 2000;
            h = 1500;
            x = -100;
            y = -200;

        } else {
            w = 1700;
            h = 1400;
            x = 100;
            y = -100;
        }

        if (lastTankIdx == 0) {
            W = 2160;
            H = 1600;
            X = -100;
            Y = -300;

        } else if (lastTankIdx == 1) {
            W = 2000;
            H = 1500;
            X = -100;
            Y = -200;

        } else {
            W = 1700;
            H = 1400;
            X = 100;
            Y = -100;
        }
        if(beg!=1){
            ET += Gdx.graphics.getDeltaTime();
            TankStars.tank_Animations.get(lastTankIdx).setPlayMode(PlayMode.REVERSED);
            sb.draw(TankStars.tank_Animations.get(lastTankIdx).getKeyFrame(ET), X, Y,W,H);
        }
        
        TankStars.tank_Animations.get(currentTankIdx).setPlayMode(PlayMode.NORMAL);
        if(TankStars.tank_Animations.get(lastTankIdx).isAnimationFinished(ET)|| beg==1){
            et += Gdx.graphics.getDeltaTime();
            sb.draw(TankStars.tank_Animations.get(currentTankIdx).getKeyFrame(et),x, y,w,h);
        }
        sb.end();
    }

    @Override
    public void render(SpriteBatch sb) {

    }


    @Override
    public void dispose() {
        background.dispose();
    
    }
}