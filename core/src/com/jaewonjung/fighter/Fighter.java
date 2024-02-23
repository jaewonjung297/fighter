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
import com.jaewonjung.fighter.models.Player;

import java.util.ArrayList;

public class Fighter extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	final private ArrayList<Rectangle> platforms = new ArrayList<>();
	private ShapeRenderer shape;
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
	}

	@Override
	public void render () {
		//need to detect when unit is not over a platform
		ScreenUtils.clear(255, 255, 255, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.render(batch);
		batch.end();
		player.update(platforms);
		for (Rectangle platform : platforms) {
			this.shape.begin(ShapeRenderer.ShapeType.Filled);
			this.shape.setColor(Color.BLUE);
			this.shape.rect(platform.x, platform.y, platform.width, platform.height);
			this.shape.end();
		}

		//making the object move with keyboard inputs
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.hitbox.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.hitbox.x += 200 * Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.jumpsLeft > 0) {
			//implement kinematic equations
			//when the user jumps, up velocity is constant, and is constantly decreasing
			player.onPlatform = false;
			player.velocityY =  player.jumpVelocity;
			player.jumpsLeft -= 1;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}

}
