package com.lamtuananh.maze.player.animations;

import java.util.Random;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AcmeAnimation extends GameAnimation {

	private boolean repeat = false;
	private boolean isRunning = true;
	private float animationTime = 0;
//	private float time = 0;
	
	private float[] possibleDelays = { 1f, 2f, 3f, 5f, 10f, 15f };
	private PlayMode[] possiblePlayModes = { PlayMode.NORMAL, PlayMode.REVERSED };
	
	private static final Random RANDOM = new Random();
	
	/**
	 * Creates an animation.
	 * 
	 * @param game		name of the game
	 * @param name		animation name
	 * @param x			x position
	 * @param y			y position
	 * @param duration	duration of animation
	 * @param delay		time delay for animation
	 * @param mode		animation mode
	 */
	public AcmeAnimation(String game, String name, float x, float y, float duration, float delay, PlayMode mode) {
		super(game, name, x, y, duration, delay, mode);
		
		start();
	}
	
	public AcmeAnimation(String game, String name, float duration, float delay, PlayMode mode) {
		this(game, name, 0, 0, duration, delay, mode);
	}
	
	public void start() {
		if (repeat) {
			return;
		}
		repeat = true;
		animationTime = 0;
		isRunning = false;
		delay =possibleDelays[RANDOM.nextInt(possibleDelays.length)];
	}
	
	public void stop() {
		repeat = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setPossibleDelays(float[] delays) {
		this.possibleDelays = delays;
	}
	
	public void setPossiblePlayModes(PlayMode[] playModes) {
		this.possiblePlayModes = playModes;
	}
	@Override
	public void drawAnimation(SpriteBatch batch, float delta, float x, float y) {
		TextureRegion frame = animation.getKeyFrame(animationTime);
		//batch.draw(frame, x, y, frame.getRegionWidth(), frame.getRegionHeight(),fullArea);
		Sprite s= (Sprite)frame;
		s.setPosition(x, y);
		s.draw(batch);
		//batch.draw(s, fullArea,0.0f,null);

		if (repeat) {
			if (delay > 0) {
				delay -= delta;
			} else if (!isRunning) {
				isRunning = true;
				animationTime = 0;
			}
		}
		
		if (isRunning) {
			animationTime += delta;
			if (animation.isAnimationFinished(animationTime)) {
				isRunning = false;
				if (repeat) {
					delay = possibleDelays[RANDOM.nextInt(possibleDelays.length)];
					animation.setPlayMode(possiblePlayModes[RANDOM.nextInt(possiblePlayModes.length)]);
				}
			}
		}
	}
}
