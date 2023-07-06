
package com.mygdx.game.states;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
        import com.mygdx.game.TankStars;

public class PlayState extends State implements InputProcessor {
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

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("screens/playScreen.png");
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