package com.lamtuananh.maze.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 2. 12. 2016.
 */
public class CustomButton
{
    float sizeX = Gdx.app.getGraphics().getWidth()/10;
    float sizeY = Gdx.app.getGraphics().getHeight()/5;

    public Button button;
    public Rectangle rect;
    public CustomButton(Stage stage , String up, String down)
    {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = MazeGame.skin.getDrawable(up);
        //imageButtonStyle.down = MazeGame.skin.getDrawable(up);
        imageButtonStyle.checked =  MazeGame.skin.getDrawable(down);
        //imageButtonStyle.over= MazeGame.skin.getDrawable(down);
        //imageButtonStyle.imageOver= MazeGame.skin.getDrawable(down);
        button=new Button(imageButtonStyle);
        button.setSize(sizeX,sizeY);
        stage.addActor(button);
    }
    protected void disable(boolean disabled)
    {
        if(!button.isChecked())
            button.setDisabled(disabled);
    }

    public boolean isOvered()
    {
        if(rect == null ){
            rect = new Rectangle(button.getX(),button.getY(),button.getWidth(),button.getHeight());
        }
        if(Gdx.input.isTouched())
        {
            float x =Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
        //    System.out.println(x + " " + y);
            //   System.out.println("button " +button.getX() + " " + button.getY());

            if(x>rect.getX() && x<rect.getX()+rect.getWidth()&& y>rect.getY()
                    && y<rect.getY()+rect.getHeight())
            {
                button.setChecked(true);
                return true;
            }
        }
        button.setChecked(false);
        return false;//|button.isChecked();
    }
    public void addListener(ClickListener click)
    {
        button.addListener(click);
    }
}