package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Nave {
    enum State {
        IDLE, LEFT, RIGHT, SHOOT;
    }

    int position;

    State state;
    float stateTime;
    float speed = 5;

    Weapon weapon;

    Nave(int initialPosition){
        position = initialPosition;
        state = State.IDLE;
        stateTime = 0;

        weapon = new Weapon();
    }

    TextureRegion getFrame(Assets assets){
        switch (state){
            case IDLE:
                return assets.naveidle.getKeyFrame(stateTime, true);
            case LEFT:
                return assets.naveleft.getKeyFrame(stateTime, true);
            case RIGHT:
                return assets.naveright.getKeyFrame(stateTime, true);
            case SHOOT:
                return assets.naveshoot.getKeyFrame(stateTime, true);
            default:
                return assets.naveidle.getKeyFrame(stateTime, true);
        }
    }

    void render(SpriteBatch batch, Assets assets){
        batch.draw(getFrame(assets), position, 10);

        weapon.render(batch, assets);
    }

    public void update(float delta) {
        stateTime += delta;

        if(Controls.isLeftPressed()){
            moveLeft();
        } else if(Controls.isRightPressed()){
            moveRight();
        } else {
            idle();
        }

        if(Controls.isShootPressed()) {
            shoot();
        }

        weapon.update(delta);
    }

    void idle(){
        state = State.IDLE;
    }

    void moveLeft(){
        position -= speed;
        state = State.LEFT;
    }

    void moveRight(){
        position += speed;
        state = State.RIGHT;
    }

    void shoot(){
        state = State.SHOOT;
        weapon.shoot(position+16);
    }
}
