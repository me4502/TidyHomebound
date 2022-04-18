package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Dialog extends InputAdapter {

    protected DialogHolder parent;

    public Dialog(DialogHolder parent) {
        this.parent = parent;
    }

    public abstract void render(SpriteBatch batch, AssetManager assetManager);

    public InputProcessor getInputProcessor() {
        return this;
    }

}
