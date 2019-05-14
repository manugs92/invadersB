package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;

public class Space {

    float stateTime;

    TextureRegion frame;

    public void render(SpriteBatch batch){
        batch.draw(frame, 0, 0);
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;
        frame = assets.space.getKeyFrame(stateTime, true);
    }
}
