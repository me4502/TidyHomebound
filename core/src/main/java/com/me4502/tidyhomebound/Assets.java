package com.me4502.tidyhomebound;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    // Textures
    // Tiles
    public static final AssetDescriptor<Texture> FLOOR_BASE = new AssetDescriptor<>("tiles/floor_base.png", Texture.class);

    public static final AssetDescriptor<Texture> WALL_BASE = new AssetDescriptor<>("tiles/wall_base.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_BASE = new AssetDescriptor<>("tiles/background_base.png", Texture.class);

    // Icons
    public static final AssetDescriptor<Texture> SPOON = new AssetDescriptor<>("icons/spoon.png", Texture.class);

    // Fonts
    public static final AssetDescriptor<BitmapFont> DOGICA = new AssetDescriptor<>("fonts/dogica.fnt", BitmapFont.class);

    /**
     * Triggers loads of any assets needed by the game.
     *
     * @param assetManager the asset manager
     */
    public static void loadGameAssets(AssetManager assetManager) {
        assetManager.load(FLOOR_BASE);
        assetManager.load(WALL_BASE);
        assetManager.load(BACKGROUND_BASE);
        assetManager.load(SPOON);
    }

    /**
     * Loads anything that is immediately needed on startup.
     *
     * @param assetManager the asset manager
     */
    public static void loadImmediateAssets(AssetManager assetManager) {
        assetManager.load(DOGICA);

        assetManager.finishLoading();
    }
}
