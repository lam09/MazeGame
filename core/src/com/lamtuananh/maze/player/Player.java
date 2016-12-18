package com.lamtuananh.maze.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.screens.PlayScreen;
import com.lamtuananh.maze.tools.CircleButton;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class Player extends Charakter {


    private boolean isEnd = false;
    private boolean isDead = false;

    public Player(PlayScreen screen, Vector2 position) {
        super(screen,position);

    }
    @Override
    public void init()
    {
        sizeScale = 48;

        defineCharakter(positon);
        setBounds(0, 0, sizeScale / MazeGame.PPM, sizeScale / MazeGame.PPM);

        speed = MovingSpeed.PLAYER_WALKING;

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
        Array<TextureRegion> framesdead = new Array<TextureRegion>();
        framesdead.add(frames.get(0));
        framesdead.add(frames1.get(0));
        framesdead.add(frames2.get(0));
        framesdead.add(frames3.get(0));
        dead = new Animation(0.1f,framesdead);
        dead.setPlayMode(Animation.PlayMode.LOOP);

        playerStand = new TextureRegion(goLeftTexture, 0, 0, 35, 49);
        playerStandLeft= new TextureRegion(goLeftTexture, 0, 0, 35, 49);
        playerStandRight= new TextureRegion(goRightTexture, 0,0,37,49);
        playerStandUp= new TextureRegion(goUpTexture, 0,0,40,49);
        playerStandDown= new TextureRegion(goDownTexture, 0,0,40,49);

        setRegion(playerStand);
    }
    @Override
    public void defineCharakter(Vector2 position){
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        //    bdef.position.set(MarioBros.V_WIDTH/2,MarioBros.V_HEIGHT/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(sizeScale/3 / MazeGame.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = MazeGame.PLAYER_BIT;
        fdef.filter.maskBits =MazeGame.END |
                MazeGame.GROUND_BIT | MazeGame.PLAYER_BIT |MazeGame.ENEMY_BIT |MazeGame.FLEXIWALL_BIT;
        fixture = b2body.createFixture(fdef);
        fixture.setUserData(this);
    }

    float beta = 0,alpha = 0f;
    @Override
    protected void movingUpdate(float dt) {
        super.movingUpdate(dt);
        if(isDead) {
           if(alpha<90) alpha+=1.5f;
           /* setOriginCenter();
            setRotation(alpha);*/
        }
        velocity = new Vector2(0,0);
        if(isDead) return;
        if(CircleButton.getInstance().isUp()) goUp(); else notGoUp();
        if(CircleButton.getInstance().isDown()) goDown(); else notGoDown();
        if(CircleButton.getInstance().isLeft()) goLeft(); else notGoLeft();
        if(CircleButton.getInstance().isRight()) goRight();else notGoRight();


      /*  if(ControlButtons.getInstance().left.isOvered()|| Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            goLeft();
        }
        else
        if(ControlButtons.getInstance().right.isOvered()||Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            goRight();
        }

        if(ControlButtons.getInstance().up.isOvered()||Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            goUp();
        }else
        if(ControlButtons.getInstance().down.isOvered()||Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            goDown();
        }*/
        a-=dt;
        if(/*a>0||*/isEnd)goRight();

        //  velocity = new Vector2(0,0);
    }

    private void notGoUp() {
       // if(velocity.y>0)velocity.y=0;
    }
    private void notGoDown() {
        //if(velocity.y<0)velocity.y=0;
    }
    private void notGoLeft() {
        //if(velocity.x <0) velocity.x = 0;
    }
    private void notGoRight() {
        //if(velocity.x>0) velocity.x = 0;
    }

    float a = 2.5f;
    public void gotoNextScreen()
    {
        isEnd = true;
    }

    public void setPosition(Vector2 position)
    {

    }
    public Vector2 getPosition()
    {
        return new Vector2(b2body.getPosition().x ,b2body.getPosition().y);
    }
    @Override
    public State getState() {
        if(isDead) return State.DEAD;
        return super.getState();
    }

    public void die() {

        isDead = true;
    }
}
