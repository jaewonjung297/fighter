package com.jaewonjung.fighter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaewonjung.fighter.models.Dummy;
import com.jaewonjung.fighter.models.Player;
import com.jaewonjung.fighter.models.PlayerInputProcessor;
import com.jaewonjung.fighter.models.PlayerStatus;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class TrainingScreen extends FighterScreen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Player player;
    private Dummy dummy;
    final private ArrayList<Rectangle> platforms = new ArrayList<>();
    private ShapeRenderer shape;

    public TrainingScreen (Game game) {
        super(game);
    }

    @Override
    public void show () {
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
        PlayerInputProcessor ip = new PlayerInputProcessor(player);
        Gdx.input.setInputProcessor(ip);
    }

    @Override
    public void render (float delta) {
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
        if (player.movingDirection == 0 && player.velocityX != 0) {
            if (player.velocityX > 0) {
                player.velocityX = Math.max(0, player.velocityX - 1500 * Gdx.graphics.getDeltaTime());
            } else {
                player.velocityX = Math.min(0, player.velocityX + 1500 * Gdx.graphics.getDeltaTime());
            }
            if (player.velocityX == 0) {
                player.playerStatus = PlayerStatus.STILL;
            }
        }
        if (player.platformPass && TimeUtils.millis() - player.keyTime[0] > 200) {
            player.platformPass = false;
        }

    }

    @Override
    public void dispose () {
        batch.dispose();
        player.dispose();
        dummy.dispose();
    }
}
