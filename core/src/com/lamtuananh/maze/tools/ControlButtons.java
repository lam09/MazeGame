package com.lamtuananh.maze.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.lamtuananh.maze.MazeGame;

/**
 * Created by a.lam.tuan on 3. 11. 2016.
 */
public class ControlButtons {
    static ControlButtons instance= null;
    float sizeX = Gdx.app.getGraphics().getWidth()/10;
    float sizeY = Gdx.app.getGraphics().getHeight()/5;
    public class CustomButton
    {
        public Button button;
        public CustomButton(Stage stage , Texture up, Texture down)
        {
            ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
            Sprite u = new Sprite(up);
            imageButtonStyle.up = new SpriteDrawable(u);
            imageButtonStyle.down = new SpriteDrawable(new Sprite(down));
            button=new ImageButton(imageButtonStyle);
            button.setSize(sizeX,sizeY);
            stage.addActor(button);
        }
        protected void disable(boolean disabled)
        {
            if(!button.isChecked())
                button.setDisabled(disabled);
        }
        public void addListener(ClickListener click)
        {
            button.addListener(click);
        }
    }
    public CustomButton up,down,left,right,attach;
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

    private void initButtons() {
        up = new CustomButton(stage, MazeGame.manager.get("buttons/up.png", Texture.class),MazeGame.manager.get("buttons/upDown.png", Texture.class));
        down = new CustomButton(stage, MazeGame.manager.get("buttons/down.png", Texture.class),MazeGame.manager.get("buttons/downDown.png", Texture.class));
        left= new CustomButton(stage, MazeGame.manager.get("buttons/left.png", Texture.class),MazeGame.manager.get("buttons/leftDown.png", Texture.class));
        right = new CustomButton(stage, MazeGame.manager.get("buttons/right.png", Texture.class),MazeGame.manager.get("buttons/rightDown.png", Texture.class));
        attach = new CustomButton(stage, MazeGame.manager.get("buttons/attach.png", Texture.class),MazeGame.manager.get("buttons/attachDown.png", Texture.class));

        up.button.setPosition(Gdx.app.getGraphics().getWidth()-2*sizeX-MazeGame.OFFSET+0.25f*sizeX,2*sizeY+MazeGame.OFFSET-sizeY);
        down.button.setPosition(Gdx.app.getGraphics().getWidth()-2*sizeX-MazeGame.OFFSET+0.25f*sizeX,MazeGame.OFFSET);
        left.button.setPosition((Gdx.app.getGraphics().getWidth()-3*sizeX-MazeGame.OFFSET+0.50f*sizeX),sizeY+MazeGame.OFFSET-0.50f*sizeY);
        right.button.setPosition((Gdx.app.getGraphics().getWidth()-sizeX-MazeGame.OFFSET),sizeY+MazeGame.OFFSET-0.50f*sizeY);
        attach.button.setPosition(2*MazeGame.OFFSET,2*MazeGame.OFFSET);

    }

}
