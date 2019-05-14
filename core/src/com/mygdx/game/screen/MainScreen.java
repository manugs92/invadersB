package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;
import runnables.MainScreenTheme;

import java.sql.SQLException;


public class MainScreen extends SpaceInvadersScreen {


    private Space space;
    private Stage stage;
    private Texture textureButtonNewGame = new Texture("buttonNewGame.png");
    private Image imageButtonNewGame = new Image(textureButtonNewGame);
    private Texture textureButtonConfiguration = new Texture("buttonConfigurationGame.png");
    private Image imageButtonConfiguration = new Image(textureButtonConfiguration);
    private Texture textureButtonTop10 = new Texture("buttonTop10.png");
    private Image imageButtonTop10 = new Image(textureButtonTop10);
    private Texture textureButtonExit = new Texture("buttonExit.png");
    private Image imageButtonExit = new Image(textureButtonExit);
    private MainScreenTheme mainScreenTheme;

    public MainScreen(SpaceInvaders spaceInvaders) {
        super(spaceInvaders);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        showMainMenu();
        addListenersToButtons();
        if(mainScreenTheme == null) {
            mainScreenTheme = new MainScreenTheme();
            mainScreenTheme.start();
        }
    }

    public MainScreen(SpaceInvaders spaceInvaders,MainScreenTheme mainScreenTheme) {
        super(spaceInvaders);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.mainScreenTheme=mainScreenTheme;
        showMainMenu();
        addListenersToButtons();
    }


    private void addListenersToButtons() {
        imageButtonNewGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreenTheme.setState(false);
                mainScreenTheme.getMainTheme().dispose();
                setScreen(new GameScreen(game));
            }
        });

        imageButtonConfiguration.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreen(new ConfigurationScreen(game,mainScreenTheme));
            }
        });

        imageButtonTop10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    setScreen(new top10Screen(game,mainScreenTheme));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        imageButtonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


    }

    @Override
    public void show()
    {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH/2, SCENE_HEIGHT/2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        viewport.apply();

        space = new Space();
    }

    @Override
    public void render(float delta) {

        update(delta, assets);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        space.render(spriteBatch);
        spriteBatch.end();

        stage.draw();
    }

    public void update(float delta, Assets assets){
            space.update(delta, assets);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

        viewport.update(width ,height);
    }

    private void showMainMenu() {
        Table table = new Table();
        table.setFillParent(true);
        table.add(imageButtonNewGame);
        table.row().pad(10);
        table.add(imageButtonTop10);
        table.row().pad(10);
        table.add(imageButtonConfiguration);
        table.row().pad(10);
        table.add(imageButtonExit);
        stage.addActor(table);
    }
}
