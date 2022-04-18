package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me4502.tidyhomebound.Assets;

public class PauseDialog extends Dialog {
    public PauseDialog(DialogHolder parent) {
        super(parent);
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        if (System.currentTimeMillis() / 1000 % 2 == 0) {
            return;
        }

        batch.begin();
        batch.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(assetManager.get(Assets.DOGICA), "PAUSED");
        assetManager.get(Assets.DOGICA).draw(
            batch,
            layout,
            (Gdx.graphics.getWidth() - layout.width) / 2f,
            (Gdx.graphics.getHeight() + layout.height) / 2f
        );
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            this.parent.setDialog(null);
            return true;
        }

        return super.keyDown(keycode);
    }
}
