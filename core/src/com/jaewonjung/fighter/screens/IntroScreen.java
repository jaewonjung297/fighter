package com.jaewonjung.fighter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jaewonjung.fighter.Fighter;

public class IntroScreen extends FighterScreen {

    private static GlyphLayout glyphLayout;
    private Stage stage;
    private TextButton button;
    private TextButton.TextButtonStyle textButtonStyle;

    public IntroScreen (Fighter game) {
        super(game);
        glyphLayout = new GlyphLayout();
        stage = new Stage();
        textButtonStyle = new TextButton.TextButtonStyle();

        textButtonStyle.font = game.font;
        textButtonStyle.fontColor = Color.BLACK;

        button = new TextButton("Training Mode", textButtonStyle);
        button.getLabel().setFontScale(2, 2);
        button.setPosition((game.dimensions[0] - button.getWidth()) /2,game.dimensions[1] / 5);
        button.setVisible(false);

        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(game.trainingScreen);
            }
        });

        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
    }

        @Override
    public void render (float delta) {
        ScreenUtils.clear(255, 255, 255, 0);
        stage.act(delta);
        stage.draw();
        time += delta;
        game.batch.begin();

        if (time > 1) {
            button.setVisible(true);
        }

        float fontY = Math.min(game.dimensions[1] * 2 / 3, time * 500);
        float fontX = (game.dimensions[0] - glyphLayout.width) / 2;
        float fontScale = Math.min(10, time * 10 + 1);
        game.font.setColor(Color.BLACK);

        game.font.getData().setScale(fontScale, fontScale);
        String menuText = "FIGHTER!";
        glyphLayout.setText(game.font, menuText);

        game.font.draw(game.batch, glyphLayout, fontX, fontY);

        game.batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
