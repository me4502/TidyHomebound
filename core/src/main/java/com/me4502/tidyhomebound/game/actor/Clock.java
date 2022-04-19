package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class Clock extends Actor {

    private final GameState gameState;

    private final Sprite clock;
    private final Sprite clockHand;

    public Clock(AssetManager assetManager, GameState gameState, Vector2 position) {
        this.gameState = gameState;

        this.clock = new Sprite(assetManager.get(Assets.CLOCK));
        this.clock.setPosition(position.x, position.y);
        this.clock.setScale(1.5f);

        this.clockHand = new Sprite(assetManager.get(Assets.CLOCK_HAND));
        this.clockHand.setPosition(position.x, position.y);
        this.clockHand.setScale(1.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        clock.draw(batch);
        clockHand.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        clockHand.setRotation((float) -(gameState.getNormalisedTimeOfDay() * 360f));
    }
}
