package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 * Screen :
 * menu - 0;
 * stage 1
 * stage 2
 * stage 3 ...
 */
public class PlayScreenManager {
    MazeGame game;
    Array<Screen> screens = new Array<Screen>();
    PlayScreen currentScreen = null;
   public static int currentScreenIndex=0;
    public PlayScreenManager(MazeGame game)
    {
        this.game = game;
        currentScreenIndex = 0;
    }
    public void setNextScreen()
    {
        currentScreenIndex++;
        MazeGame.prefs.putInteger("level",currentScreenIndex);
        MazeGame.prefs.flush();
        setCurrentScreen();
    }
    public void resetStage()
    {
        setCurrentScreen();
    }
    public void setCurrentScreen()
    {

        switch (currentScreenIndex){
            case 1:
                currentScreen = new PlayScreenStageOne(game,"maze1.tmx",1);
                game.setScreen(currentScreen);
                break;
            case 2:
                currentScreen = new PlayScreenStageOne(game,"maze2.tmx",1);
                game.setScreen(currentScreen);
                break;
            case 3:
                currentScreen = new PlayScreenStageOne(game,"maze3.tmx",1);
                game.setScreen(currentScreen);
                break;
            case 4:
                currentScreen = new PlayScreenStageOne(game,"maze4.tmx",1);
                game.setScreen(currentScreen);
                break;
            default:
                game.setScreen(new PlayScreenStageOne(game,"maze1.tmx",1));
                currentScreenIndex=1;
                MazeGame.prefs.putInteger("level",currentScreenIndex);
                MazeGame.prefs.flush();
                break;

        }
    }
    public PlayScreen getCurrentScreen(){
        return currentScreen;
    }
}
