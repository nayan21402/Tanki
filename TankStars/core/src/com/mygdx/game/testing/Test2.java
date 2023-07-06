package com.mygdx.game.Test;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.resources.HyperBlast;
import com.mygdx.game.resources.NapalmStrike;
import com.mygdx.game.resources.Player;
import com.mygdx.game.resources.Rocket;
import com.mygdx.game.resources.Tank;

import org.junit.Test;
import static org.junit.Assert.*;
public class Test2 {

    @Test
    public void checkPlayer(){
        Player p1 = new Player(100.0f , new Tank(100.0f , new Vector3(0,0,0) , null , 0.0f));
        assertSame(new Rocket() , p1.getTank().getWeapons().get(0));
        assertSame(new NapalmStrike() , p1.getTank().getWeapons().get(0));
        assertSame(new HyperBlast() , p1.getTank().getWeapons().get(0));
    }
}