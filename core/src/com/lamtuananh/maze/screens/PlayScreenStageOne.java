package com.lamtuananh.maze.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.tools.B2WorldCreator;
import com.lamtuananh.maze.tools.WorldContactListener;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 */
public class PlayScreenStageOne extends PlayScreen {
    public PlayScreenStageOne(MazeGame game,String mapName) {
        super(game,mapName);
    }

    @Override
    public void init() {
        System.out.print(mapName);
        map = MazeGame.manager.get(""+mapName);
    }
    @Override
    public void initWorld() {
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);
        world.setContactListener(new WorldContactListener(game));    }
}
