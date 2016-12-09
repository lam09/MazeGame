package com.lamtuananh.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lamtuananh.maze.MazeGame;
import com.lamtuananh.maze.tools.CustomButton;

/**
 * Created by a.lam.tuan on 2. 11. 2016.
 */
public class MenuScreen implements Screen{
    private final OrthographicCamera camera;
    BitmapFont textButtonFont,labelFont, serverLabelFont;
    public Label versionLabel, connectingLabel,serverLabel;
    public class CustomButton
    {
        public Button button;
        public CustomButton(Stage stage , Skin skin, String up, String down, String checked, String disabled)
        {
            TextButton.TextButtonStyle textButtonStyle=new TextButton.TextButtonStyle();
            textButtonStyle.up = skin.getDrawable(up);
            textButtonStyle.down = skin.getDrawable(down);
            textButtonStyle.over = skin.getDrawable(checked);
            // textButtonStyle.checked = skin.getDrawable(checked);
            textButtonStyle.disabled = skin.getDrawable(disabled);
            button=new Button(textButtonStyle);
            stage.addActor(button);
        }
        public CustomButton(Stage stage ,Skin skin,String up,String down,String checked,String disabled,String name,Vector2 position)
        {
            TextButton.TextButtonStyle textButtonStyle=new TextButton.TextButtonStyle();
            textButtonStyle.up = skin.getDrawable(up);
            textButtonStyle.down = skin.getDrawable(down);
            textButtonStyle.over = skin.getDrawable(checked);
            textButtonStyle.disabled = skin.getDrawable(disabled);
            textButtonStyle.font = textButtonFont;
            button = new TextButton(name, textButtonStyle);
            button.setPosition(position.x,position.y);
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


    SpriteBatch batch;
    Stage serviceStage;
    Skin serviceSkin;
    InputMultiplexer inputMul;
    TextureAtlas buttonAtlas;
    CustomButton newGame, continueGame;
 Texture background;
    public MenuScreen(){
        batch = new SpriteBatch();
        batch = new SpriteBatch();
        background = MazeGame.manager.get("menuBackground.png", Texture.class);
        inputMul = new InputMultiplexer();
        serviceStage = new Stage();
        inputMul.addProcessor(serviceStage);
        Gdx.input.setInputProcessor(serviceStage);
        // CAMERA
        camera = new OrthographicCamera();
        // camera.setToOrtho(false, Ag.SCREEN_WIDTH, Ag.SCREEN_HEIGHT);
        // SKIN
        serviceSkin = MazeGame.manager.get("uiskin.json", Skin.class);

        // ATLAS
        buttonAtlas = MazeGame.manager.get("maintenance.atlas", TextureAtlas.class);
        serviceSkin.addRegions(buttonAtlas);
        textButtonFont=MazeGame.manager.get("font/showcard20.fnt",BitmapFont.class);
//      textButtonFont =  MazeGame.manager.get("default.fnt", BitmapFont.class);
        newGame = new CustomButton(serviceStage, serviceSkin,"Bill", "BillDown", "BillDown", "BillDown", "  New Game  ",new Vector2(MazeGame.SCREEN_WIDTH/2 - 55,MazeGame.SCREEN_HEIGHT/2 + 40));
        continueGame = new CustomButton(serviceStage, serviceSkin,"Bill", "BillDown", "BillDown", "BillDown", "  Continue  ",new Vector2(MazeGame.SCREEN_WIDTH/2 - 55,MazeGame.SCREEN_HEIGHT/2 - 40));
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                MazeGame.mng.setNextScreen();
            }
        });
        continueGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                MazeGame.mng.setNextScreen();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        serviceStage.act();
        batch.begin();
        batch.draw(background,0,0,MazeGame.SCREEN_WIDTH,MazeGame.SCREEN_HEIGHT);
        batch.end();
        serviceStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
