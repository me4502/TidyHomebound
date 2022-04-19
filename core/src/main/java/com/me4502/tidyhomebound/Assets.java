package com.me4502.tidyhomebound;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    // Textures
    // Tiles
    public static final AssetDescriptor<Texture> FLOOR_BASE = new AssetDescriptor<>("tiles/floor_base.png", Texture.class);

    public static final AssetDescriptor<Texture> WALL_BASE = new AssetDescriptor<>("tiles/wall_base.png", Texture.class);
    public static final AssetDescriptor<Texture> DOOR_BASE = new AssetDescriptor<>("tiles/door_base.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_BASE = new AssetDescriptor<>("tiles/background_base.png", Texture.class);

    // Icons
    public static final AssetDescriptor<Texture> SPOON = new AssetDescriptor<>("icons/spoon.png", Texture.class);
    public static final AssetDescriptor<Texture> BORROWED_SPOON = new AssetDescriptor<>("icons/borrowed_spoon.png", Texture.class);

    // UI
    public static final AssetDescriptor<TextureAtlas> DIALOG = new AssetDescriptor<>("ui/dialog.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> COPING_BAR_FULL = new AssetDescriptor<>("ui/coping_bar_full.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> COPING_BAR_EMPTY = new AssetDescriptor<>("ui/coping_bar_empty.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Texture> CLOCK = new AssetDescriptor<>("ui/clock.png", Texture.class);
    public static final AssetDescriptor<Texture> CLOCK_HAND = new AssetDescriptor<>("ui/clock_hand.png", Texture.class);

    // Furniture
    public static final AssetDescriptor<Texture> PLANT_POT_GOOD = new AssetDescriptor<>("furniture/plant_pot_good.png", Texture.class);

    public static final AssetDescriptor<Texture> FRIDGE_GOOD = new AssetDescriptor<>("furniture/fridge_good.png", Texture.class);
    public static final AssetDescriptor<Texture> FRIDGE_BAD = new AssetDescriptor<>("furniture/fridge_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> FRIDGE_CRITICAL = new AssetDescriptor<>("furniture/fridge_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> SINK_GOOD = new AssetDescriptor<>("furniture/sink_good.png", Texture.class);
    public static final AssetDescriptor<Texture> SINK_BAD = new AssetDescriptor<>("furniture/sink_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> SINK_CRITICAL = new AssetDescriptor<>("furniture/sink_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> OVEN_GOOD = new AssetDescriptor<>("furniture/oven_good.png", Texture.class);
    public static final AssetDescriptor<Texture> OVEN_BAD = new AssetDescriptor<>("furniture/oven_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> OVEN_CRITICAL = new AssetDescriptor<>("furniture/oven_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> BED = new AssetDescriptor<>("furniture/bed.png", Texture.class);

    // Fonts
    public static final AssetDescriptor<BitmapFont> DOGICA = new AssetDescriptor<>("fonts/dogica.16.fnt", BitmapFont.class);

    /**
     * Triggers loads of any assets needed by the game.
     *
     * @param assetManager the asset manager
     */
    public static void loadGameAssets(AssetManager assetManager) {
        assetManager.load(FLOOR_BASE);
        assetManager.load(WALL_BASE);
        assetManager.load(DOOR_BASE);
        assetManager.load(BACKGROUND_BASE);
        assetManager.load(SPOON);
        assetManager.load(BORROWED_SPOON);

        assetManager.load(DIALOG);
        assetManager.load(COPING_BAR_FULL);
        assetManager.load(COPING_BAR_EMPTY);
        assetManager.load(CLOCK);
        assetManager.load(CLOCK_HAND);

        assetManager.load(PLANT_POT_GOOD);
        assetManager.load(FRIDGE_GOOD);
        assetManager.load(FRIDGE_BAD);
        assetManager.load(FRIDGE_CRITICAL);
        assetManager.load(SINK_GOOD);
        assetManager.load(SINK_BAD);
        assetManager.load(SINK_CRITICAL);
        assetManager.load(OVEN_GOOD);
        assetManager.load(OVEN_BAD);
        assetManager.load(OVEN_CRITICAL);

        assetManager.load(BED);
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
