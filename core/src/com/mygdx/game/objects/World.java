package com.mygdx.game.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.Configuration;
import com.mygdx.game.Timer;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MatchGameResultScreen;
import runnables.WorldScreenTheme;

import java.sql.SQLException;

public class World {
    Space space;
    Ship ship;
    AlienArmy alienArmy;
    GameScreen gameScreen;
    Timer timer;


    int WORLD_WIDTH, WORLD_HEIGHT;

    public World(GameScreen gameScren){
        this.WORLD_WIDTH = gameScren.SCENE_WIDTH;
        this.WORLD_HEIGHT = gameScren.SCENE_HEIGHT;
        this.gameScreen = gameScren;

        space = new Space();
        ship = new Ship(WORLD_WIDTH/2);
        alienArmy = new AlienArmy(WORLD_WIDTH, WORLD_HEIGHT);
        timer = new Timer(999999999);
    }



    public void render(float delta, SpriteBatch batch, Assets assets, WorldScreenTheme worldScreenTheme) throws SQLException {

        update(delta, assets,worldScreenTheme);
        timer.update(delta);

        batch.begin();
        space.render(batch);
        ship.render(batch);

        alienArmy.render(batch);

        batch.end();

    }

    void update(float delta, Assets assets,WorldScreenTheme worldScreenTheme) throws SQLException {

        if(ship.isDestroyed() || alienArmy.areAllAliensDead()) {
            worldScreenTheme.setState(false);
            worldScreenTheme.getWorldTheme().dispose();
            gameScreen.setScreen((new MatchGameResultScreen(gameScreen.getGame(),ship.getAliensKilled().getNumber(),ship.getLifes(),timer.getTime())));
        }else {
            space.update(delta, assets);
            ship.update(delta, assets);
            alienArmy.update(delta, assets);

            checkCollisions(assets);
        }
    }

    private void checkCollisions(Assets assets) {
        checkNaveInWorld();
        checkShootsInWorld();
        checkShootsToAlien(assets);
        checkShootsToShip(assets);
    }

    private void checkShootsToShip(Assets assets) {
        Rectangle shipRectangle = new Rectangle(ship.position.x, ship.position.y, ship.frame.getRegionWidth(), ship.frame.getRegionHeight());

        for(AlienShoot shoot: alienArmy.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());

            if (Intersector.overlaps(shootRectangle, shipRectangle)) {
                ship.damage(assets);
                shoot.remove();
            }
        }
    }

    private void checkShootsToAlien(Assets assets) {
        for(Shoot shoot: ship.weapon.shoots){
            Rectangle shootRectangle = new Rectangle(shoot.position.x, shoot.position.y, shoot.frame.getRegionWidth(), shoot.frame.getRegionHeight());
            for(Alien alien: alienArmy.aliens){
                if(alien.isAlive()) {
                    Rectangle alienRectangle = new Rectangle(alien.position.x, alien.position.y, alien.frame.getRegionWidth(), alien.frame.getRegionHeight());

                    if (Intersector.overlaps(shootRectangle, alienRectangle)) {
                        alien.kill();
                        ship.getAliensKilled().addOne();
                        shoot.remove();
                        if(!Configuration.isSoundsDisabled()) {
                            assets.aliendieSound.play();
                        }
                    }
                }
            }
        }
    }

    private void checkShootsInWorld() {
        for(Shoot shoot: ship.weapon.shoots){
            if(shoot.position.y > WORLD_HEIGHT){
                shoot.remove();
            }
        }

        for(AlienShoot shoot: alienArmy.shoots){
            if(shoot.position.y < 0){
                shoot.remove();
            }
        }
    }

    private void checkNaveInWorld() {
        if(ship.position.x > WORLD_WIDTH-32){
            ship.position.x = WORLD_WIDTH-32;
        } else if(ship.position.x < 0){
            ship.position.x = 0;
        }
    }
}
