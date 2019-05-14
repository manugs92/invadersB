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
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.objects.Space;
import database.DBConnection;
import database.DBQueries;
import gui.BackgroundTable;
import runnables.MainScreenTheme;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class top10Screen extends SpaceInvadersScreen {

    private Stage stage;
    private Space space;
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private DBConnection dbConnection;
    private Connection con;
    private Table table;
    private MainScreenTheme mainScreenTheme;

    public top10Screen(SpaceInvaders game,MainScreenTheme mainScreenTheme) throws SQLException {
        super(game);
        this.mainScreenTheme=mainScreenTheme;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        initConection();
        createWidgets();

        if(con!=null) {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(DBQueries.getTop10());
            addRegisters(rs);

            dbConnection.closeStament(stmt);
            dbConnection.closeResultSet(rs);
            dbConnection.closeConnection(con);
        }else {
            showConetionFailed();
        }
        addButtonToMainMenu();
        stage.addActor(table);
    }



    private void addButtonToMainMenu() {
        Image buttonToMainMenu = new Image(new Texture("buttonToMainMenu.png"));
        buttonToMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setScreen(new MainScreen(game,mainScreenTheme));
            }
        });
        table.add(buttonToMainMenu).colspan(5).align(Align.right);
    }

    private void showConetionFailed() {
        Label nothingToShow = new Label("No se ha podido conectar\nconla base de datos\ndel servidor.",skin);
        table.add(nothingToShow).colspan(5);
        table.row().pad(30,10,5,10);
    }

    private void addRegisters(ResultSet rs) throws SQLException {
        int position = 1;
        while(rs.next()) {
            Label labelPosition = new Label(position+" º",skin);
            labelPosition.setFontScale((float) 0.5);
            Label labelName = new Label(rs.getString("playerName"),skin);
            labelName.setFontScale((float) 0.5);
            Label labelKills = new Label(rs.getString("aliensKilled"),skin);
            labelKills.setFontScale((float) 0.5);
            Label labelLifes = new Label(rs.getString("lives"),skin);
            labelLifes.setFontScale((float) 0.5);
            Label labelTime = new Label(rs.getString("time"),skin);
            labelTime.setFontScale((float) 0.5);

            table.add(labelPosition);
            table.add(labelName);
            table.add(labelKills);
            table.add(labelLifes);
            table.add(labelTime);
            table.row().pad(5,10,5,10);

            position++;
        }
    }

    public void createWidgets() {
        table = new Table();

        BackgroundTable backgroundTable = new BackgroundTable("black.png");
        table.setFillParent(true);
        table.setBackground(backgroundTable);

        Label mainHeaderLabel = new Label("TOP 10",skin);
        mainHeaderLabel.getStyle().fontColor.set(255,255,255,255);
        mainHeaderLabel.setFontScale(1);


        Label headerPosition = new Label("POSICION",skin);
        headerPosition.setFontScale((float) 0.5);
        Label headerPlayer = new Label("JUGADOR",skin);
        headerPlayer.setFontScale((float) 0.5);
        Label  headerAliensKilled = new Label("ALIENS ELIMINADOS",skin);
        headerAliensKilled.setFontScale((float) 0.5);
        Label  headerLifes = new Label("VIDAS RESTANTES",skin);
        headerLifes.setFontScale((float) 0.5);
        Label  headerTime = new Label("TIEMPO REALIZADO",skin);
        headerTime.setFontScale((float) 0.5);

        table.add(mainHeaderLabel).colspan(5).align(Align.center);
        table.row().pad(30,10,5,10);

        table.add(headerPosition);
        table.add(headerPlayer);
        table.add(headerAliensKilled);
        table.add(headerLifes);
        table.add(headerTime);
        table.row().pad(5,10,5,10);
    }

    private void initConection() {
        dbConnection = new DBConnection();
        con = dbConnection.getConnection();
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.position.set(SCENE_WIDTH/2, SCENE_HEIGHT/2, 0);
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);

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

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

        viewport.update(width ,height);
    }

    public void update(float delta, Assets assets){
        space.update(delta, assets);
    }
}
