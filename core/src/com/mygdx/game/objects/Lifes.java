package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.GameScreen;

public class Lifes {

    private final int START_LIVES = 5;
    private int actualLives;
    private Texture[] textureHeart = new Texture[START_LIVES];
    private float posX = 5;
    private float posY = GameScreen.SCENE_HEIGHT-20;

    public Lifes(){
        for(int i=0;i<START_LIVES;i++) {
            textureHeart[i] = new Texture("heart0.png");
        }
        actualLives=START_LIVES;
    }

    public void render(SpriteBatch batch) {
        for(int i=0;i<actualLives;i++) { batch.draw(textureHeart[i], posX+(20*i), posY); }

    }

    public int getLifes() {
        return actualLives;
    }

    public void loseLifes() {
        this.actualLives = this.actualLives-1;
    }
}
