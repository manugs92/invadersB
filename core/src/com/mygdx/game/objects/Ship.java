package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Configuration;
import com.mygdx.game.Controls;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT;
    }

    Vector2 position;

    State state;
    float stateTime;
    float speed = 5;

    TextureRegion frame;

    Weapon weapon;

    Lifes lifes;

    AliensKilled aliensKilled;

    Ship(int initialPosition){
        position = new Vector2(initialPosition, 10);
        state = State.IDLE;
        stateTime = 0;
        weapon = new Weapon();
        lifes = new Lifes();
        aliensKilled = new AliensKilled();
    }


    void setFrame(Assets assets){
        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }
    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x, position.y);
        lifes.render(batch);
        aliensKilled.render(batch);
        weapon.render(batch);
    }

    public void update(float delta, Assets assets) {
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
            if(!Configuration.isSoundsDisabled()) {
                assets.shootSound.play();
            }

        }

        setFrame(assets);

        weapon.update(delta, assets);
    }

    void idle(){
        state = State.IDLE;
    }

    void moveLeft(){
        position.x -= speed;
        state = State.LEFT;
    }

    void moveRight(){
        position.x += speed;
        state = State.RIGHT;
    }

    void shoot(){
        state = State.SHOOT;
        weapon.shoot(position.x +16);
    }

    public void damage(Assets assets) {
        if(!Configuration.isSoundsDisabled()) {
            assets.shipDamageSound.play();
        }
        lifes.loseLifes();
    }

    public boolean isDestroyed(){
        if(lifes.getLifes()<0) {
            return true;
        }else {
            return false;
        }
    }

    public AliensKilled getAliensKilled() {
        return aliensKilled;
    }

    public int getLifes() {
        return lifes.getLifes();
    }
}
