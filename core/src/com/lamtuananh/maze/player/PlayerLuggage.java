package com.lamtuananh.maze.player;

/**
 * Created by Lam on 11/11/2016.
 */
public class PlayerLuggage {
    static PlayerLuggage instance = new PlayerLuggage();
    public PlayerLuggage(){

    }
    public PlayerLuggage getInstance()
    {
        return instance;
    }

    //Items
    boolean hasKey = false;
    boolean hasWeapon = false;
    public void takeKey()
    {
        hasKey = true;
    }
    public void takeWeapon()
    {
        hasWeapon = true;
    }
}
