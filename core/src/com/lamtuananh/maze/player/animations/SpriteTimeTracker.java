package com.lamtuananh.maze.player.animations;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteTimeTracker {
	private long startTime;
	private long trackTime;
	private boolean continues;
	private Sprite sprite;
	ArrayList<SpriteCallback> scenes;
	
	public SpriteTimeTracker(boolean continues)
	{
		this.continues = continues;
	}
	public void start(){
		startTime=System.currentTimeMillis();
		trackTime = startTime;	
	}
	
	public void update()
	{
		trackTime = System.currentTimeMillis();
		
	}
}
