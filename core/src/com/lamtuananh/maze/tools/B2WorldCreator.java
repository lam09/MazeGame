package com.lamtuananh.maze.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.player.Enemy.StoneMonster;
import com.lamtuananh.maze.screens.PlayScreen;


/**
 * Created by lamtuananh on 9/11/16.
 */
public class B2WorldCreator {
    PlayScreen screen;
    World world;
    TiledMap map;
    public B2WorldCreator(PlayScreen screen){
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();

        init(world,map);

    }

    BodyDef bdef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fdef = new FixtureDef();
    Body body;
    private void init(World world, TiledMap map) {
        //create body and fixture variables
        initBackgroundObject();
        initEnemies();
     //   initMovingableWall();
    }
    protected void initBackgroundObject(){
        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get("wall").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MazeGame.PPM, (rect.getY() + rect.getHeight() / 2) / MazeGame.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / MazeGame.PPM, rect.getHeight() / 2 / MazeGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
    protected void initMovingableWall(){
        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get("flexiwall").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MazeGame.PPM, (rect.getY() + rect.getHeight() / 2) / MazeGame.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / MazeGame.PPM, rect.getHeight() / 2 / MazeGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
    protected void initEnemies()
    {
        Rectangle start=((RectangleMapObject) map.getLayers().get("start").getObjects().getByType(RectangleMapObject.class).get(0)).getRectangle();
        {
            screen.createPlayer(new Vector2(start.getX()/MazeGame.PPM,start.getY()/MazeGame.PPM));
        }
        for(MapObject object : map.getLayers().get("enemy").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            screen.enemies.add(new StoneMonster(screen,new Vector2(rect.getX()/MazeGame.PPM,rect.getY()/MazeGame.PPM)));
        }
    }

    protected void initPrincess()
    {
        Rectangle start=((RectangleMapObject) map.getLayers().get("end").getObjects().getByType(RectangleMapObject.class).get(0)).getRectangle();
        {
            screen.createPlayer(new Vector2(start.getX()/MazeGame.PPM,start.getY()/MazeGame.PPM));
        }


    }

}
