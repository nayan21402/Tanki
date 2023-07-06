
package com.mygdx.game.Test;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.resources.AirDrop;
import com.mygdx.game.resources.Rocket;

import org.junit.Test;
import static org.junit.Assert.*;
public class Test4 {

    @Test
    public void checkDamage(){
        AirDrop a1 = new AirDrop(new Rocket() , new Vector2(10 ,0 ));
        assertSame(a1.getWeapons().getDamage() , 65.0f);
    }
}