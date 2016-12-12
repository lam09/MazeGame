package com.lamtuananh.maze.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.items.InteractionTileObject;

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
                //game.mng.setCurrentScreen();
                game.mng.getCurrentScreen().reset();
                //game.mng.resetStage();
                break;
            case MazeGame.FLEXIWALL_BIT|MazeGame.STONE_BIT:
                System.out.print("flexi hit the stone");

                break;
            case MazeGame.FLEXIWALL_BIT|MazeGame.PLAYER_BIT:
                System.out.print("flexi hit the playerrrrrrrrrrrr");

                break;
            case MazeGame.FLEXIWALL_BIT|MazeGame.GROUND_BIT:
                System.out.print("Heloo wall");
                if(fixA.getUserData() instanceof InteractionTileObject)
                {
                    ((InteractionTileObject) fixA.getUserData()).onHeadHit();
                }
                if(fixB.getUserData() instanceof InteractionTileObject)
                {
                    ((InteractionTileObject) fixB.getUserData()).onHeadHit();
                }
                break;
            case MazeGame.PLAYER_BIT|MazeGame.END:
                MazeGame.mng.getCurrentScreen().ending();

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
