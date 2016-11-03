package com.lamtuananh.maze.tools;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by a.lam.tuan on 3. 11. 2016.
 */
public class ControlButtons {
    public class CustomButton
    {
        public Button button;
        public CustomButton(Stage stage , Skin skin, String up, String down, String checked, String disabled)
        {
            TextButton.TextButtonStyle textButtonStyle=new TextButton.TextButtonStyle();
            textButtonStyle.up = skin.getDrawable(up);
            textButtonStyle.down = skin.getDrawable(down);
            textButtonStyle.checked = skin.getDrawable(checked);
            textButtonStyle.disabled = skin.getDrawable(disabled);
            button=new Button(textButtonStyle);
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
    public CustomButton up,down,left,right;
    Stage stage;
    Skin skin;
    public ControlButtons ()
    {

    }

}
