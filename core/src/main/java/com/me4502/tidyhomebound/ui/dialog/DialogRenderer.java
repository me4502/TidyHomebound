package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.me4502.tidyhomebound.Assets;

public class DialogRenderer {

    private static final float SCALE_FACTOR = 1.5f;

    public static void drawDialog(AssetManager assetManager, SpriteBatch batch, int x, int y, int width, int height) {
        float scaleFactor = SCALE_FACTOR;

        TextureAtlas dialogAtlas = assetManager.get(Assets.DIALOG);
        NinePatch ninePatch = dialogAtlas.createPatch("dialog");
        ninePatch.draw(batch, x, y, 0, 0, width / scaleFactor, height / scaleFactor, scaleFactor, scaleFactor, 0);
    }
}
