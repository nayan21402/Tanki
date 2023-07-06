package com.mygdx.game.resources;


import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class AirDrop implements Serializable {

    private Weapons weapons;

    public Weapons getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapons weapons) {
        this.weapons = weapons;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    private Vector2 location;

    public AirDrop(Weapons weapons, Vector2 location) {
        this.weapons = weapons;
        this.location = location;
    }

    public int getPriorityRatio() {
        return 1;
    }
}
