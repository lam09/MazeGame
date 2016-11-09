package com.lamtuananh.maze.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by brentaureli on 9/4/15.
 */
public class WorldContactListener implements ContactListener {
    MazeGame game;
    public WorldContactListener(MazeGame game)
    {
        this.game = game;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef){
            case MazeGame.PLAYER_BIT|MazeGame.STONE_BIT:
                game.mng.setCurrentScreen();
                break;
            default:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
