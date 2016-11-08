package com.lamtuananh.maze.screens;

import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 */
public class PlayScreenManager {
    MazeGame game;
    Array<PlayScreen> screens = new Array<PlayScreen>();
    int currentScreenIndex;
    public PlayScreenManager(MazeGame game)
    {
        this.game = game;
        currentScreenIndex = 0;
    }
    public void addScreen(PlayScreen screen)
    {
        screens.add(screen);
    }
    public void setNextScreen()
    {
        if(currentScreenIndex<screens.size)
        game.setScreen(screens.get(++currentScreenIndex));
    }
    public void replayCurrentScreen()
    {
        game.setScreen(screens.get(currentScreenIndex));
    }
}
