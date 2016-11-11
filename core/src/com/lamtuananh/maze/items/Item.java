package com.lamtuananh.maze.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.lamtuananh.maze.screens.PlayScreen;

/**
 * Created by a.lam.tuan on 11. 11. 2016.
 */
public abstract class Item extends Sprite{
    protected PlayScreen screen;
    protected String name;
    protected Vector2 position;
    protected Vector2 bounds;
    public World world;
    public Body b2body;

    public Item(PlayScreen screen)
    {
        this.screen = screen;
        this.world =screen.getWorld();

    }

    protected abstract void init();
    public abstract void update(float delta);
    protected abstract void render(float delta);
}
