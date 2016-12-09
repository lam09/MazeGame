package com.lamtuananh.maze.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.lamtuananh.maze.MazeGame;

public class LoadingScreen implements Screen {


    final MazeGame game;
    OrthographicCamera camera;
    Stage stage;
    private static Skin skin;
    Label LoadLabel;
    ProgressBarStyle barStyle;
    ProgressBar bar;
    Sprite BackgroundSprite;
    SpriteBatch batch;

    protected BitmapFont font = null;
    GlyphLayout layout = new GlyphLayout(); //get font width in newer libgdx

    private TextureAtlas initialAtlas = null;
    private Sprite[] circles = null;
    private float[] rotations = null;
    public static LoadingScreen instance = null;
    private boolean isInitialLoading = true;
    private float initialLoadingDuration = 10f;
    private boolean useRealLoadingDuration = true;
    Texture background1;
    protected float time = 0;



    public LoadingScreen(final MazeGame game, Skin skin, BitmapFont font) {
        this.game = game;
        this.skin = skin;
        this.font = font;
        instance = this;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MazeGame.SCREEN_WIDTH, MazeGame.SCREEN_HEIGHT);
 //       Viewport viewport = new FitViewport()
        Texture background = new Texture("loading/LoadBG.png");
         background1 = new Texture("menuBackground.png");

        background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        BackgroundSprite = new Sprite(background, 0, 0, MazeGame.SCREEN_WIDTH, MazeGame.SCREEN_HEIGHT);

        stage = new Stage();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        LoadLabel = new Label("Načítavanie...", skin);
        LoadLabel.setPosition(MazeGame.SCREEN_WIDTH/2-LoadLabel.getWidth()/2, MazeGame.SCREEN_HEIGHT/2+50);
        stage.addActor(LoadLabel);

