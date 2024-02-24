package com.jaewonjung.fighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.jaewonjung.fighter.models.Dummy;
import com.jaewonjung.fighter.models.Player;
import com.jaewonjung.fighter.models.PlayerStatus;

import java.util.ArrayList;

public class Fighter extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private Dummy dummy;
	final private ArrayList<Rectangle> platforms = new ArrayList<>();
	private ShapeRenderer shape;
	private boolean keyPressed;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		Rectangle platform1 = new Rectangle();
		Rectangle platform2 = new Rectangle();
		platform1.set(100, 50, 200, 20);
		platform2.set(400, 200, 200, 20);
		platforms.add(platform1);
		platforms.add(platform2);
		player = new Player();
		dummy = new Dummy();
		keyPressed = false;
	}

	@Override
	public void render () {
		//need to detect when unit is not over a platform
		ScreenUtils.clear(255, 255, 255, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.render(batch);
		dummy.render(batch);
		batch.end();
		player.update(platforms);
		dummy.update(platforms);
		for (Rectangle platform : platforms) {
			this.shape.begin(ShapeRenderer.ShapeType.Filled);
			this.shape.setColor(Color.BLUE);
			this.shape.rect(platform.x, platform.y, platform.width, platform.height);
			this.shape.end();
		}
		keyPressed = false;
		//making the object move with keyboard inputs
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			keyPressed = true;
			player.hitbox.x -= 400 * Gdx.graphics.getDeltaTime();
			player.facingDirection = -1;
			player.playerStatus = PlayerStatus.RUNNING;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			keyPressed = true;
			player.hitbox.x += 400 * Gdx.graphics.getDeltaTime();
			player.facingDirection = 1;
			player.playerStatus = PlayerStatus.RUNNING;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.jumpsLeft > 0) {
			//implement kinematic equations
			//when the user jumps, up velocity is constant, and is constantly decreasing
			keyPressed = true;
			player.onPlatform = false;
			player.velocityY =  player.jumpVelocity;
			player.jumpsLeft -= 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			keyPressed = true;
			player.velocityY -= 80;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			keyPressed = true;
			player.attack(dummy);
		}
		if (!keyPressed) {
			player.playerStatus = PlayerStatus.STILL;
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
		dummy.dispose();
	}

}
