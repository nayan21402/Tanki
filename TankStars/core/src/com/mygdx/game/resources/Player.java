package com.mygdx.game.resources;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public class Player implements Serializable {
    double health;

    Tank tank;

    public Player(float health, Tank tank) {
    }

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
    public void setTexture(Texture t){
        tank.setImage(t);
    }
    public Player(double health , Tank tank){
        this.health = health;
        this.tank = tank;
    }
}
