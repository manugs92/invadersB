package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screen.LoadingScreen;

public class SpaceInvaders extends Game {

	public Assets assets;
    public SpriteBatch spriteBatch;

    public OrthographicCamera camera;
    public Viewport viewport;

    public int SCENE_WIDTH = 16;
    public int SCENE_HEIGHT = 8;


	@Override
	public void create () {
		assets = new Assets();
		assets.load();

		camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH/2, SCENE_HEIGHT/2, 0);
		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
		viewport.apply();

		spriteBatch = new SpriteBatch();

        setScreen(new LoadingScreen(this));
	}

    @Override
    public void render() {
	    spriteBatch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0.23f,0.73f,0.98f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void resize(int width, int height) {
	    super.resize(width,height);

        viewport.update(width ,height);
    }
}
