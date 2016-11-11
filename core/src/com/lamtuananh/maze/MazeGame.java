package com.lamtuananh.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.lamtuananh.maze.screens.PlayScreen;
import com.lamtuananh.maze.screens.PlayScreenManager;
import com.lamtuananh.maze.screens.PlayScreenStageOne;

public class MazeGame extends Game {
	public static final float PPM = 50;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short STONE_BIT = 4;
	public static final short FLEXIWALL_BIT = 8;
	public static  int OFFSET = 25;

	public static int WIDTH;// = Gdx.app.getGraphics().getWidth();
	public static int HEIGHT;// = Gdx.app.getGraphics().getHeight();



	public SpriteBatch batch;
	Texture img;
	public static AssetManager manager;
	public static TmxMapLoader mapLoader;
	public PlayScreenManager mng;
	@Override
	public void create () {
		System.out.print(Gdx.app.getGraphics().getWidth() + " " + HEIGHT);
		manager = new AssetManager();
		mapLoader = new TmxMapLoader();
//		mapLoader.load("maze1.tmx");
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("maze1.tmx", TiledMap.class);
	//	manager.load("maze2.tmx", TiledMap.class);
	/*	manager.load("maze3.tmx", TiledMap.class);
		manager.load("maze4.tmx", TiledMap.class);
		manager.load("maze5.tmx", TiledMap.class);
		manager.load("maze6.tmx", TiledMap.class);
		manager.load("maze7.tmx", TiledMap.class);*/
		manager.load("buttons/up.png", Texture.class);
		manager.load("buttons/down.png", Texture.class);
		manager.load("buttons/left.png", Texture.class);
		manager.load("buttons/right.png", Texture.class);
		manager.load("buttons/upDown.png", Texture.class);
		manager.load("buttons/downDown.png", Texture.class);
		manager.load("buttons/leftDown.png", Texture.class);
		manager.load("buttons/rightDown.png", Texture.class);
		manager.load("buttons/attach.png", Texture.class);
		manager.load("buttons/attachDown.png", Texture.class);

		manager.load("tortoise/goDown3.png", Texture.class);
		manager.load("tortoise/goLeft.png", Texture.class);
		manager.load("tortoise/goRight.png", Texture.class);
		manager.load("tortoise/goUp.png", Texture.class);
		manager.load("enemy/stone/Stone.png", Texture.class);
		manager.load("flexiwall.png", Texture.class);

		for(int i = 1 ; i<13; i++)
			manager.load("enemy/stone/"+i+".png", Texture.class);

		manager.finishLoading();

		mng = new PlayScreenManager(this);
		mng.addScreen(new PlayScreenStageOne(this,"maze1.tmx"));
		//mng.addScreen(new PlayScreenStageOne(this,"maze2.tmx"));

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
