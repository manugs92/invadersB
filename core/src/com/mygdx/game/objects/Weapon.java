package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Weapon {

    enum State {
        IDLE, SHOOTING;
    }

    Array<Shoot> shoots;

    State state;
    float stateTime;
    float shootRate = 0.5f;

    Weapon(){
        shoots = new Array<Shoot>();
        state = State.IDLE;
        stateTime = 0;
    }

    void render(SpriteBatch batch, Assets assets){
        for (Shoot shoot: shoots) {
            shoot.render(batch, assets);
        }
    }

    public void update(float delta) {
        stateTime += delta;
        for(Shoot shoot: shoots){
            shoot.update(delta);
        }
    }

    public void shoot(int position){
        state = State.SHOOTING;
        if(stateTime > shootRate){
            shoots.add(new Shoot(position));
            stateTime = 0;
        }
    }
}
