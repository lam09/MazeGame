package com.lamtuananh.maze.items;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
public class Wall extends InteractionTileObject{
    public Vector2 velocity ;
    //public Vector2 position = new Vector2();
    Rectangle rect;
    Texture texture;
    TextureRegion region;
    public Wall(PlayScreen screen, Rectangle rect)
    {

        super(screen);
        if(rect.getWidth()>rect.getHeight()) velocity=new Vector2(0.5f,0);
        else velocity=new Vector2(0,0.5f);
        this.rect = rect;
        init();
//        texture = MazeGame.manager.get("wall.png", Texture.class);
        texture = MazeGame.manager.get("flexiwall.png", Texture.class);
        region = new TextureRegion(texture,0,0,(int)rect.getWidth(),(int)rect.getHeight());
        System.out.println(this.rect.getWidth() + " " + this.rect.getHeight());
        setBounds(0,0,rect.getWidth()/MazeGame.PPM,rect.getHeight()/MazeGame.PPM);
        setRegion(region);
        setPosition(rect.getX(),rect.getY());
        if(rect.getWidth()>rect.getHeight()) velocity=new Vector2(5,0);

        fixture.setUserData(this);
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
        fdef.density = 1000000f;
        fdef.friction=1f;
        fdef.restitution = 0f;
        fdef.filter.categoryBits = MazeGame.FLEXIWALL_BIT;
        fdef.filter.maskBits =  MazeGame.GROUND_BIT|MazeGame.STONE_BIT|MazeGame.PLAYER_BIT;
        fixture = b2body.createFixture(fdef);
        //fixture.setUserData("flexiwall");
        //System.out.print("init walll");

    }

    @Override
    public void update(float delta) {
        b2body.setLinearVelocity(velocity);
        b2body.setAngularVelocity(0f);
        position.x = b2body.getPosition().x - rect.getWidth()/MazeGame.PPM/2;
        position.y = b2body.getPosition().y - rect.getHeight()/MazeGame.PPM/2;
        setPosition(position.x,position.y);
        setRegion(region);
    }

    public void goBack()
    {
        velocity.x *= -1;
        velocity.y *= -1;
    }
    public void render(float delta)
    {

    }

    @Override
    public void onHeadHit() {
        goBack();
        System.out.println("wall hit to wall");
    }

    public void draw(Batch batch){
       super.draw(batch);
       /* Sprite s = new Sprite(region);
        s.scale(1f/16f/MazeGame.PPM );
       // batch.draw(texture,0,0);
        s.draw(batch);*/
    }

}
