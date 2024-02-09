package com.jaewonjung.fighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Fighter extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture playerImg;
	private Texture platformImg;
	private OrthographicCamera camera;
	private Rectangle player;
	private float velocityY = 0;
	private float gravity = (float) -2000;
	private float jumpVelocity = 800;
	private ArrayList<Rectangle> platforms = new ArrayList<>();

	@Override
	public void create () {
		playerImg = new Texture("bucket.png");
		platformImg = new Texture("bucket.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		Rectangle platform1 = new Rectangle();
		platform1.set(100, 50, 200, 20);
		platforms.add(platform1);

		player = new Rectangle();
		player.x = 800 / 2 - 64 / 2;
		player.y = 0;
		player.width = 64;
		player.height = 64;
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerImg, player.x, player.y);
		for (Rectangle platform : platforms) {
			batch.draw(platformImg, platform.x, platform.y, platform.width, platform.height);
		}
		batch.end();


		//apply gravity to y velocity
		velocityY += gravity * Gdx.graphics.getDeltaTime();
		player.y += velocityY * Gdx.graphics.getDeltaTime();

		//can only jump if model is on the ground
		if (player.y < 0) {
			player.y = 0;
			velocityY = 0;
		}

		//set boundaries
		if (player.x < 0) {
			player.x = 0;
		}
		if (player.x > 800 - 64) {
			player.x = 800 - 64;
		}

		//making the object move with keyboard inputs
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.y == 0) {
			//implement kinematic equations
			//when the user jumps, up velocity is constant, and is constantly decreasing
			velocityY =  jumpVelocity;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerImg.dispose();
	}
}
