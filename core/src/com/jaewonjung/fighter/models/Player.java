package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player {
    private final Texture playerImg;
    public Rectangle hitbox;
    public float velocityY;
    public float gravity = (float) - 2000;
    public float jumpVelocity = 800;
    public boolean onPlatform;
    public int jumpsLeft = 2;
    public Player() {
        this.playerImg = new Texture("bucket.png");
        this.hitbox = new Rectangle(368, 0, 30, 64);
        this.velocityY = 0;
        this.onPlatform = false;
    }
    public void update(ArrayList<Rectangle> platforms) {
        for (Rectangle platform: platforms) {
            if (hitbox.overlaps(platform)) {
                if (!onPlatform && velocityY < 0 && hitbox.y > platform.y) {
                    velocityY = 0;
                    hitbox.y = platform.y + platform.height - 1;
                    onPlatform = true;
                    jumpsLeft = 2;
                } else {
                    onPlatform = false;
                }
            }
        }
        if (!onPlatform) {
            velocityY += gravity * Gdx.graphics.getDeltaTime();
            hitbox.y += velocityY * Gdx.graphics.getDeltaTime();
        }

        if (hitbox.y < 0) {
            hitbox.y = 0;
            velocityY = 0;
            jumpsLeft = 2;
        }

        if (hitbox.x < 0) {
            hitbox.x = 0;
        }
        if (hitbox.x > 800 - 64) {
            hitbox.x = 800 - 64;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(playerImg, hitbox.x, hitbox.y);
    }

    public void dispose() {
        playerImg.dispose();
    }
}
