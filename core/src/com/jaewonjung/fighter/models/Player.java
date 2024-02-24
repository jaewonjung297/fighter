package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player {
    private final Texture stillImage;
    private final Texture runningImage;
    private final TextureRegion currentRegion;
    public final Rectangle hitbox;
    public float velocityY;
    public float gravity = (float) - 2000;
    public float jumpVelocity = 800;
    public boolean onPlatform;
    public int jumpsLeft = 2;
    public int health;
    public int facingDirection;
    private boolean inAttack = false;
    public PlayerStatus playerStatus;
    public Player() {
        this.stillImage = new Texture("stick.png");
        this.runningImage = new Texture("stick_running.png");
        this.currentRegion = new TextureRegion(stillImage);
        this.playerStatus = PlayerStatus.STILL;
        this.hitbox = new Rectangle(368, 0, 30, 64);
        this.velocityY = 0;
        this.onPlatform = false;
        this.health = 100;
        this.facingDirection = 1;
    }
    public void update(ArrayList<Rectangle> platforms) {
        //update animation
        for (Rectangle platform: platforms) {
            if (hitbox.overlaps(platform)) {
                if (!onPlatform && velocityY < 0 && hitbox.y > platform.y) {
                    velocityY = 0;
                    hitbox.y = platform.y + platform.height - 1;
                    onPlatform = true;
                    jumpsLeft = 2;
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
            jumpsLeft = 2;
        }

        //bounds checking
        if (hitbox.x < 0) {
            hitbox.x = 0;
        }
        if (hitbox.x > 800 - 64) {
            hitbox.x = 800 - 64;
        }
    }

    public void attack(Dummy opponent) {
        if (!inAttack) {
            Rectangle attackHitBox = new Rectangle(this.hitbox.x, this.hitbox.y, this.hitbox.width / 2 + 80, this.hitbox.height);
            if (attackHitBox.overlaps(opponent.hitbox)) {
                //attack render, move opponent to the direction of attack
                opponent.hitbox.x += this.facingDirection * 20;
                opponent.hitbox.y += 10;
            }
        }
    }

    private void updateCurrentRegion() {
        switch (playerStatus) {
            case STILL:
                currentRegion.setRegion(stillImage);
                break;
            case RUNNING:
                currentRegion.setRegion(runningImage);
        if ((facingDirection == -1 && currentRegion.isFlipX()) || (facingDirection != -1 && !currentRegion.isFlipX())) {
            currentRegion.flip(true, false);
        }
        }
    }


    public void render(SpriteBatch batch) {
        updateCurrentRegion();
        batch.draw(currentRegion, hitbox.x, hitbox.y);
    }

    public void dispose() {
        stillImage.dispose();
        runningImage.dispose();
    }
}
