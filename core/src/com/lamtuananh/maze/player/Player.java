package com.lamtuananh.maze.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class Player extends Sprite {
    public World world;
    public Body b2body;
    private PlayScreen screen;
    private Vector2 velocity;
    float speed = 0;

    public Player(PlayScreen screen) {
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();
        definePlayer(new Vector2(5,5));
        setBounds(0, 0, 16 / MazeGame.PPM, 16 / MazeGame.PPM);
        velocity = new Vector2(50,50);
        speed = MovingSpeed.WALKING;
    }


    public void definePlayer(Vector2 position){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        //    bdef.position.set(MarioBros.V_WIDTH/2,MarioBros.V_HEIGHT/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(16 / MazeGame.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = MazeGame.PLAYER_BIT;
        fdef.filter.maskBits = MazeGame.GROUND_BIT ;

        b2body.createFixture(fdef).setUserData(this);
    }
    public void update(float dt) {

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            //System.out.print("go left");
            velocity.x =-speed;
        }
        else
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            //System.out.print("go right");
            velocity.x = speed;
        }
        else
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.y = speed;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) velocity.y = -speed;
        else velocity = new Vector2(0,0);
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x,b2body.getPosition().y);
    }
    public void draw(Batch batch){
        super.draw(batch);
    }
}
