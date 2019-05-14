package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.SoundHud;
import com.mygdx.game.objects.World;
import runnables.WorldScreenTheme;

import java.sql.SQLException;

public class GameScreen extends SpaceInvadersScreen {

    World world;
    WorldScreenTheme worldScreenTheme;
    SoundHud soundHud;

    public GameScreen(SpaceInvaders spaceInvaders) {
        super(spaceInvaders);
        worldScreenTheme = new WorldScreenTheme();
        worldScreenTheme.start();
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH/2, SCENE_HEIGHT/2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        viewport.apply();

        world = new World(this);
        soundHud = new SoundHud(viewport);
        soundHud.showSounds();
        soundHud.addListenersToButtons();
    }

    @Override
    public void render(float delta) {
        spriteBatch.setProjectionMatrix(camera.combined);

        try {
            world.render(delta, spriteBatch, assets,worldScreenTheme);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        soundHud.render();
        soundHud.update();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        viewport.update(width ,height);
    }
}
