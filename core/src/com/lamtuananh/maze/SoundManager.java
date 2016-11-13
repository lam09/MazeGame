package com.lamtuananh.maze;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Lam on 11/12/2016.
 */
public class SoundManager {
    static SoundManager instance = new SoundManager();
    public SoundManager (){}
    public static SoundManager getInstance() {return instance;}

    Music background;
    Music menuMusic;
    Sound hit;
    float volume = 1.0f;
    public void init()
    {
        background = MazeGame.manager.get("sound/background.mp3",Music.class);
      //  menuMusic = MazeGame.manager.get("sound/menu.mp3",Music.class);
    }
    public void playBackgroundMusic(){
        background.setLooping(true);
        background.play();
    }
    public void stopBackgroundMusic(){
        background.stop();
    }
    public void setVolume(float value)
    {
        this.volume = value;
    }
}
