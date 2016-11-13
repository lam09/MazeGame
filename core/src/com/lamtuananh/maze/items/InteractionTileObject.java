package com.lamtuananh.maze.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 23. 6. 2016.
 */
public abstract class InteractionTileObject extends Sprite{
    protected TiledMap map;
    protected TiledMapTile tile;
   // protected Body body;
    public Fixture fixture;


    protected PlayScreen screen;
    protected String name;
    protected Vector2 position = new Vector2();
    protected Vector2 bounds;
    public World world;
    public Body b2body;

    public InteractionTileObject(PlayScreen screen)
    {
        this.screen = screen;
        this.world =screen.getWorld();
    }

    protected abstract void init();
    public abstract void update(float delta);
    protected abstract void render(float delta);

 /*   public InteractionTileObject(World world, TiledMap map, Rectangle bounds)
    {
        this.world=world;
        this.map=map;
        this.bounds=bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/ MazeGame.PPM,(bounds.getY()+bounds.getHeight()/2)/MazeGame.PPM);

        b2body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth()/2/MazeGame.PPM,bounds.getHeight()/2/MazeGame.PPM);
        fdef.shape = shape;
        fixture = b2body.createFixture(fdef);
    }
*/
    public abstract void onHeadHit();
  /*  public void setCatagoryBit(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(b2body.getPosition().x* MazeGame.PPM / 16),(int)(b2body.getPosition().y*MazeGame.PPM/16));
    }*/
}
