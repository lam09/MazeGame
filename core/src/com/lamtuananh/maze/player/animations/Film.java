package com.lamtuananh.maze.player.animations;

public class Film implements  SceneHandler {
	
	Scene currentScene=null;
    Scene lastAddScene=null;
	public boolean running=false;
	public int  size=0;
	FilmHandler handler;
	public Film(FilmHandler handler)
	{
		 this.handler=handler;
	}
	public void addScene(Scene scene)
	{
	  scene.addSceneCallback(this);
	  size++;
	  scene.addIndex(size);
		if(lastAddScene==null)
		{
			currentScene=scene;
			lastAddScene=scene;
		}
		else
		{
			Scene tmpS=lastAddScene;
			 tmpS.addNextScene(scene);
			 scene.addPrevScene(tmpS);
			 lastAddScene=scene;
		}
		
		
	}
	public int getCurrentIndex()
	{
		return currentScene.index;
	}
	public Scene  getCurrentScene()
	{
		return currentScene;
	}
	public void update()
	{
		if(!running)return;
		currentScene.update();
	}
	public void start()
	{
		if(running)return;
		if(currentScene==null)return;
		running=true;
		currentScene.start();
	}
	public void end()
	{
		running=false;
		handler.onFilmEnded();
	}
	@Override
	public void onSceneEnded(Scene scene) {
		if(scene.next==null)
		{
			end();
			return;
		}
		currentScene=scene.next;
		currentScene.start();
		
	}

	@Override
	public void onSceneStarted(Scene scene) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
