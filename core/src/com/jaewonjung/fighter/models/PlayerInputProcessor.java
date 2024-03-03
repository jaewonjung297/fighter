package com.jaewonjung.fighter.models;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.TimeUtils;

public class PlayerInputProcessor implements InputProcessor {

    private Player p;
    private long jumpTime;

    public PlayerInputProcessor(Player p) {
        this.p = p;
        jumpTime = -1;
    }

    public boolean keyDown (int keycode) {
        if (keycode == Input.Keys.UP && p.jumpsLeft > 0) {
            p.onPlatform = false;
            p.velocityY = p.jumpVelocity;
            p.jumpsLeft -= 1;
            jumpTime = TimeUtils.millis();
        }
        return true;
    }

    public boolean keyUp (int keycode) {
        float jumpDuration = TimeUtils.millis() - jumpTime;
        if (keycode == Input.Keys.UP && jumpDuration < 300) {
            p.velocityY -= 500;
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
