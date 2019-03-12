package com.mygdx.game.screen;

import com.mygdx.game.SpaceInvaders;

public class GameScreen extends SpaceInvadersScreen {

    float timer;

    public GameScreen(SpaceInvaders spaceInvaders) {
        super(spaceInvaders);
    }

    @Override
    public void render(float delta) {
        update(delta);

        batch.begin();
        batch.draw(assets.alien.getKeyFrame(timer, true), 1, 1, 8, 8);
        batch.end();
    }

    void update(float delta){
        timer += delta;
    }
}
