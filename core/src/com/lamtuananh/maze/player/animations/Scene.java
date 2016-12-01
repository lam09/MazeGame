package com.lamtuananh.maze.player.animations;

public class Scene {
	protected Scene prev,next;
	protected SceneHandler callback;
    public boolean running=false;
    public int index;
    public long time;
	public Scene(Scene prev, Scene next) {
		this.prev = prev;
		this.next = next;
	}
	public Scene() {}
    public void addPrevScene(Scene prev)
    {
    	this.prev=prev; 
    }
    public void addIndex(int index)
    {
    	this.index=index;
    }
    public void addNextScene(Scene next)
    {
    	this.next=next;
    }
    public void addSceneCallback(SceneHandler callback)
    {
    	this.callback=callback;
    }
	public void update()
	{
	
		
	}
	public void start()
	{
	   running=true;
	   time=System.currentTimeMillis();
	   callback.onSceneStarted(this);
	}
	public void end()
	{
		running=false;
		callback.onSceneEnded(this);
	}



}
