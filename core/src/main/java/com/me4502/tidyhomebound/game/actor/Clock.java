package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class Clock extends Actor {

    private final GameState gameState;

    private final Sprite clock;
    private final Sprite clockHand;

    private final BitmapFont font;

    public Clock(AssetManager assetManager, GameState gameState, Vector2 position) {
        this.gameState = gameState;

        setPosition(position.x, position.y);

        this.clock = new Sprite(assetManager.get(Assets.CLOCK));
        this.clock.setPosition(position.x, position.y);
        this.clock.setScale(1.5f);

        this.clockHand = new Sprite(assetManager.get(Assets.CLOCK_HAND));
        this.clockHand.setPosition(position.x, position.y);
        this.clockHand.setScale(1.5f);

        font = assetManager.get(Assets.DOGICA);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        font.setColor(Color.BLACK);
        GlyphLayout layout = new GlyphLayout(font, "Day " + gameState.getDay());
        font.draw(batch, layout, getX() - 20 - layout.width, getY() + layout.height + 10);

        clock.draw(batch);
        clockHand.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        clockHand.setRotation((float) -(gameState.getNormalisedTimeOfDay() * 360f));
    }
}
