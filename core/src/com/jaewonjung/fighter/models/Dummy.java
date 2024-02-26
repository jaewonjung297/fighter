package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Dummy implements Model {
    private final Texture dummyImg;
    public Rectangle hitbox;
    public int health;
    public float velocityY;
    public float gravity = (float) - 2000;
    public boolean onPlatform;
    public Dummy() {
        this.dummyImg = new Texture("bucket.png");
        this.hitbox = new Rectangle(400, 0, 30, 64);
        this.health = 100;
        this.velocityY = 0;
        this.onPlatform = false;
    }

    @Override
    public void update(ArrayList<Rectangle> platforms) {
        for (Rectangle platform: platforms) {
            if (hitbox.overlaps(platform)) {
                if (!onPlatform && velocityY < 0 && hitbox.y > platform.y) {
                    velocityY = 0;
                    hitbox.y = platform.y + platform.height - 1;
                    onPlatform = true;
                }
            } else {
                onPlatform = false;
            }
        }
        if (!onPlatform) {
            velocityY += gravity * Gdx.graphics.getDeltaTime();
            hitbox.y += velocityY * Gdx.graphics.getDeltaTime();
        }

        if (hitbox.y < 0) {
            hitbox.y = 0;
            velocityY = 0;
        }

        if (hitbox.x < 0) {
            hitbox.x = 0;
        }
        if (hitbox.x > 800 - 64) {
            hitbox.x = 800 - 64;
        }
    }
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(dummyImg,hitbox.x, hitbox.y);
    }
    @Override
    public void dispose() {
        dummyImg.dispose();
    }
}
