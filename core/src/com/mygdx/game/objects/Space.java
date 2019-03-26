package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Space {

    float stateTime;

    void render(SpriteBatch batch, Assets assets){
        batch.draw(assets.space.getKeyFrame(stateTime, true), 0, 0);
    }

    public void update(float delta) {
        stateTime += delta;
    }
}
