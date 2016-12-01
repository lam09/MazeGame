package com.lamtuananh.maze.player.animations;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.lamtuananh.maze.MazeGame;

/**
 * General class for encapsulating animations consisting of an image set.
 */
public class GameAnimation {
	protected static final String LOCATION = "animations/";
	
	// animation name
	protected String name;
	// to which game the animation belongs
	protected String game;
	// position on x axis
	public float x;
	// position on y axis
	public float y;
	public float radius=0;
	public  float scale=1f;
	
	public  float width = 0f;
	public float height = 0f;
	private boolean winning;
	
	protected TextureAtlas atlas;
	protected String [] regionNames=null;
	protected Animation animation;
	protected float elapsedTime = 0;
	protected float delay = 0;
	public float animDuration;
	protected float animDelay;
	protected PlayMode mode;
	protected Actor staticActor = null;
	AnimationCallback callback;
	boolean doAnimation=true;
	float k=1f;
	public void setCallback(AnimationCallback callback)
	{
		this.callback=callback;
	}

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
	
	public GameAnimation(String game, String name, float x, float y, float duration, float delay, PlayMode mode,boolean enabled)
	{
	  this(game, name, x, y, duration, delay, mode);
	  this.doAnimation=enabled;
	}
	public void setAnimationFar(float k)
	{
		this.k=k;
	}
	
	public GameAnimation(String game, String name, float x, float y, float duration, float delay, PlayMode mode) {
		this.game = game;
		this.name = name;
		this.x = x;
		this.y = y;
		this.animDuration = duration;
		this.animDelay = delay;
		this.mode = mode;
		this.winning = false;
		
		init();
		
	}
	public GameAnimation(String game, String name, float x, float y,float scale, float duration, float delay, PlayMode mode) {
		this(game, name, x, y, duration, delay, mode);
		this.scale=scale;
	}
	public void  dispose()
	{
		atlas.dispose();
	    
	}
	public int c ,r;
	public GameAnimation(int c,int r ,String [] regionNames,String game,String name, float x, float y,float scale, float duration, float delay, PlayMode mode) {
		this(game, name, x, y, duration, delay, mode);
		this.scale=scale;
		this.c=c;
		this.r=r;
		this.regionNames=regionNames;
		init();
	}
	
	/**
	 * Initializes animation.
	 */
	public void init() {
		// overrite to test  hunter  lines  from hunter season 
	   
		if(MazeGame.manager.isLoaded(name))
		   atlas = MazeGame.manager.get(name);
		if(MazeGame.manager.isLoaded(name))
			atlas = MazeGame.manager.get(name);
		Array<Sprite> sprites=null;
		//add specified names in atlas , else add all
		
		
		if(atlas!= null)
		{
		if(regionNames!=null)
		{
			sprites=new Array<Sprite>();
			//sprites.get(0).setre
			
			
			for(String name:regionNames)
				sprites.add(atlas.createSprite(name));
		}
		else {
			sprites = atlas.createSprites();
			
		//	atlas.
			
			
	
		/*
	  for(AtlasRegion region:atlas.getRegions())
	  {
		  
	  }
			*/
			
			
		}

		
		animation = new Animation(animDuration / sprites.size, sprites, mode);
		width = sprites.get(0).getRegionWidth();
		height = sprites.get(0).getRegionHeight();
		}
		
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setDuration(float duration) {
		animDuration = duration;
		animation.setFrameDuration(duration / animation.getKeyFrames().length);
	}

	public float getDuration() {
		return animDuration;

	}

   public void setOpacity (float alpha)
   {
	   this.k=alpha;
   }
   public void setEnabled(boolean enabled)
   {
	   doAnimation=enabled;
   }
	public void setPlayMode(PlayMode playMode) {
		this.mode = playMode;
		animation.setPlayMode(playMode);
	}
	
	/**
	 * Draws current frame to batch.
	 * 
	 * @param batch
	 * @param delta time offset
	 */
	public void drawAnimation(SpriteBatch batch , float delta) {
		drawAnimation(batch, delta, x, y);
	}
	
	public void drawAnimation(SpriteBatch batch, float delta, float x, float y) {
		//if(shader != null) batch.setShader(shader.shader);
		if (delay >= animDelay&&doAnimation) {
			// only start after rolling ends
			TextureRegion frame = animation.getKeyFrame(elapsedTime);
	
			
			if (staticActor != null && animation.getKeyFrameIndex(elapsedTime) == 0) {
				// remove static image on first frame
				staticActor.remove();
				staticActor = null;
			}
			
		//	batch.draw(frame, x, y, frame.getRegionWidth()*scale, frame.getRegionHeight()*scale);
			
			 
			//batch.draw(frame, x, y, frame.getRegionWidth()*scale, frame.getRegionHeight()*scale,fullArea);
			Sprite s= (Sprite)frame;
			s.setPosition(x, y);
			s.setAlpha(k);
			s.draw(batch);
			elapsedTime += delta;
		}
		else 
		{
			TextureRegion frame = animation.getKeyFrame(0);
			Sprite s= (Sprite)frame;
			s.setPosition(x, y);
			s.setAlpha(k);
			s.draw(batch);
			
		}
		
		delay += delta;

		/*if (extension != null) {
			batch.draw(extension, x + extensionX, y + extensionY, extension.getRegionWidth()*extensionScale, extension.getRegionHeight()*extensionScale);
		}
		if(shader != null) batch.setShader(null);*/
		if(isEnded()&&callback!=null&&!onEndCalled){callback.onEnd();onEndCalled=true;}
	}
	boolean onEndCalled=false;
	//Rectangle fullArea=new Rectangle(0f,0f,Draw.WIDTH*2,Draw.HEIGHT);
	/**
	 * Checks if animation already ended.
	 * @return true for finished animation, false otherwise.
	 */
	public boolean isEnded() {
		return elapsedTime > (PlayMode.LOOP_PINGPONG.equals(mode) ? animDuration*2 : animDuration);
	}
	public String getName() {
		return name;
	}

	public Actor getStaticActor() {
		return staticActor;
	}

	public void setStaticActor(Actor staticActor) {
		this.staticActor = staticActor;
	}
	
	public void restart() {
		doAnimation=true;
		delay = 0;
		elapsedTime = 0;
		onEndCalled=false;
	}
	public void start() {
		doAnimation=true;
		delay = 0;
		elapsedTime = 0;
		onEndCalled=false;
	}

	public boolean isWinning() {
		return winning;
	}

	public void setWinning(boolean winning) {
		this.winning = winning;
	}

	/*public void setShader(SymbolShader shader) {
		this.shader = shader;
	}*/
	
}
