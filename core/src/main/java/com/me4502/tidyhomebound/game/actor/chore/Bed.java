package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.Spoon;

public class Bed extends ImageChore {
    private static final double BED_CUTOFF_LIMIT = 5;
    private static final double BED_REWARD_CUTOFF = 1;

    private double timeInBed = 0;
    private double timeSinceReward = 0;
    private State state = State.GOOD;

    private final Drawable goodTexture;
    private final Drawable restingTexture;

    public Bed(AssetManager assetManager, GameState gameState, Vector2 homePosition) {
        super(gameState, homePosition, new ChoreAttributes(0, 0, 0, 0));

        goodTexture = new TextureRegionDrawable(assetManager.get(Assets.BED));
        restingTexture = new TextureRegionDrawable(assetManager.get(Assets.BED_RESTING));

        setDrawable(goodTexture);

        setSize(161, 95);
    }

    @Override
    public void act(float delta) {
        timeSinceReward += delta;

        if (assignedSpoons.isEmpty()) {
            timeInBed -= delta / 2;
            timeInBed = Math.max(0, timeInBed);
        } else {
            timeInBed += delta;
            if (timeSinceReward >= BED_REWARD_CUTOFF) {
                timeSinceReward = 0;
                gameState.modifySelfCare(0.1, getHomePosition());
            }
        }

        updateState();
    }

    private void updateState() {
        if (timeInBed > 0) {
            if (state != State.RESTING) {
                setDrawable(restingTexture);
                state = State.RESTING;
            }
        } else if (state != State.GOOD) {
            setDrawable(goodTexture);
            state = State.GOOD;
        }
    }

    @Override
    public void reward() {
        // No reward.
    }

    @Override
    public void perform(Spoon spoon, float delta) {
        // This does nothing
    }

    @Override
    public boolean isComplete() {
        return timeInBed >= BED_CUTOFF_LIMIT;
    }

    @Override
    public boolean canPerform() {
        return timeInBed == 0;
    }

    private enum State {
        GOOD,
        RESTING
    }
}
