package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;

public class World {
    Space space;
    Nave nave;

    int WORLD_WIDTH, WORLD_HEIGHT;

    public World(int WORLD_WIDTH, int WORLD_HEIGHT){
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;

        space = new Space();
        nave = new Nave(WORLD_WIDTH/2);
    }

    public void render(float delta, SpriteBatch batch, Assets assets){

        update(delta);

        batch.begin();
        space.render(batch, assets);
        nave.render(batch, assets);
        batch.end();
    }

    void update(float delta){
        space.update(delta);
        nave.update(delta);

        checkCollisions();
    }

    private void checkCollisions() {
        checkNaveInWorld();
        checkShootsInWorld();
    }

    private void checkShootsInWorld() {
        Array<Shoot> shootsToRemove = new Array<Shoot>();
        for(Shoot shoot: nave.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shootsToRemove.add(shoot);
            }
        }

        for (Shoot shoot: shootsToRemove){
            nave.weapon.shoots.removeValue(shoot, true);
        }
    }

    private void checkNaveInWorld() {
        if(nave.position > WORLD_WIDTH-32){
            nave.position = WORLD_WIDTH-32;
        } else if(nave.position < 0){
            nave.position = 0;
        }
    }
}
