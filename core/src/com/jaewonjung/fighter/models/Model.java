package com.jaewonjung.fighter.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public interface Model {
    void update(ArrayList<Rectangle> platforms);
    void render(SpriteBatch batch);
    void dispose();

}