        Pixmap pixmap = new Pixmap(10, 25, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.WHITE), skin.newDrawable("white", Color.GREEN));
        barStyle.knobBefore = barStyle.knob;
        bar = new ProgressBar(0, 100, 1f, false, barStyle);
        bar.setPosition(MazeGame.SCREEN_WIDTH/4, MazeGame.SCREEN_HEIGHT/2);
        bar.setSize(MazeGame.SCREEN_WIDTH/2, MazeGame.SCREEN_HEIGHT/8);
        bar.setAnimateDuration(0.01f);
        stage.addActor(bar);

        initialAtlas = new TextureAtlas(Gdx.files.internal("loading/initial-loader.atlas"));
        circles = new Sprite[5];
        rotations = new float[5];
        int[] xs = new int[]{755, 699, 645, 592, 540};
        int[] ys = new int[]{271, 218, 165, 112, 60};
        for (int i = 0; i < circles.length; i++) {
            Sprite circle = new Sprite(initialAtlas.findRegion(""+(20 + i*20)));
            circle.setPosition(xs[i], ys[i]);
            circles[i] = circle;
            rotations[i] = MazeGame.RANDOM.nextInt(10) + 1;
        }
        StartLoading();
    }

    public void StartLoading(){
        initialLoadingDuration =10f;
        LoadAssets();
    }

    /**
     * Put Assets to loading here
     */
    public static TmxMapLoader mapLoader;

    private void LoadAssets() {

        MazeGame.loading = this;
        MazeGame.manager.load("loading/uiskin.atlas", TextureAtlas.class);
        MazeGame.manager.load("loading/uiskin.json", Skin.class );
        MazeGame.manager.load("loading/game-loader.atlas", TextureAtlas.class );
        MazeGame.manager.load("loading/initial-loader.atlas", TextureAtlas.class );
        MazeGame.manager.load("loading/loader.atlas", TextureAtlas.class );
        MazeGame.manager.load("loading/default.fnt", BitmapFont.class );

        mapLoader = new TmxMapLoader();
        MazeGame.manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        //MazeGame.manager.load("maze3.tmx", TiledMap.class);
     /*   MazeGame.manager.load("buttons/up.png", Texture.class);
        MazeGame.manager.load("buttons/down.png", Texture.class);
        MazeGame.manager.load("buttons/left.png", Texture.class);
        MazeGame.manager.load("buttons/right.png", Texture.class);
        MazeGame.manager.load("buttons/upDown.png", Texture.class);
        MazeGame.manager.load("buttons/downDown.png", Texture.class);
        MazeGame.manager.load("buttons/leftDown.png", Texture.class);
        MazeGame.manager.load("buttons/rightDown.png", Texture.class);
        MazeGame.manager.load("buttons/attach.png", Texture.class);
        MazeGame.manager.load("buttons/attachDown.png", Texture.class);*/
        MazeGame.manager.load("buttons/buttons.atlas",TextureAtlas.class);
        MazeGame.manager.load("font/showcard20.fnt",BitmapFont.class);

        MazeGame.manager.load("tortoise/goDown3.png", Texture.class);
        MazeGame.manager.load("tortoise/goLeft.png", Texture.class);
        MazeGame.manager.load("tortoise/goRight.png", Texture.class);
        MazeGame.manager.load("tortoise/goUp.png", Texture.class);
        MazeGame.manager.load("enemy/stone/Stone.png", Texture.class);
      //  MazeGame.manager.load("wall.png", Texture.class);
        MazeGame.manager.load("flexiwall.png", Texture.class);
        MazeGame.manager.load("uiskin.json", Skin.class);
        MazeGame.manager.load("maintenance.atlas",TextureAtlas.class);
        MazeGame.manager.load("menuBackground.png", Texture.class);
        MazeGame.manager.load("default.fnt",BitmapFont.class);
        MazeGame.manager.load("sound/background.mp3", Music.class);
        MazeGame.manager.load("enemy/a/a.png", Texture.class);
        // stage maps load
        MazeGame.manager.load("maze1.tmx", TiledMap.class);
        MazeGame.manager.load("maze2.tmx", TiledMap.class);
        MazeGame.manager.load("maze3.tmx", TiledMap.class);
        MazeGame.manager.load("maze4.tmx", TiledMap.class);

        for(int i = 1 ; i<13; i++)
            MazeGame.manager.load("enemy/stone/"+i+".png", Texture.class);


        MazeGame.doneLoading = LoadingStatus.STARTED;
    }

    /**
     * Writes text on screen.
     *
     * @param text
     * @param x center point
     * @param y center point
     */
    protected void writeToCenter(String text, float x, float y) {
        layout.setText(font,text);
        font.draw(batch, text, x - layout.width / 2, y + layout.height / 2);
    }

    /**
     * Render progress of loading
     * @param delta - gdx delta time
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background1, 0, 0,MazeGame.SCREEN_WIDTH,MazeGame.SCREEN_HEIGHT);
      //  batch.draw(BackgroundSprite, 0, 0,MazeGame.SCREEN_WIDTH*2,MazeGame.SCREEN_HEIGHT*2);

       // batch.draw(initialAtlas.findRegion("Logo"), 632, 663);

        //camera.update();

/*        if(MyGame.doneLoading == LoadingStatus.NOT_STARTED){
            writeToCenter("Trying to connect to server", 841, 50);
            batch.end();
            return;
        }
*/
        batch.end();

        if (MazeGame.doneLoading != LoadingStatus.DONE) {
            if (MazeGame.manager.update()) {
                MazeGame.doneLoading = LoadingStatus.DONE;

            }
        }

        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            time = 0;
        }

        batch.begin();

        float duration = 0;

        float percent = 0;

        if (useRealLoadingDuration) {
            percent = MazeGame.manager.getProgress()*100;
        } else {
            if (isInitialLoading) {
                duration = initialLoadingDuration;
            } else {
               // duration = gameLoadingDuration[loadGameIndex];
            }
            percent = time/duration*100;
        }

     /*   for (int i = 0; i < circles.length; i++) {
            Sprite circle = circles[i];
            float progress = Math.min(percent/(20*(i + 1)), 1);
            float start = 90;
            if (i % 2 == 1) {
                progress *= -1;
                start += 180;
            }
            float rotation = 1.25f;
            circle.setRotation(start + rotation*360*progress);
            circle.draw(batch);

        }*/

       // int max100percent = Math.min(Math.round(percent), 100);
       // writeToCenter(max100percent+" %", 841, 360);
        bar.act(delta);
        bar.setValue(percent);
        bar.draw(batch,1f);
        batch.end();

        if (percent >= 100) {
            if (MazeGame.doneLoading != LoadingStatus.DONE) {
                //logger.debug("Loading didn't finish in {}, waiting for it to finish", time);
                MazeGame.manager.finishLoading();
            }
            //logger.debug("Loading finished", time);
            if (isInitialLoading) {
                isInitialLoading = false;
                finishInitialLoading();
                //			return;
            } else {
                finishLoadingGame();
//				return;
            }
        }
        time += delta;
    }

    /**
     * will be ran when loading finished
     */
    private void finishLoadingGame() {
        // it is important the GameGreen gets new instance when assets are reloaded
       // MazeGame.menu = new MenuScreen();
       // MazeGame.instance.setScreen(MazeGame.menu);

    }

    private void finishInitialLoading() {
        game.finishedLoading();
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
        stage.dispose();
        batch.dispose();
    }

    public enum LoadingStatus {
        NOT_STARTED,
        STARTED,
        DONE
    }

}
