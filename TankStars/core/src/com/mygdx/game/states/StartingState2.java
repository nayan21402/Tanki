package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.TankStars;

public class StartingState2 extends State implements InputProcessor{
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
        boolean isYNew = screenY <= (TankStars.HEIGHT-150) && screenY>=(TankStars.HEIGHT-310);
        boolean isYLoad = screenY <= (TankStars.HEIGHT-370) && screenY>=(TankStars.HEIGHT-520);
        boolean isX = screenX >= 640 && screenX<=1190;
        if(isYNew && isX){
            gsm.set(new NewGameState(gsm));
            dispose();
        }
        else if(isYLoad && isX){
            gsm.set(new LoadState(gsm));
            dispose();
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

    public StartingState2(GameStateManager gsm) {
        super(gsm);
        background = new Texture("screens/startPage2.png");
        Gdx.input.setInputProcessor(this);

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