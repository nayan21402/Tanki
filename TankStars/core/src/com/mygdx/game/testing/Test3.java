
package com.mygdx.game.Test;
        import com.badlogic.gdx.math.Vector2;

        import com.mygdx.game.resources.AirDrop;
        import com.mygdx.game.resources.HyperBlast;
        import com.mygdx.game.resources.NapalmStrike;

        import com.mygdx.game.resources.Rocket;
        import com.mygdx.game.resources.Tank;

        import org.junit.Test;
        import static org.junit.Assert.*;
public class Test3 {

    @Test
    public void checkAirDrop(){
        AirDrop a1 = new AirDrop(new Rocket() , new Vector2(10 ,0 ));
        assertEquals(a1.getPriorityRatio() , 6);
    }
}