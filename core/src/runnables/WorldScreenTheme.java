package runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.Configuration;

public class WorldScreenTheme extends Thread{

    private String pathWorldTheme = "earth.mp3";
    private Music worldTheme = Gdx.audio.newMusic(Gdx.files.internal(pathWorldTheme));
    private  boolean state = true;

    @Override
    public void run() {
       worldTheme.play();
        worldTheme.setLooping(true);
        while(state) {
            if(!Configuration.isMusicDisabled()) {
                if(!worldTheme.isPlaying()) {
                    worldTheme.play();
                }
                worldTheme.setVolume(Configuration.getVolume());
            }else {
                worldTheme.stop();
            }
        }
    }

    public Music getWorldTheme() {
        return worldTheme;
    }

    public void setState(boolean state) {
        this.state=state;
    }
}
