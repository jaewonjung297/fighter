package com.jaewonjung.fighter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class FighterScreen implements Screen {
    Game game;

    public FighterScreen (Game game) {
        this.game = game;
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
