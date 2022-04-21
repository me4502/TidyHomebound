package com.me4502.tidyhomebound;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    // Textures
    // Tiles
    public static final AssetDescriptor<Texture> FLOOR_BASE = new AssetDescriptor<>("tiles/floor_base.png", Texture.class);
    public static final AssetDescriptor<Texture> FLOOR_LIGHT_DIRT = new AssetDescriptor<>("tiles/floor_light_dirt.png", Texture.class);
    public static final AssetDescriptor<Texture> FLOOR_HEAVY_DIRT = new AssetDescriptor<>("tiles/floor_heavy_dirt.png", Texture.class);

    public static final AssetDescriptor<Texture> WALL_BASE = new AssetDescriptor<>("tiles/wall_base.png", Texture.class);
    public static final AssetDescriptor<Texture> WALL_WINDOW = new AssetDescriptor<>("tiles/wall_window.png", Texture.class);
    public static final AssetDescriptor<Texture> DOOR_BASE = new AssetDescriptor<>("tiles/door_base.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUND_BASE = new AssetDescriptor<>("tiles/background_base.png", Texture.class);

    // Icons
    public static final AssetDescriptor<Texture> SPOON = new AssetDescriptor<>("icons/spoon.png", Texture.class);
    public static final AssetDescriptor<Texture> BORROWED_SPOON = new AssetDescriptor<>("icons/borrowed_spoon.png", Texture.class);
    public static final AssetDescriptor<Texture> SMOKE = new AssetDescriptor<>("icons/smoke.png", Texture.class);

    public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<>("icons/logo.png", Texture.class);

    // UI
    public static final AssetDescriptor<TextureAtlas> DIALOG = new AssetDescriptor<>("ui/dialog.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> COPING_BAR_FULL = new AssetDescriptor<>("ui/coping_bar_full.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> COPING_BAR_EMPTY = new AssetDescriptor<>("ui/coping_bar_empty.9.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Texture> CLOCK = new AssetDescriptor<>("ui/clock.png", Texture.class);
    public static final AssetDescriptor<Texture> CLOCK_HAND = new AssetDescriptor<>("ui/clock_hand.png", Texture.class);
    public static final AssetDescriptor<Texture> TOAST_SELFCARE_GAIN = new AssetDescriptor<>("ui/toast_selfcare_gain.png", Texture.class);
    public static final AssetDescriptor<Texture> TOAST_POSITIVE = new AssetDescriptor<>("ui/toast_positive.png", Texture.class);
    public static final AssetDescriptor<Texture> TOAST_NEGATIVE = new AssetDescriptor<>("ui/toast_negative.png", Texture.class);

    // Furniture
    public static final AssetDescriptor<Texture> PLANT_POT_GOOD = new AssetDescriptor<>("furniture/plant_pot_good.png", Texture.class);
    public static final AssetDescriptor<Texture> PLANT_POT_BAD = new AssetDescriptor<>("furniture/plant_pot_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> PLANT_POT_CRITICAL = new AssetDescriptor<>("furniture/plant_pot_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> BIN_GOOD = new AssetDescriptor<>("furniture/bin_good.png", Texture.class);
    public static final AssetDescriptor<Texture> BIN_BAD = new AssetDescriptor<>("furniture/bin_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> BIN_CRITICAL = new AssetDescriptor<>("furniture/bin_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> FRIDGE_GOOD = new AssetDescriptor<>("furniture/fridge_good.png", Texture.class);
    public static final AssetDescriptor<Texture> FRIDGE_BAD = new AssetDescriptor<>("furniture/fridge_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> FRIDGE_CRITICAL = new AssetDescriptor<>("furniture/fridge_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> SINK_GOOD = new AssetDescriptor<>("furniture/sink_good.png", Texture.class);
    public static final AssetDescriptor<Texture> SINK_BAD = new AssetDescriptor<>("furniture/sink_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> SINK_CRITICAL = new AssetDescriptor<>("furniture/sink_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> OVEN_GOOD = new AssetDescriptor<>("furniture/oven_good.png", Texture.class);
    public static final AssetDescriptor<Texture> OVEN_BAD = new AssetDescriptor<>("furniture/oven_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> OVEN_CRITICAL = new AssetDescriptor<>("furniture/oven_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> DESK_GOOD = new AssetDescriptor<>("furniture/desk_good.png", Texture.class);
    public static final AssetDescriptor<Texture> DESK_BAD = new AssetDescriptor<>("furniture/desk_bad.png", Texture.class);
    public static final AssetDescriptor<Texture> DESK_CRITICAL = new AssetDescriptor<>("furniture/desk_critical.png", Texture.class);

    public static final AssetDescriptor<Texture> VACUUM = new AssetDescriptor<>("furniture/vacuum.png", Texture.class);

    public static final AssetDescriptor<Texture> BED = new AssetDescriptor<>("furniture/bed.png", Texture.class);
    public static final AssetDescriptor<Texture> BED_RESTING = new AssetDescriptor<>("furniture/bed_resting.png", Texture.class);

    // Fonts
    public static final AssetDescriptor<BitmapFont> DOGICA = new AssetDescriptor<>("fonts/dogica.16.fnt", BitmapFont.class);

    // Audio
    public static final AssetDescriptor<Sound> BACKGROUND_MUSIC = new AssetDescriptor<>("audio/safe_at_home.ogg", Sound.class);

    /**
     * Triggers loads of any assets needed by the game.
     *
     * @param assetManager the asset manager
     */
    public static void loadGameAssets(AssetManager assetManager) {
        assetManager.load(FLOOR_BASE);
        assetManager.load(FLOOR_LIGHT_DIRT);
        assetManager.load(FLOOR_HEAVY_DIRT);
        assetManager.load(WALL_BASE);
        assetManager.load(WALL_WINDOW);
        assetManager.load(DOOR_BASE);
        assetManager.load(BACKGROUND_BASE);
        assetManager.load(SPOON);
        assetManager.load(BORROWED_SPOON);

        assetManager.load(DIALOG);
        assetManager.load(COPING_BAR_FULL);
        assetManager.load(COPING_BAR_EMPTY);
        assetManager.load(CLOCK);
        assetManager.load(CLOCK_HAND);
        assetManager.load(SMOKE);

        assetManager.load(PLANT_POT_GOOD);
        assetManager.load(PLANT_POT_BAD);
        assetManager.load(PLANT_POT_CRITICAL);
        assetManager.load(BIN_GOOD);
        assetManager.load(BIN_BAD);
        assetManager.load(BIN_CRITICAL);
        assetManager.load(FRIDGE_GOOD);
        assetManager.load(FRIDGE_BAD);
        assetManager.load(FRIDGE_CRITICAL);
        assetManager.load(SINK_GOOD);
        assetManager.load(SINK_BAD);
        assetManager.load(SINK_CRITICAL);
        assetManager.load(OVEN_GOOD);
        assetManager.load(OVEN_BAD);
        assetManager.load(OVEN_CRITICAL);
        assetManager.load(DESK_GOOD);
        assetManager.load(DESK_BAD);
        assetManager.load(DESK_CRITICAL);

        assetManager.load(VACUUM);

        assetManager.load(BED);
        assetManager.load(BED_RESTING);

        assetManager.load(TOAST_SELFCARE_GAIN);
        assetManager.load(TOAST_POSITIVE);
        assetManager.load(TOAST_NEGATIVE);

        assetManager.load(BACKGROUND_MUSIC);
    }

    /**
     * Loads anything that is immediately needed on startup.
     *
     * @param assetManager the asset manager
     */
    public static void loadImmediateAssets(AssetManager assetManager) {
        assetManager.load(DOGICA);
        assetManager.load(LOGO);

        assetManager.finishLoading();
    }
}
