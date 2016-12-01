package com.lamtuananh.maze.player.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;

public class CollectiveAnimation extends GameAnimation {


	public CollectiveAnimation(String game, String name, float x, float y, float scale, float duration, float delay,
			PlayMode mode) {
		super(game, name, x, y, scale, duration, delay, mode);
		// TODO Auto-generated constructor stub
	}

	public CollectiveAnimation(String game, String name, float x, float y, float duration, float delay, PlayMode mode,
			boolean enabled) {
		super(game, name, x, y, duration, delay, mode, enabled);
		// TODO Auto-generated constructor stub
	}

	public CollectiveAnimation(String game, String name, float x, float y, float duration, float delay, PlayMode mode) {
		super(game, name, x, y, duration, delay, mode);
		// TODO Auto-generated constructor stub
	}
	
	
	
	class FrameType
	{
		String name;
		Vector2 relativePosition;
		Animation animation;
	    Array<Sprite> sprites=new Array<Sprite>();
		public FrameType(String name, Vector2 relativePosition) {
			this.name = name;
			this.relativePosition = relativePosition;
			
		}
		public void addSprite(Sprite sprite)
		{
			sprite.setPosition(x+relativePosition.x, y+relativePosition.y);
			sprites.add(sprite);
		}
		public void createAnimation()
		{
			if(this.sprites==null)return;
			this.animation = new Animation(animDuration / sprites.size, sprites, mode);
		//	System.out.println("create animation "+sprites.size);
		}
		
	}
	
	FrameType []  fTypes;
			
	@Override
	public void init() {
		
		fTypes=new FrameType[] {
			new FrameType("top", new Vector2(-208f,411.5f-103f)),
			new FrameType("down", new Vector2(-208f,-411.5f)),
			new FrameType("right", new Vector2(208f-124f,-411.5f+98f)),
			new FrameType("left", new Vector2(-208f,-411.5f+98f))};
		

		if(MazeGame.manager.isLoaded(name))
		   atlas = MazeGame.manager.get(name);
		if(MazeGame.manager.isLoaded(name))
			atlas = MazeGame.manager.get(name);
		Array<Sprite> sprites=null;
		//add specified names in atlas , else add all
		
		
		if (atlas != null) {

			for (AtlasRegion region : atlas.getRegions()) {
				for (FrameType ft : fTypes) {
					if (region.name.contains(ft.name)) {
						ft.addSprite(atlas.createSprite(region.name));
						break;
					}
				}

			}
			for (FrameType ft : fTypes)
				ft.createAnimation();

		}
	}

	public void drawAnimation(SpriteBatch batch, float delta) {

		if (delay >= animDelay && doAnimation) {
			drawFrameAtTime(elapsedTime, batch);
			elapsedTime += delta;
		} else
			drawFrameAtTime(0.0f, batch);

		delay += delta;
		if (isEnded() && callback != null && !onEndCalled) {
			callback.onEnd();
			onEndCalled = true;
		}
	}

	private void drawFrameAtTime(float time, SpriteBatch batch) {
		for (FrameType ft : fTypes) {
			TextureRegion frame = ft.animation.getKeyFrame(time);
			Sprite s= (Sprite)frame;
			s.setPosition(x, y);
			s.setAlpha(k);
			s.draw(batch);
		//	batch.draw((Sprite) frame, fullArea, 0.0f, null);
		}
	}
	@Override
	public void dispose() {
		super.dispose();
		
	}
	
	
	
	
	

}
