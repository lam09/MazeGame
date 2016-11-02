package com.lamtuananh.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
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
		manager.finishLoading();

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	/*	Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
