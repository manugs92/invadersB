package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.screen.LoadingScreen;

public class SpaceInvaders extends Game {

	public Assets assets;

	@Override
	public void create () {
		assets = new Assets();
		assets.load();

        setScreen(new LoadingScreen(this));
	}

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.23f,0.73f,0.98f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }
}
