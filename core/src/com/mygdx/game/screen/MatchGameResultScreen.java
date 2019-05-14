package com.mygdx.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.Configuration;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.MatchGame;
import com.mygdx.game.objects.Space;
import database.DBConnection;
import database.DBQueries;
import gui.BackgroundTable;
import runnables.MainScreenTheme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchGameResultScreen extends SpaceInvadersScreen {

    //TODO: Añadir listeners para ir a Top10 y a menú principal.
    //TODO: Cambiar el formato de presentación de los segundos a minutos y segundos.

    Stage stage;
    Space space;
    Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    MatchGame matchGame;
    Image buttonToMainMenu;
    Image buttonToTop10;
    MainScreenTheme mainScreenTheme;


    public MatchGameResultScreen(SpaceInvaders game,int aliensKilled,int lifes,float time) throws SQLException {
        super(game);
        lifes = lifes-5;
        if(lifes<0) lifes = 0;
        matchGame = new MatchGame(Configuration.getPlayerName(),aliensKilled,lifes,time);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        mainScreenTheme=new MainScreenTheme();
        mainScreenTheme.start();

        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.getConnection();
        if(con != null) {
            Statement stmt=con.createStatement();
            stmt.executeUpdate(DBQueries.insertMatchGame(matchGame));
        }else {
            System.out.println("No se ha introducido tu partida por error en la conexión con la BD.");
        }

    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH/2, SCENE_HEIGHT/2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        showResume();
        addListenerToButtons();

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


    private void showResume() {
        Table table = new Table();
        BackgroundTable backgroundTable = new BackgroundTable("black.png");
        table.setFillParent(true);
        table.setBackground(backgroundTable);

        Label headerLabel = new Label("RESULTADO DE PARTIDA",skin);
        headerLabel.getStyle().fontColor.set(255,255,255,255);

        Label playerNameLabel = new Label("JUGADOR: "+matchGame.getPlayerName(),skin);

        Label aliensKilledLabel = new Label("ALIENS ELIMINADOS: "+matchGame.getAliensKilled(),skin);

        Label timesDeadLabel = new Label("VIDAS RESTANTES: "+matchGame.getLifes(),skin);

        Label timeDoneLabel = new Label("TIEMPO REALIZADO: "+matchGame.getTime(),skin);

        buttonToMainMenu = new Image(new Texture("buttonToMainMenu.png"));

        buttonToTop10 = new Image(new Texture("buttonToTop10.png"));


        table.add(headerLabel).colspan(2).align(Align.center);
        table.row().pad(30,10,5,10);
        table.add(playerNameLabel).align(Align.left);
        table.row().pad(5,10,5,10);
        table.add(aliensKilledLabel).align(Align.left);
        table.row().pad(5,10,5,10);
        table.add(timesDeadLabel).align(Align.left);
        table.row().pad(5,10,5,10);
        table.add(timeDoneLabel).align(Align.left);
        table.row().pad(30,10,5,10);
        table.add(buttonToMainMenu);
        table.add(buttonToTop10);

        stage.addActor(table);
    }

    private void addListenerToButtons() {
        buttonToMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreen(new MainScreen(game,mainScreenTheme));
            }
        });

        buttonToTop10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    setScreen(new top10Screen(game,mainScreenTheme));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
