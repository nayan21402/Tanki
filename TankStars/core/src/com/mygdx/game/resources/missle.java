package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TankStars;

public class missle
{
    public int speed=1000;
    public int direction;
    private static Texture texture;

    public float x,y;

    public boolean remove = false;

    public missle(float x, float y,int direction) {
        this.x = x;
        this.y = y;
        this.direction=direction;
        if (texture == null) {
            texture = new Texture("screens/bomb.png");
        }
    }

        public void update(float dt) {
            x += speed * dt * direction;
            if (x > TankStars.WIDTH) {
                remove = true;
            }
            if (x < 0) {
                remove = true;
            }

        }
        public void render(SpriteBatch sb){
        sb.draw(texture,x,y,20,20);

    }

}
