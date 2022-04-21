package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class Sink extends ImageChore {
    public Sink(AssetManager assetManager, GameState gameState, Vector2 homePosition) {
        super(gameState, homePosition, new ChoreAttributes(0.6, 0.6, 0.7, 0));

        setTextures(
            new TextureRegionDrawable(assetManager.get(Assets.SINK_GOOD)),
            new TextureRegionDrawable(assetManager.get(Assets.SINK_BAD)),
            new TextureRegionDrawable(assetManager.get(Assets.SINK_CRITICAL))
        );

        setSize(128, 125);
    }
}
