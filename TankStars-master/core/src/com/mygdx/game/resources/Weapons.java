package com.mygdx.game.resources;

import java.io.Serializable;

public class Weapons implements Serializable {
    private double damage ;

    private String name ;

    public Weapons(double damage, String name) {
        this.damage = damage;

        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
