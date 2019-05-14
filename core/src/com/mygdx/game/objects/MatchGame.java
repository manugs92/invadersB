package com.mygdx.game.objects;

public class MatchGame {

    private String playerName;
    private int aliensKilled;
    private int lifes;
    private float time;

    public MatchGame(String playerName,int aliensKilled, int lifes, float time) {
        this.playerName=playerName;
        this.aliensKilled=aliensKilled;
        this.lifes=lifes;
        this.time=time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAliensKilled() {
        return aliensKilled;
    }

    public void setAliensKilled(int aliensKilled) {
        this.aliensKilled = aliensKilled;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }



}
