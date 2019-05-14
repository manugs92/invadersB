package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Configuration;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;
import gui.BackgroundTable;
import runnables.MainScreenTheme;

public class ConfigurationScreen extends SpaceInvadersScreen {

    private Space space;
    private Stage stage;
    private Slider slider;
    private Image backButton;
    private CheckBox cbActiveMusic;
    private CheckBox cbactiveSounds;
    private MainScreenTheme mainScreenTheme;
    private TextField textFieldPlayerName;

    public ConfigurationScreen(SpaceInvaders game, MainScreenTheme mainScreenTheme) {
        super(game);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.mainScreenTheme=mainScreenTheme;
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        showConfigurationMenu(skin);
        addListenersToButtons();
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

    private void addListenersToButtons() {
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Configuration.setVolume(slider.getValue());
            }
        });

        cbActiveMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!cbActiveMusic.isChecked()) {
                    Configuration.setMusicDisabled(false);
                    Configuration.setVolume((float) 0.5);
                    slider.setValue((float)0.5);
                }else {
                    Configuration.setMusicDisabled(true);
                    Configuration.setVolume(0);
                    slider.setValue(0);
                }

            }
        });

        cbactiveSounds.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Configuration.setSoundsDisabled(true);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Configuration.setPlayerName(textFieldPlayerName.getText());
                setScreen(new MainScreen(game,mainScreenTheme));
            }
        });


    }

    private void showConfigurationMenu(Skin skin) {

        //Configuration of widgets

        Table table = new Table();
        BackgroundTable backgroundTable = new BackgroundTable("black.png");

        Label playerName = new Label("Nombre de jugador: ",skin);
        playerName.getStyle().fontColor.set(255,255,255,255);

        Label volumeValue = new Label("Volumen musica: ",skin);

        textFieldPlayerName = new TextField("",skin);
        if(Configuration.getPlayerName()!=null) {
            textFieldPlayerName.setText(Configuration.getPlayerName());
        }

        Label activeMusic = new Label("Musica activa: ",skin);

        cbActiveMusic = new CheckBox("",skin);
        if(Configuration.isMusicDisabled()) {
            cbActiveMusic.setChecked(true);
        }else {
            cbActiveMusic.setChecked(false);
        }

        Label activeSounds = new Label("Sonidos activos: ",skin);

        cbactiveSounds = new CheckBox("",skin);
        if(Configuration.isSoundsDisabled()) {
            cbactiveSounds.setChecked(true);
        }else {
            cbactiveSounds.setChecked(false);
        }


        Texture textureBack = new Texture("backButton.png");
        backButton = new Image(textureBack);

        slider = new Slider(0,1,0.05f,false,skin);
        slider.setValue(Configuration.getVolume());
        slider.setSize(450,50);

        table.setFillParent(true);
        table.add(playerName);
        table.add(textFieldPlayerName);
        table.row().pad(10);
        table.add(volumeValue).align(Align.left);
        table.add(slider);
        table.row().pad(10);
        table.add(activeMusic).align(Align.left);
        table.add(cbActiveMusic);
        table.row().pad(10);
        table.add(activeSounds).align(Align.left);
        table.add(cbactiveSounds);
        table.row().pad(10);
        table.add(backButton).colspan(2).align(Align.right);

        table.setBackground(backgroundTable);


        stage.addActor(table);
    }


}
