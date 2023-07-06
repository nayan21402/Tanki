package com.mygdx.game.states;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
        import com.mygdx.game.TankStars;

public class StartingState1 extends State implements InputProcessor {
    private Texture background ;
    static float ET=0;

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
        boolean isY = screenY <= (TankStars.HEIGHT-250) && screenY>=(TankStars.HEIGHT-390);
        boolean isX = screenX >= 640 && screenX<=1190;
        if(isY && isX){
            gsm.set(new StartingState2(gsm));
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

    public StartingState1(GameStateManager gsm) {
        super(gsm);
        background = new Texture("screens/OldstartPage1.png");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput() {
//        if(Gdx.input.isTouched()){
//            gsm.set(new StartingState2(gsm));
//            dispose();
//        }
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