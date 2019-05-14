package runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.Configuration;


public class MainScreenTheme extends Thread {

    private String pathMainTheme = "tittle_screen.mp3";
    private Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal(pathMainTheme));
    private  boolean state = true;

    @Override
    public void run() {
        mainTheme.play();
        mainTheme.setLooping(true);
        while(state) {
            mainTheme.setVolume(Configuration.getVolume());
        }
    }

    public Music getMainTheme() {
        return mainTheme;
    }

    public void setState(boolean state) {
        this.state=state;
    }
}
