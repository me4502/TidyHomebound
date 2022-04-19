package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class PlantPot extends ImageChore {

    public PlantPot(AssetManager assetManager, GameState gameState, Vector2 homePosition) {
        super(assetManager, gameState, homePosition, new ChoreAttributes(0.5, 0.3, 0.5, 0.2));

        setDrawable(new TextureRegionDrawable(assetManager.get(Assets.PLANT_POT_GOOD)));
        setSize(32, 32);
    }
}
