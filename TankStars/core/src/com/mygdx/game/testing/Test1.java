package com.mygdx.game.Test;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.resources.Rocket;
import com.mygdx.game.resources.Tank;

import org.junit.Test;
import static org.junit.Assert.*;
public class Test1 {

    @Test
    public void checkTank(){
        Tank t1 = new Tank(100.0f , new Vector3(0,0,0) , null , 0.0f);
        assertSame(new Rocket() , t1.getWeapons().get(0));
    }
}
