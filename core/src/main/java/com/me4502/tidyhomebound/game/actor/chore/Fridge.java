package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class Fridge extends ImageChore {
    public Fridge(AssetManager assetManager, GameState gameState, Vector2 homePosition) {
        super(gameState, homePosition, new ChoreAttributes(0.4, 0.3, 0.8, 0));

        setTextures(
            new TextureRegionDrawable(assetManager.get(Assets.FRIDGE_GOOD)),
            new TextureRegionDrawable(assetManager.get(Assets.FRIDGE_BAD)),
            new TextureRegionDrawable(assetManager.get(Assets.FRIDGE_CRITICAL))
        );

        setSize(128, 192);
    }
}
