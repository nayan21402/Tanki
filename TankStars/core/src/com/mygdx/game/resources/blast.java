package com.mygdx.game.resources;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class blast {

    public Body create_body(int x, int y, int width, int height, boolean isStatic, World world){
        Body pBody;
        BodyDef def = new BodyDef();
        if(!isStatic)
            def.type=BodyDef.BodyType.DynamicBody;
        else
            def.type=BodyDef.BodyType.StaticBody;

        def.position.set(x/32f,y/32f);
        def.fixedRotation=true;
        pBody=world.createBody(def);

        PolygonShape shape=new PolygonShape();
        shape.setAsBox(width/2 /32f,height/2 /32f);

        pBody.createFixture(shape,1f); //density
        shape.dispose();
        return pBody;
    }
}
