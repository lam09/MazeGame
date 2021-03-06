package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.player.Charakter;
import com.lamtuananh.maze.player.Player;
import com.lamtuananh.maze.tools.B2WorldCreator;
import com.lamtuananh.maze.tools.ControlButtons;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class PlayScreen  implements Screen {
    MazeGame game;
    public Integer index ;
    protected OrthographicCamera gamecam;
    protected Viewport gamePort;

    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;


    protected World world;
    protected Box2DDebugRenderer b2dr;
    protected B2WorldCreator creator;

    protected Player player;
    public Array<Charakter> enemies = new Array<Charakter>();
   // public Array<Wall> walls = new Array<Wall>();
    Stage stage;

    public Music background;

    protected String mapName;
    private boolean isEnd = false;
    private boolean reseting = false;
    private Sprite circleButton;
    public PlayScreen(MazeGame game,String mapName,Integer index){
        System.out.print("Starting playing screen......");
        this.index=index;
        this.game = game;
        this.mapName = mapName;
        gamecam = new OrthographicCamera();
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(Gdx.app.getGraphics().getWidth()/MazeGame.ZOOM/ MazeGame.PPM,Gdx.app.getGraphics().getHeight()/MazeGame.ZOOM/ MazeGame.PPM, gamecam);
        init();
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MazeGame.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        initWorld();
        initButton();

    }

    private void initButton() {
        //stage = new Stage();
        //controlButtons = new ControlButtons(stage);
        stage = ControlButtons.getInstance().getStage();
        Gdx.input.setInputProcessor(stage);
        circleButton = new Sprite(MazeGame.manager.get("buttons/circleButton.png",Texture.class));
        circleButton.scale(1/MazeGame.PPM);
    }

    public void initWorld()
    {
    }
    public void createPlayer(Vector2 position)
    {
        player = new Player(this,position);
    }
    public  void init()
    {
    }

    @Override
    public void show() {

    }
    float zoom = 2f;
    public void update (float delta){
        world.step(1 / 60f, 6, 2);
        //update our gamecam with correct coordinates after changes
        if(reseting)
        {
            if(zoom<2f)zoom+=0.01f;
            else MazeGame.mng.setCurrentScreen();

           // gamecam.zoom = zoom;

        }
        else
        if(!isEnd) {
            if (zoom > 1f) zoom -= 0.01f;
           // gamecam.zoom = zoom;
        }
        else
        {
            if(zoom<2f) zoom+=0.01f;
            else {
            MazeGame.mng.setNextScreen();
            }
           // gamecam.zoom = zoom;
        }
        gamecam.position.set(player.getX(),player.getY(),0);
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(gamecam);

        //Player moving
        player.update(delta);
       // circleButton.setPosition(player.getX(),player.getY());
        //Enemy update
        for (Charakter charakter: enemies)
            charakter.update(delta);
    }
    @Override
    public void render(float delta) {
      // System.out.print( Gdx.app.getGraphics().getWidth() +" " + Gdx.app.getGraphics().getHeight());
               update(delta);
       // map.getLayers().get("background").
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
        renderItem(game.batch,delta);
//        game.batch.draw(circleButton,player.getPosition().x,player.getPosition().y, 10,10 );
        game.batch.draw(circleButton,player.getPosition().x,player.getPosition().y, 1,1 );

        game.batch.end();
        stage.act();
        stage.draw();
    }

    public void renderItem(SpriteBatch batch, float delta) {
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

    public Body b2body;

    public void endSecction(Rectangle rect) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / MazeGame.PPM, (rect.getY() + rect.getHeight() / 2) / MazeGame.PPM);
        b2body = world.createBody(bdef);
        shape.setAsBox(rect.getWidth() / 2 / MazeGame.PPM, rect.getHeight() / 2 / MazeGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = MazeGame.END;
        fdef.filter.maskBits = MazeGame.GROUND_BIT|MazeGame.ENEMY_BIT |MazeGame.PLAYER_BIT;
        b2body.createFixture(fdef);
    }
    public void reset(){
        reseting = true;
        player.die();
    }
    public void ending()
    {
        isEnd = true;
        player.gotoNextScreen();
    }
    public Player getPlayer()
    {
        return player;
    }

    public Vector2 getPlayerPosition()
    {
        return new Vector2(player.getX(),player.getY());
    }
}
