package com.lamtuananh.maze.tools;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 3. 11. 2016.
 */
public class ControlButtons {
    static ControlButtons instance= null;
   // float sizeX = Gdx.app.getGraphics().getWidth()/10;
  //  float sizeY = Gdx.app.getGraphics().getHeight()/5;
    float sizeX = 100,sizeY=100;

    public CustomButton up,down,left,right,attach, circle;
    Stage stage;

    public ControlButtons ( Stage stage)
    {
            this.stage = stage;
            initButtons();
            instance = this;
    }
    public static ControlButtons getInstance(){
        return instance;
    }
    public Stage getStage()
    {
        return stage;
    }
    private void initButtons() {
  /*      up = new CustomButton(stage, "up","upDown");
        down = new CustomButton(stage,"down","downDown");
        left= new CustomButton(stage, "left","leftDown");
        right = new CustomButton(stage,"right","rightDown" );
        attach = new CustomButton(stage,"attach","attachDown");


        up.button.setPosition(Gdx.app.getGraphics().getWidth()-2*sizeX-MazeGame.OFFSET+0.25f*sizeX,2*sizeY+MazeGame.OFFSET-sizeY);
        down.button.setPosition(Gdx.app.getGraphics().getWidth()-2*sizeX-MazeGame.OFFSET+0.25f*sizeX,MazeGame.OFFSET);

        left.button.setPosition((Gdx.app.getGraphics().getWidth()-3*sizeX-MazeGame.OFFSET+0.50f*sizeX),sizeY+MazeGame.OFFSET-0.50f*sizeY);

        right.button.setPosition((Gdx.app.getGraphics().getWidth()-sizeX-MazeGame.OFFSET),sizeY+MazeGame.OFFSET-0.50f*sizeY);

        attach.button.setPosition(1.5f*MazeGame.OFFSET,1.5f*MazeGame.OFFSET);
*/
        circle = new CustomButton(stage,"circleButton","circleButton");
        circle.button.setPosition(MazeGame.circlePosition.x,MazeGame.circlePosition.y);
    }

}
