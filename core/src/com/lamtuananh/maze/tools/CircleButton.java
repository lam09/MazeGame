package com.lamtuananh.maze.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by a.lam.tuan on 16. 12. 2016.
 */
public class CircleButton {

    private static CircleButton instance = null;
    final float boundx= 100;
    final float boundy = 100;
    private static Vector2 position;
    public CircleButton (Vector2 postition)
    {
        this.position=postition;
        instance = this;
    }

    public static CircleButton getInstance() {
        return instance;
    }

    public  boolean isUp()
    {
        //System.out.println("is up checking");
        if(Gdx.input.isTouched())
        {
            float x =Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
           // System.out.println(x + " " + y);
           // System.out.println("position x  " + position.x + " y" + position.y  );

            if (x>=position.x && position.x +2* boundx >=x && position.y+2*boundy < y && position.y + 3*boundy >=y)
            {
                System.out.println("is up checkeddddddddddddddddddddddd ok ");
                return true;
            }
        }
        return false;
    }
    public  boolean isDown()
    {
        if(Gdx.input.isTouched())
        {
            float x =Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
            if (position.x <=x && position.x +2* boundx >=x && position.y <= y && position.y + boundy >=y)
            {
                System.out.println("is down ok ");

                return true;
            }
        }
        return false;
    }
    public  boolean isLeft()
    {
        if(Gdx.input.isTouched())
        {
            float x =Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
            if (position.x  <=x && position.x+boundx >=x && position.y  <= y && position.y + 3*boundy >=y)
            {
                System.out.println("is left ok ");

                return true;
            }
        }
        return false;
    }
    public  boolean isRight()
    {
        if(Gdx.input.isTouched())
        {
            float x =Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
            if (position.x + 3*boundx >=x && position.x +2*boundx<=x && position.y  <= y && position.y + 3*boundy >=y)
            {
                System.out.println("is right ok ");
                return true;
            }
        }
        return false;
    }
}
