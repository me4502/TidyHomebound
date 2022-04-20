package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;

public class StartDialog extends Dialog {
    public StartDialog(DialogHolder parent) {
        super(parent);
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        batch.begin();
        BitmapFont font = assetManager.get(Assets.DOGICA);
        font.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(font, "Click to Start");
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
        if (keycode == Input.Keys.ENTER) {
            this.parent.setDialog(null);
            return true;
        }

        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.parent.setDialog(null);
        return true;
    }
}
