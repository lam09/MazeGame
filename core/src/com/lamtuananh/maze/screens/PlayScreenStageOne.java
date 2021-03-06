package com.lamtuananh.maze.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.items.InteractionTileObject;
import com.lamtuananh.maze.items.Wall;
import com.lamtuananh.maze.tools.B2WorldCreator;
import com.lamtuananh.maze.tools.WorldContactListener;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 */
public class PlayScreenStageOne extends PlayScreen {
    private Array<InteractionTileObject> walls;// = new Array<Wall>();

    public PlayScreenStageOne(MazeGame game,String mapName,Integer index) {
        super(game,mapName,index);
    }

    @Override
    public void init() {
        System.out.print(mapName);
        map = MazeGame.manager.get(""+mapName);
        MazeGame.soundManager.playBackgroundMusic();
    }
    @Override
    public void initWorld() {
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);
        world.setContactListener(new WorldContactListener(game));
        initWalls();
    }

    private void initWalls() {
        walls = new Array<InteractionTileObject>();
        //if(map.getLayers().get("flexiwall").isVisible())
        for(MapObject object : map.getLayers().get("flexiwall").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            walls.add(new Wall(this,rect));
        }

    }
    @Override
    public void update (float delta) {
       // System.out.print("Iam herere");
        super.update(delta);
        for(InteractionTileObject w:walls)
            w.update(delta);
    }
    @Override
    public void renderItem(SpriteBatch batch, float delta) {
        for(InteractionTileObject w:walls)
        {
            w.draw(batch);
        }
    }



}
