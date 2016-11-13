package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 */
public class PlayScreenManager {
    MazeGame game;
    Array<Screen> screens = new Array<Screen>();
    int currentScreenIndex;
    public PlayScreenManager(MazeGame game)
    {
        this.game = game;
        currentScreenIndex = 0;
    }
    public void addScreen(Screen screen)
    {
        screens.add(screen);
    }
    public void setNextScreen()
    {
        if(currentScreenIndex<screens.size)
        game.setScreen(screens.get(++currentScreenIndex));
    }
    public void resetStage()
    {
        if(screens.get(currentScreenIndex)instanceof PlayScreenStageOne){
            screens.set(currentScreenIndex, new PlayScreenStageOne(game,"maze1.tmx"));
        }
        setCurrentScreen();
    }
    public void setCurrentScreen()
    {
        game.setScreen(screens.get(currentScreenIndex));
    }
}
