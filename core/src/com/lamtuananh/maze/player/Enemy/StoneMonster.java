package com.lamtuananh.maze.player.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.player.Charakter;
import com.lamtuananh.maze.player.MovingSpeed;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 4. 11. 2016.
 */
public class StoneMonster extends Charakter {
    public StoneMonster(PlayScreen screen,Vector2 position){
        super(screen,position);
    }
    @Override
    protected void init() {
        defineCharakter(positon);
        setBounds(0, 0, 48 / MazeGame.PPM, 48 / MazeGame.PPM);
        velocity = new Vector2(50,50);
        speed = MovingSpeed.SLOWING;
        //Texture texture = MazeGame.manager.get("enemy/stone/Stone.png", Texture.class);
        Array<TextureRegion> frameAtlas = new Array<TextureRegion>();
        for(int i=1;i<13;i++)
                frameAtlas.add(new TextureRegion(MazeGame.manager.get("enemy/stone/"+i+".png", Texture.class)));
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(frameAtlas.get(6));
        frames.add(frameAtlas.get(7));
        frames.add(frameAtlas.get(8));
        frames.add(frameAtlas.get(9));
        frames.add(frameAtlas.get(10));
        frames.add(frameAtlas.get(11));
        goLeft = new Animation(0.2f,frames);
        goLeft.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames1 = new Array<TextureRegion>();
        frames1.add(frameAtlas.get(5));
        frames1.add(frameAtlas.get(4));
        frames1.add(frameAtlas.get(3));
        frames1.add(frameAtlas.get(2));
        frames1.add(frameAtlas.get(1));
        frames1.add(frameAtlas.get(0));
        goDown = new Animation(0.2f,frames1);
        goDown.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames2 = new Array<TextureRegion>();
        frames2.add(frameAtlas.get(11));
        frames2.add(frameAtlas.get(10));
        frames2.add(frameAtlas.get(9));
        frames2.add(frameAtlas.get(8));
        frames2.add(frameAtlas.get(7));
        frames2.add(frameAtlas.get(6));
        goRight = new Animation(0.2f,frames2);
        goRight.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames3 = new Array<TextureRegion>();
        frames3.add(frameAtlas.get(0));
        frames3.add(frameAtlas.get(1));
        frames3.add(frameAtlas.get(2));
        frames3.add(frameAtlas.get(3));
        frames3.add(frameAtlas.get(4));
        frames3.add(frameAtlas.get(5));
        goUp = new Animation(0.2f,frames3);
        goUp.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        playerStand = new TextureRegion(frameAtlas.get(0));
        playerStandLeft= new TextureRegion(frameAtlas.get(0));
        playerStandRight= new TextureRegion(frameAtlas.get(0));
        playerStandUp= new TextureRegion(frameAtlas.get(0));
        playerStandDown= new TextureRegion(frameAtlas.get(0));

        setRegion(playerStand);
    }

    @Override
    public void defineCharakter(Vector2 position) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        //    bdef.position.set(MarioBros.V_WIDTH/2,MarioBros.V_HEIGHT/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(24 / MazeGame.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = MazeGame.STONE_BIT;
        fdef.filter.maskBits = MazeGame.GROUND_BIT | MazeGame.PLAYER_BIT |MazeGame.STONE_BIT;

        b2body.createFixture(fdef).setUserData(this);
    }
    @Override
    public void update(float delta)
    {
        //super.update(delta);
        if(currentState == State.GODOWN ) goDown();
        if(currentState == State.GOLEFT ) goLeft();
        if(currentState == State.GORIGHT ) goRight();
        if(currentState == State.GOUP ) goUp();
        if(currentState == State.STANDING ) stand();
        if(MathUtils.random(0,5000)>4950) {
            int i = MathUtils.random(1,5);
            if(i==1) goDown();
            if(i==2) goLeft();
            if(i==3) goRight();
            if(i==4) goUp();
            if(i==5) stand();
        }
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - 24 / MazeGame.PPM,b2body.getPosition().y-24 / MazeGame.PPM);
        setRegion(getFrame(delta));
    }
}
