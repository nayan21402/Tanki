package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

public class GameStateManager { // GameStateManager manages the states in a game
    // if during the game user is currently on the play state and the presses pause button
    // the pause state will be on top of play state therefore it needs to keep on
    // rendering and updating the pause state instead of play state
    // TO MANAGE THE STATES WE WILL BE USING STACK OF STATES

    private static Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) throws InterruptedException {
        states.peek().render(sb,sr);
    }
}
