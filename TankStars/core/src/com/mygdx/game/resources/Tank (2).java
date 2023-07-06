package com.mygdx.game.resources;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Tank implements Serializable {
    private ArrayList<Weapons> weapons;

    private double fuel;

    private Vector3 location;

    private Texture image;


    private float current ;
    private Weapons currWeapon;
    public Tank(double fuel, Vector3 location, Texture image , float current) {
        this.weapons = new ArrayList<Weapons>();
        weapons.add(new Rocket());
        weapons.add(new NapalmStrike());
        weapons.add(new HyperBlast());
        this.fuel = fuel;
        this.location = location;
        this.image = image;
        this.current = current;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public Weapons getCurrWeapon() {
        return currWeapon;
    }

    public void setCurrWeapon(Weapons currWeapon) {
        this.currWeapon = currWeapon;
    }

    public ArrayList<Weapons> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapons> weapons) {
        this.weapons = weapons;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public Vector3 getLocation() {
        return location;
    }

    public void setLocation(Vector3 location) {
        this.location = location;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    // iterator design pattern
    public void fireWeapon(){
        Iterator iter = weapons.iterator();
        while(iter.hasNext()){
            if(iter == currWeapon) iter.remove();
        }
    }
}