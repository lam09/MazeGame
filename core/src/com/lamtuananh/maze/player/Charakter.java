package com.lamtuananh.maze.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 4. 11. 2016.
 */
public abstract class Charakter extends Sprite{
    public enum  State{GOLEFT, GORIGHT, GOUP , GODOWN,STANDING,APPEARING,DISAPPEARING};
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;
    protected PlayScreen screen;
    protected Vector2 velocity;
    public float speed = 0;

    public Animation goLeft,goRight,goUp,goDown;
    private float stateTimer;
    public Vector2 positon;
    public TextureRegion playerStand,playerStandLeft,playerStandRight,playerStandUp,playerStandDown;
    public float sizeScale;
    public Charakter(PlayScreen screen,Vector2 postion) {
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();
        this.positon = postion;
        velocity = new Vector2(50,50);
        init();

    }

    protected abstract void init();

    public TextureRegion getFrame(float dt){
        currentState=getState();
        TextureRegion region = null;
        switch (currentState){
            case GOLEFT:
                region=goLeft.getKeyFrame(stateTimer);
                break;
            case GORIGHT:
                region=goRight.getKeyFrame(stateTimer);
                break;
            case GOUP:
                region=goUp.getKeyFrame(stateTimer);
                break;
            case GODOWN:
                region=goDown.getKeyFrame(stateTimer);
                break;
            case DISAPPEARING:
                break;
            case APPEARING:
                break;
            default:
                region = playerStand;
                break;
        }

        stateTimer=currentState==previousState? stateTimer+dt:0;
        previousState = currentState;
        if(previousState == State.GODOWN) playerStand = playerStandDown;
        else  if(previousState == State.GOLEFT) playerStand = playerStandLeft;
        else  if(previousState == State.GORIGHT) playerStand = playerStandRight;
        else if(previousState == State.GOUP) playerStand = playerStandUp;
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

    /**
     * Define physical body for charakter
     * @param position start position of charakter
     */
    public abstract void defineCharakter(Vector2 position);

    /**
     * The simple moving style for characters
     * @param dt
     */
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
        setPosition(b2body.getPosition().x - 24 / MazeGame.PPM,b2body.getPosition().y-24 / MazeGame.PPM);
        setRegion(getFrame(dt));
    }
    protected void goUp()
    {
        velocity = new Vector2(0,speed);
        currentState = State.GOUP;
    }
    protected void goDown()
    {
        velocity = new Vector2(0,-speed);
        currentState = State.GODOWN;

    }
    protected void goRight()
    {
        velocity = new Vector2(speed,0);
        currentState = State.GORIGHT;
    }
    protected void goLeft()
    {
        velocity = new Vector2(-speed,0);
        currentState = State.GOLEFT;
    }protected void stand()
    {
        velocity = new Vector2(0,0);
        currentState = State.STANDING;
    }
    public void draw(Batch batch){
        super.draw(batch);
    }
}
