package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;

public class SpaceInvadersScreen implements Screen {
    SpaceInvaders si;
    Assets assets;
    SpriteBatch batch;

    public SpaceInvadersScreen(SpaceInvaders si){
        this.si = si;
        this.assets = si.assets;
        this.batch = si.spriteBatch;
    }

    void setScreen(Screen screen){
        si.setScreen(screen);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
