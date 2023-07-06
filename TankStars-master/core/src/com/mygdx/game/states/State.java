package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera cam ; // for every state camera tell us what part of
    // the world we want to show
    protected Vector3 mouse; // pointer x,y,z coordinate system

    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt); // takes a delta time
    // ie difference in time between one frame render and another
    public abstract void render(SpriteBatch sb, ShapeRenderer sr) throws InterruptedException; // contains all the content we need to render the screen and the method renders

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();                                       // everything in one big blob
}
