package com.jaewonjung.fighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaewonjung.fighter.models.Dummy;
import com.jaewonjung.fighter.models.Player;
import com.jaewonjung.fighter.models.PlayerStatus;
import com.jaewonjung.fighter.models.PlayerInputProcessor;
import com.jaewonjung.fighter.screens.IntroScreen;
import com.jaewonjung.fighter.screens.TrainingScreen;

import java.util.ArrayList;

public class Fighter extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public int[] dimensions;
	public TrainingScreen trainingScreen;
	public IntroScreen introScreen;

	@Override
	public void create() {
		dimensions = new int[]{1200, 700};
		batch = new SpriteBatch();
		font = new BitmapFont();
		trainingScreen = new TrainingScreen(this);
		introScreen = new IntroScreen(this);
		setScreen(introScreen);
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}