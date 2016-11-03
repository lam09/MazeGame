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
    TextureRegion playerStand,playerStandLeft,playerStandRight,playerStandUp,playerStandDown;
    public Player(PlayScreen screen) {
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();
        definePlayer(screen.startPosition);
        setBounds(0, 0, 48 / MazeGame.PPM, 48 / MazeGame.PPM);
        velocity = new Vector2(50,50);
        speed = MovingSpeed.WALKING;

        Texture goLeftTexture = MazeGame.manager.get("tortoise/goLeft.png", Texture.class);

        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(goLeftTexture,0,0,35,49));
        frames.add(new TextureRegion(goLeftTexture,45,0,35,49));
        frames.add(new TextureRegion(goLeftTexture,94,0,35,49));
        frames.add(new TextureRegion(goLeftTexture,142,0,35,49));
        goLeft = new Animation(0.2f,frames);
        goLeft.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Texture goDownTexture = MazeGame.manager.get("tortoise/goDown3.png", Texture.class);
        Array<TextureRegion> frames1 = new Array<TextureRegion>();
        frames1.add(new TextureRegion(goDownTexture,0,0,40,49));
        frames1.add(new TextureRegion(goDownTexture,47,0,40,49));
        frames1.add(new TextureRegion(goDownTexture,95,0,40,49));
        frames1.add(new TextureRegion(goDownTexture,145,0,40,49));
        goDown = new Animation(0.2f,frames1);
        goDown.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Texture goRightTexture = MazeGame.manager.get("tortoise/goRight.png", Texture.class);
        Array<TextureRegion> frames2 = new Array<TextureRegion>();
        frames2.add(new TextureRegion(goRightTexture,0,0,37,49));
        frames2.add(new TextureRegion(goRightTexture,47,0,37,49));
        frames2.add(new TextureRegion(goRightTexture,96,0,37,49));
        frames2.add(new TextureRegion(goRightTexture,144,0,37,49));
        goRight = new Animation(0.2f,frames2);
        goRight.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Texture goUpTexture = MazeGame.manager.get("tortoise/goUp.png", Texture.class);
        Array<TextureRegion> frames3 = new Array<TextureRegion>();
        frames3.add(new TextureRegion(goUpTexture,0,0,40,49));
        frames3.add(new TextureRegion(goUpTexture,46,0,40,49));
        frames3.add(new TextureRegion(goUpTexture,94,0,40,49));
        frames3.add(new TextureRegion(goUpTexture,146,0,40,49));
        goUp = new Animation(0.2f,frames3);
        goUp.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        playerStand = new TextureRegion(goLeftTexture, 0, 0, 35, 49);
        playerStandLeft= new TextureRegion(goLeftTexture, 0, 0, 35, 49);
        playerStandRight= new TextureRegion(goRightTexture, 0,0,37,49);
        playerStandUp= new TextureRegion(goUpTexture, 0,0,40,49);
        playerStandDown= new TextureRegion(goDownTexture, 0,0,40,49);

        setRegion(playerStand);
    }
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
        setPosition(b2body.getPosition().x - 24 / MazeGame.PPM,b2body.getPosition().y-24 / MazeGame.PPM);
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
