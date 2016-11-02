package com.lamtuananh.maze.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class Player extends Sprite {

    public enum  State{GOLEFT, GORIGHT, GOUP , GODOWN,STANDING};
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;
    private PlayScreen screen;
    private Vector2 velocity;
    float speed = 0;

    Animation goLeft,goRight,goUp,goDown;
    private float stateTimer;
    TextureRegion playerStand;
    public Player(PlayScreen screen) {
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();
        definePlayer(new Vector2(5,5));
        setBounds(0, 0, 16 / MazeGame.PPM, 16 / MazeGame.PPM);
        velocity = new Vector2(50,50);
        speed = MovingSpeed.WALKING;

        Texture goLeftTexture = MazeGame.manager.get("tortoise/goLeft.png", Texture.class);

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i=0;i<4;i++)
            frames.add(new TextureRegion(goLeftTexture,i*46.5f,49,46.5f,49));
        goLeft = new Animation(0.1f,frames);
         playerStand = new TextureRegion(goLeftTexture, 0, 49, 46.5f, 49);
        setRegion(frames.get(0));
    }
    public TextureRegion getFrame(float dt){
        currentState=getState();
        TextureRegion region = null;
        switch (currentState){
            case GOLEFT:
                region=goLeft.getKeyFrame(stateTimer);
                break;
            case GORIGHT:
                break;
            case GOUP:
                break;
            case GODOWN:
                break;
            default:
                region = playerStand;
                break;
        }

        stateTimer=currentState==previousState? stateTimer+dt:0;
        previousState = currentState;
        return region;
    }
    public State getState(){
        if(b2body.getLinearVelocity().y>0 )
            return  State.GOUP;
        if(b2body.getLinearVelocity().y<0)
            return State.GODOWN;
        else if(b2body.getLinearVelocity().x > 0)
            return State.GORIGHT;
        else
        if(b2body.getLinearVelocity().x < 0)
            return State.GOLEFT;
        else return State.STANDING;
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
           goLeft();
        }
        else
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            goRight();
        }
        else
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            goUp();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) goDown();
        else velocity = new Vector2(0,0);
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x,b2body.getPosition().y);
        setRegion(getFrame(dt));
    }
    private void goUp()
    {
        velocity = new Vector2(0,speed);
        currentState = State.GOUP;
    }
    private void goDown()
    {
        velocity = new Vector2(0,-speed);
        currentState = State.GODOWN;

    }
    private void goRight()
    {
        velocity = new Vector2(speed,0);
        currentState = State.GORIGHT;
    }
    private void goLeft()
    {
        velocity = new Vector2(-speed,0);
        currentState = State.GOLEFT;
    }
    public void draw(Batch batch){
        super.draw(batch);
    }
}
