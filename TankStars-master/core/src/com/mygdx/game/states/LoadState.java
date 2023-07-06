package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TankStars;
import com.mygdx.game.resources.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadState extends State implements InputProcessor {
    private Texture background ;

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
        boolean isY = screenY <= (TankStars.HEIGHT-(70)) && screenY>=(TankStars.HEIGHT-(750+80));
        boolean isXSlot1 = screenX >= 150 && screenX<=510;
        boolean isXSlot2 = screenX >= 800 && screenX<=1000;
        boolean isXSlot3 = screenX >= 1400 && screenX<=1720;
//        if(isXSlot1 && isY){
//            try {
//                deserialize("Slot1Player1.txt" , "Slot1Player2.txt");
//
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            finally {
//                TankStars.load = 1;
//                gsm.set(new fight(gsm));
//
//            }
//        }
//        if(isXSlot2 && isY){
//            try {
//                deserialize("Slot2Player1.txt" , "Slot2Player2.txt");
//            } catch (ClassNotFoundException | IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//                TankStars.load = 1;
//                gsm.set(new fight(gsm));
//
//            }
//        }
//        if(isXSlot3 && isY){
//            try {
//                deserialize("Slot3Player1.txt" , "Slot3Player2.txt");
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            finally {
//                TankStars.load = 1;
//                gsm.set(new fight(gsm));
//
//            }
//        }

        if(isXSlot1 && isY){
            try {
                deserialize("Slot1Player1.txt" );

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
            }

            try {
                deserialize2("Slot1Player2.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                TankStars.load = 1;
                gsm.set(new fight(gsm));
            }


        }
        if(isXSlot2 && isY){
            try {
                deserialize("Slot2Player1.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
            }

            try {
                deserialize2("Slot2Player2.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                TankStars.load = 1;
                gsm.set(new fight(gsm));

            }
        }
        if(isXSlot3 && isY){
            try {
                deserialize("Slot3Player1.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
            }

            try {
                deserialize2("Slot3Player2.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                TankStars.load = 1;
                gsm.set(new fight(gsm));
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

    private Texture playBtn;

    public LoadState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("screens/Save.png");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput() {
//        if(Gdx.input.isTouched()){
//            gsm.set(new StartingState2(gsm));
//            dispose();
//        }
    }


    public static void deserialize(String out1) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream(out1));
            TankStars.player1 = (Player) in.readObject();
        } finally {
            in.close();
        }
    }


    public static void deserialize2(String out1) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream(out1));
            TankStars.player2 = (Player) in.readObject();
        } finally {
            in.close();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.begin();
        sb.draw(background , 0 , 0 , TankStars.WIDTH,  TankStars.HEIGHT);
//        sb.draw(playBtn , (TankStars.WIDTH/2) - (playBtn.getWidth() / 2) , TankStars.HEIGHT/2);
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