package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.player.Charakter;
import com.lamtuananh.maze.player.Player;
import com.lamtuananh.maze.tools.B2WorldCreator;
import com.lamtuananh.maze.tools.ControlButtons;
import com.lamtuananh.maze.tools.WorldContactListener;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class PlayScreen  implements Screen {
    MazeGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Player player;
    public Vector2 startPosition;

    public Array<Charakter> enemies = new Array<Charakter>();
    Skin skin;
    ControlButtons controlButtons;

    public PlayScreen(MazeGame game){
        System.out.print("Starting playing screen......");
        this.game = game;
        gamecam = new OrthographicCamera();
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(800/ MazeGame.PPM,500/ MazeGame.PPM, gamecam);

        map = MazeGame.manager.get("maze1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MazeGame.PPM);
        System.out.print(gamePort.getWorldWidth() / 2 +" "+ gamePort.getWorldHeight() / 2);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);


        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);
        world.setContactListener(new WorldContactListener());
    }

    public void createPlayer(Vector2 position)
    {
        player = new Player(this,position);
    }


    @Override
    public void show() {

    }

    public void update (float delta){
        world.step(1 / 60f, 6, 2);
        //update our gamecam with correct coordinates after changes
        gamecam.position.set(player.getX(),player.getY(),0);
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(gamecam);

        //Player moving
        player.update(delta);

        //Enemy update
        for (Charakter charakter: enemies)
            charakter.update(delta);
    }
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);


        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        //render our game map
        renderer.render();
        game.batch.begin();
        player.draw(game.batch);
        for (Charakter charakter: enemies)
            charakter.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
}
