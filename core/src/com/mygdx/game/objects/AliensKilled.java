package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.GameScreen;

public class AliensKilled {

    BitmapFont font = new BitmapFont();
    float posX;
    float posY;
    int number;

    public AliensKilled() {
        this.posX = GameScreen.SCENE_WIDTH-40;
        this.posY = GameScreen.SCENE_HEIGHT-5;
        this.number=0;
    }

    public int getNumber() {
        return number;
    }

    public void addOne() {
        this.number=this.number+1;
    }

    public void render(SpriteBatch batch) {
         font.draw(batch,String.valueOf(number), posX, posY);
    }
}
