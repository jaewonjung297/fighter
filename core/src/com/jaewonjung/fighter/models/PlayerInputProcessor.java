package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Arrays;

public class PlayerInputProcessor implements InputProcessor {

    private Player p;
    private long jumpTime;
    private long leftTime;
    private long rightTime;

    public PlayerInputProcessor(Player p) {
        this.p = p;
    }

    public boolean keyDown (int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                if (p.jumpsLeft > 0) {
                    p.onPlatform = false;
                    p.velocityY = p.jumpVelocity;
                    p.jumpsLeft -= 1;
                    p.keyTime[0] = TimeUtils.millis();
                    break;
                }
            case Input.Keys.DOWN:
                long currentTime = TimeUtils.millis();
                if (currentTime - p.keyTime[0] < 200) {
                    p.platformPass = true;
                }
                p.keyTime[0] = currentTime;
                break;
            case Input.Keys.LEFT:
                //p.velocityX = Math.min(0, p.velocityX - 400);
                p.velocityX = -400;
                p.facingDirection = -1;
                p.playerStatus = PlayerStatus.RUNNING;
                p.movingDirection = -1;
                p.keyTime[2] = TimeUtils.millis();
                break;
            case Input.Keys.RIGHT:
                //p.velocityX += 400;
                p.facingDirection = 1;
                p.velocityX = 400;
                p.movingDirection = 1;
                p.playerStatus = PlayerStatus.RUNNING;
                p.keyTime[3] = TimeUtils.millis();
            default:
                return false;
        }
        return true;
    }

    public boolean keyUp (int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                float jumpDuration = TimeUtils.millis() - p.keyTime[0];
                if (jumpDuration < 300) {
                    p.velocityY = Math.min(0, p.velocityY - 80);
                }
                break;
            case Input.Keys.DOWN:

                break;
            case Input.Keys.LEFT:
                float leftDuration = TimeUtils.millis() - p.keyTime[2];
                if (leftDuration < 300) {
                    p.velocityX = Math.min(0, p.velocityX + 200);
                }
                if (p.movingDirection == -1) {
                    p.movingDirection = 0;
                }
                break;
            case Input.Keys.RIGHT:
                float rightDuration = TimeUtils.millis() - p.keyTime[3];
                if (rightDuration < 300) {
                    p.velocityX = Math.max(0, p.velocityX - 200);
                }
                if (p.movingDirection == 1) {
                    p.movingDirection = 0;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }

    public boolean touchCancelled(int x, int y, int pointer, int button) {
        return false;
    }
}
