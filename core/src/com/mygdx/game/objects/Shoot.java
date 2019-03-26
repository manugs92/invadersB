package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Shoot {
    Vector2 position;

    float stateTime;
    float speed = 5;

    Shoot(int position){
        this.position = new Vector2(position, 16);
        stateTime = 0;
    }


    void render(SpriteBatch batch, Assets assets){
        batch.draw(assets.shoot.getKeyFrame(stateTime, true), position.x, position.y);
    }

    public void update(float delta) {
        stateTime += delta;

        position.y += speed;
    }
}
