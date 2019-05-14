package com.mygdx.game;


public class Configuration {

    private static String playerName="Player";
    private static float volume= (float) 0.5;
    private static boolean musicDisabled = false;
    private static boolean soundsDisabled = false;


    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerNameR) {
        playerName = playerNameR;
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volumeR) {
        volume = volumeR;
    }

    public static boolean isMusicDisabled() { return musicDisabled; }

    public static void setSoundsDisabled(boolean soundsDisabledR) {
        soundsDisabled = soundsDisabledR;
    }

    public static boolean isSoundsDisabled() { return soundsDisabled; }

    public static void setMusicDisabled(boolean volumeDisabledR) {
        musicDisabled = volumeDisabledR;
    }

}
