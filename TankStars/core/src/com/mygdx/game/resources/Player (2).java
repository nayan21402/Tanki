package com.mygdx.game.resources;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public class Player implements Serializable {
    private double health;

    private Tank tank;

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Player(double health , Tank tank){
        this.health = health;
        this.tank = tank;
    }

    public void setTexture(Texture tex){
        if(tank == null) return;
        tank.setImage(tex);
    }
    public void setCurrent(float curr){
        tank.setCurrent(curr);
    }
}