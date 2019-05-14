package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;

public class SpaceInvadersScreen implements Screen {

    SpaceInvaders game;
    Assets assets;
    public static final int SCENE_WIDTH = 384;
    public static final int SCENE_HEIGHT = 256;

    protected SpriteBatch spriteBatch;
    protected OrthographicCamera camera;
    protected Viewport viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);

    public SpaceInvadersScreen(SpaceInvaders game){
        this.game = game;
        this.assets = game.assets;
    }

    public SpaceInvaders getGame() {
        return game;
    }

    public void setScreen(Screen screen){
        game.setScreen(screen);
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
