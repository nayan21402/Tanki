package com.mygdx.game.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TankStars;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveState extends State implements InputProcessor {
    @Override
    public int hashCode() {
        return super.hashCode();
    }

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



    public static void serialize(String outPlayer1 , String outPlayer2) throws IOException {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream (new FileOutputStream(outPlayer1));
            out.writeObject(TankStars.player1);
            out = new ObjectOutputStream (new FileOutputStream(outPlayer2));
            out.writeObject(TankStars.player2);
        }
        finally {
            out.close();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)  {
        boolean isY = screenY <= (TankStars.HEIGHT - 390) && screenY>=(TankStars.HEIGHT - 550);
        boolean isXSlot1 = screenX >= 150 && screenX<=510;
        boolean isXSlot2 = screenX >= 800 && screenX<=1000;
        boolean isXSlot3 = screenX >= 1400 && screenX<=1720;
        if(isXSlot1 && isY){
            try {
                serialize("Slot1Player1" , "Slot1Player2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(isXSlot2 && isY){
            try {
                serialize("Slot2Player1" , "Slot2Player2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(isXSlot3 && isY){
            try {
                serialize("Slot3Player1" , "Slot3Player2");
            } catch (IOException e) {
                e.printStackTrace();
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

    private Texture background ;
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }

    public SaveState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("screens/Save.png");
    }
}