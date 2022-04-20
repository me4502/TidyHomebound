package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class Desk extends ImageChore {
    public Desk(AssetManager assetManager, GameState gameState, Vector2 homePosition) {
        super(gameState, homePosition, new ChoreAttributes(0.4, 0.4, 0.8, 0));

        setTextures(
            new TextureRegionDrawable(assetManager.get(Assets.DESK_GOOD)),
            new TextureRegionDrawable(assetManager.get(Assets.DESK_BAD)),
            new TextureRegionDrawable(assetManager.get(Assets.DESK_CRITICAL))
        );

        setSize(174, 120);
    }
}
