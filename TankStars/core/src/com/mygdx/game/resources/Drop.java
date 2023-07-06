package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TankStars;

public class Drop
{
    public int speed=500;
    public int direction;
    private static Texture texture;

    public float x,y;
    public Vector2 final_pos;

    public boolean remove = false;

    public Drop(float x, float y,Vector2 final_pos) {
        this.x = x;
        this.y = y;
        this.final_pos=final_pos;
        if (texture == null) {
            texture = new Texture("screens/air_drop.jpg");
        }
    }

    public void update(float dt) {
        if(y>final_pos.y)
        y -= speed * dt ;
        if(y<=final_pos.y){
            y=final_pos.y;
        }

    }
    public void render(SpriteBatch sb){
        //sb.draw(texture,x,y,50,50);
        sb.draw(texture, x - 25, y - 25, 25, 25, 75, 75, 1, 1, 0, 0, 0, texture.getWidth(), texture.getHeight(), false, false);


    }

}
