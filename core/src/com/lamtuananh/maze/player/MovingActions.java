package com.lamtuananh.maze.player;

/**
 * Created by Lam on 11/4/2016.
 */
public interface MovingActions {
    public void goDown();
    public void goUp();
    public void goLeft();
    public void goRight();
    public void appearing();
    public void disappearing();
    public void die();
    public void attack();
    public void wincelebration();

}
