package com.lamtuananh.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.lamtuananh.maze.screens.LoadingScreen;
import com.lamtuananh.maze.screens.MenuScreen;
import com.lamtuananh.maze.screens.PlayScreenManager;
import com.lamtuananh.maze.screens.PlayScreenStageOne;

import java.util.Random;

public class MazeGame extends Game {
	public static MazeGame instance;

	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	public static  int OFFSET = 25;
	public static  float ZOOM = 1.5f;

	public static final float PPM = 50;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short STONE_BIT = 4;
	public static final short FLEXIWALL_BIT = 8;
	public static final short END = 16;


	public static int WIDTH;// = Gdx.app.getGraphics().getWidth();
	public static int HEIGHT;// = Gdx.app.getGraphics().getHeight();
//loading screen
	public static LoadingScreen.LoadingStatus doneLoading = LoadingScreen.LoadingStatus.NOT_STARTED;
	public static LoadingScreen loading;
	public static MenuScreen menu;
	public static final Random RANDOM = new Random();
	public static Skin skin;
	public static TextureAtlas textureAtlas;
	public static BitmapFont font;
//loading screen
	public SpriteBatch batch;
	Texture img;
	public static AssetManager manager;
	public static PlayScreenManager mng;
	public static SoundManager soundManager;
	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		OFFSET = Gdx.graphics.getHeight()/8;
		ZOOM = 2f;
		batch = new SpriteBatch();

		System.out.print(Gdx.app.getGraphics().getWidth() + " " + HEIGHT);
		manager = new AssetManager();
		font = new BitmapFont(Gdx.files.internal("loading/default.fnt"), Gdx.files.internal("loading/default.png"), false);
		textureAtlas = new TextureAtlas(Gdx.files.internal("loading/uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("loading/uiskin.json"),textureAtlas);
		loading = new LoadingScreen(this, skin, font);
		setScreen(loading);


	}
	public void finishedLoading()
	{
		soundManager = SoundManager.getInstance();
		soundManager.init();
		mng = new PlayScreenManager(this);
		mng.addScreen(new PlayScreenStageOne(this,"maze1.tmx"));
		mng.addScreen(new PlayScreenStageOne(this,"maze2.tmx"));
		mng.setCurrentScreen();

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
