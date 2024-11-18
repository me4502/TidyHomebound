package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;

public class PauseDialog extends Dialog {
    private final long startTime;

    public PauseDialog(DialogHolder parent) {
        super(parent);

        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        if ((System.currentTimeMillis() - startTime) / 1000 % 2 == 1) {
            return;
        }

        batch.begin();
        BitmapFont font = assetManager.get(Assets.DOGICA);
        font.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(font, "PAUSED");
        font.draw(
            batch,
            layout,
            (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
            (TidyHomebound.GAME_HEIGHT + layout.height) / 2f
        );
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.PAUSE) {
            this.parent.setDialog(null);
            return true;
        }

        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            this.parent.setDialog(null);
            return true;
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
