package com.lamtuananh.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.lamtuananh.maze.screens.PlayScreen;

public class MazeGame extends Game {
	public static final float PPM = 50;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;


	public SpriteBatch batch;
	Texture img;
	public static AssetManager manager;
	public static TmxMapLoader mapLoader;

	@Override
	public void create () {
		manager = new AssetManager();
		mapLoader = new TmxMapLoader();
//		mapLoader.load("maze1.tmx");
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("maze1.tmx", TiledMap.class);
		manager.load("tortoise/goDown3.png", Texture.class);
		manager.load("tortoise/goLeft.png", Texture.class);
		manager.load("tortoise/goRight.png", Texture.class);
		manager.load("tortoise/goUp.png", Texture.class);
		manager.load("enemy/stone/Stone.png", Texture.class);
		for(int i = 1 ; i<13; i++)
			manager.load("enemy/stone/"+i+".png", Texture.class);

		manager.finishLoading();

		setScreen(new PlayScreen(this));
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
