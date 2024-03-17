package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.*;

public class Player {
    private final Texture stillImage;
    private final Texture runningImage;
    private final TextureRegion currentRegion;
    public final Rectangle hitbox;
    public float velocityX;
    public float velocityY;
    public float gravity = (float) - 2000;
    public float jumpVelocity = 800;
    public boolean onPlatform;
    public HashMap<Rectangle, Boolean> canPass;
    public boolean platformPass;
    public int jumpsLeft = 2;
    public int health;
    public int facingDirection;
    public int movingDirection;
    private boolean inAttack = false;
    public long[] keyTime;

    public PlayerStatus playerStatus;
    public Player() {
        this.stillImage = new Texture("stick.png");
        this.runningImage = new Texture("stick_running.png");
        this.currentRegion = new TextureRegion(stillImage);
        this.playerStatus = PlayerStatus.STILL;
        this.hitbox = new Rectangle(368, 0, 30, 64);
        this.velocityY = 0;
        this.velocityX = 0;
        this.onPlatform = false;
        this.canPass = new HashMap<>();
        this.platformPass = false;
        this.health = 100;
        this.facingDirection = 0;
        this.movingDirection = 0;
        this.keyTime = new long[4];
        Arrays.fill(this.keyTime, -1);
    }
    public void update(ArrayList<Rectangle> platforms) {
        //update animation
        onPlatform = false;
        for (Rectangle platform: platforms) {
            canPass.putIfAbsent(platform, false);
            if (hitbox.y <= platform.y) {
                canPass.put(platform, true);
            }
            else {
                canPass.put(platform, false);
            }
            if (hitbox.overlaps(platform)) {
                if (!onPlatform && !platformPass && !canPass.get(platform) && velocityY < 0 && hitbox.y > platform.y) {
                    velocityY = 0;
                    hitbox.y = platform.y + platform.height - 1;
                    onPlatform = true;
                    jumpsLeft = 2;
                }
            }
        }
        if (!onPlatform) {
            velocityY += gravity * Gdx.graphics.getDeltaTime();
            hitbox.y += velocityY * Gdx.graphics.getDeltaTime();
        }

        hitbox.x += velocityX * Gdx.graphics.getDeltaTime();

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
