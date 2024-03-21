package com.jaewonjung.fighter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.jaewonjung.fighter.Fighter;

public abstract class FighterScreen implements Screen {
    final Fighter game;
    public float time;

    public FighterScreen (final Fighter game) {
        this.game = game;
        this.time = 0f;
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}
