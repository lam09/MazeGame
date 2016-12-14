package com.lamtuananh.maze.player.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.player.MovingSpeed;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 9. 12. 2016.
 */
public class Monster extends StoneMonster {

    public Monster(PlayScreen screen, Vector2 position) {
        super(screen, position);
    }
    @Override
    protected void init() {
        defineCharakter(positon);
        setBounds(0, 0, 48 / MazeGame.PPM, 48 / MazeGame.PPM);
        velocity = new Vector2(50,50);
        speed = MovingSpeed.MONSTER_WALKING;
        Texture texture = MazeGame.manager.get("enemy/a/b.png", Texture.class);

        Array<TextureRegion> frameAtlas = new Array<TextureRegion>();
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(texture,0,0,80,90));
        frames.add(new TextureRegion(texture,80,0,80,90));
        frames.add(new TextureRegion(texture,160,0,80,90));

        goLeft = new Animation(0.2f,frames);
        goLeft.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames1 = new Array<TextureRegion>();
        frames1.add(new TextureRegion(texture,0,100,80,90));
        frames1.add(new TextureRegion(texture,80,100,80,90));
        frames1.add(new TextureRegion(texture,160,100,80,90));
        goDown = new Animation(0.2f,frames1);
        goDown.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames2 = new Array<TextureRegion>();
        frames2.add(new TextureRegion(texture,0,200,80,90));
        frames2.add(new TextureRegion(texture,80,200,80,90));
        frames2.add(new TextureRegion(texture,160,200,80,90));
        goRight = new Animation(0.2f,frames2);
        goRight.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        Array<TextureRegion> frames3 = new Array<TextureRegion>();
        frames3.add(new TextureRegion(texture,0,300,80,90));
        frames3.add(new TextureRegion(texture,80,300,80,90));
        frames3.add(new TextureRegion(texture,160,300,80,90));
        goUp = new Animation(0.2f,frames3);
        goUp.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        playerStand = new TextureRegion(frames.get(0));
        playerStandLeft= new TextureRegion(frames.get(0));
        playerStandRight= new TextureRegion(frames.get(0));
        playerStandUp= new TextureRegion(frames.get(0));
        playerStandDown= new TextureRegion(frames.get(0));

        setRegion(playerStand);
    }

}
