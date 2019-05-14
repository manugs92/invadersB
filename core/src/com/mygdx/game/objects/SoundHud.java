package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Configuration;
import com.mygdx.game.screen.GameScreen;

public class SoundHud {


    private Texture textureMusicOff = new Texture("musicoff.png");
    private Texture textureMusicOn = new Texture("musicon.png");
    private Texture textureSoundOn = new Texture("soundon.png");
    private Texture textureSoundOf = new Texture("soundoff.png");

    private Image imageMusicOff = new Image(textureMusicOff);
    private Image imageMusicOn = new Image(textureMusicOn);
    private Image imageSoundOff = new Image(textureSoundOf);
    private Image imageSoundOn = new Image(textureSoundOn);
    private Stage stage;

    public SoundHud(Viewport viewport) {
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
    }

    public void showSounds() {
        imageMusicOn.setPosition(GameScreen.SCENE_WIDTH/2-20, GameScreen.SCENE_HEIGHT-20);
        imageMusicOff.setPosition(GameScreen.SCENE_WIDTH/2-20, GameScreen.SCENE_HEIGHT-20);
        imageSoundOn.setPosition(GameScreen.SCENE_WIDTH/2,GameScreen.SCENE_HEIGHT-20);
        imageSoundOff.setPosition(GameScreen.SCENE_WIDTH/2,GameScreen.SCENE_HEIGHT-20);

        update();
    }

    public void addListenersToButtons() {
        imageMusicOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Configuration.setMusicDisabled(false);
            }
        });

        imageMusicOn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Configuration.setMusicDisabled(true);
            }
        });

        imageSoundOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Configuration.setSoundsDisabled(false);
            }
        });

        imageSoundOn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Configuration.setSoundsDisabled(true);
            }
        });

        stage.addActor(imageSoundOn);
        stage.addActor(imageSoundOff);
        stage.addActor(imageMusicOff);
        stage.addActor(imageMusicOn);
    }

    public void render(){
        stage.draw();
    }

    public void update() {
        if(Configuration.isSoundsDisabled()) {
            imageSoundOn.setVisible(false);
            imageSoundOff.setVisible(true);
        }else {
            imageSoundOn.setVisible(true);
            imageSoundOff.setVisible(false);
        }

        if(Configuration.isMusicDisabled()) {
            imageMusicOn.setVisible(false);
            imageMusicOff.setVisible(true);
        }else {
            imageMusicOn.setVisible(true);
            imageMusicOff.setVisible(false);
        }
    }
}
