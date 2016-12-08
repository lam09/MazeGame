package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.tools.CustomButton;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class MenuScreen implements Screen{

    SpriteBatch batch;

    CustomButton newGame, continueGame;

    public MenuScreen(){
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) MazeGame.mng.setNextScreen();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
