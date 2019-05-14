package database;

import com.mygdx.game.objects.MatchGame;

public class DBQueries {

    public static String getTop10() {
        return "select * from test order by aliensKilled,lives,time LIMIT 10 ";
    }

    public static String insertMatchGame(MatchGame matchGame) {
        String query = "INSERT INTO test (playerName,aliensKilled,lives,time) VALUES ('"+matchGame.getPlayerName()+"','"+matchGame.getAliensKilled()+"','"+matchGame.getLifes()+"','"+matchGame.getTime()+"')";

        return query;
    }
}
