package com.lamtuananh.maze.items;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 8. 11. 2016.
 */
public class Wall extends Item{
    public Vector2 velocity;
    public Vector2 position = new Vector2();
    Rectangle rect;
    Texture texture;
    public Wall(PlayScreen screen, Rectangle rect)
    {
        super(screen);
        this.rect = rect;
        init();
        texture = MazeGame.manager.get("flexiwall.png", Texture.class);
        setTexture(texture);

        setBounds(0,0,rect.getWidth()/MazeGame.PPM,rect.getHeight()/MazeGame.PPM);
        //setRegion(0,0,rect.getWidth(),rect.getHeight());
        setPosition(rect.getX(),rect.getY());
    }


    @Override
    protected void init() {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / MazeGame.PPM, (rect.getY() + rect.getHeight() / 2) / MazeGame.PPM);
        b2body = world.createBody(bdef);
        shape.setAsBox(rect.getWidth() / 2 / MazeGame.PPM, rect.getHeight() / 2 / MazeGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    @Override
    public void update(float delta) {
        if(rect.getWidth()<rect.getHeight()) velocity = new Vector2(0,0.1f);
        else velocity = new Vector2(0.1f,0);
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - 24 / MazeGame.PPM,b2body.getPosition().y-24 / MazeGame.PPM);
        position = b2body.getPosition();
    }

    public void render(float delta)
    {

    }
    public void draw(Batch batch){
        super.draw(batch);
//        batch.draw(texture,position.x,position.y,0,0,(int)rect.getWidth(),(int)rect.getHeight());
    }

}
