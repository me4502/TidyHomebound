package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.Spoon;

import java.util.ArrayList;
import java.util.List;

public class ImageChore extends Image implements Chore {

    private static final float BAD_CUTOFF = 0.3f;
    private static final float CRITICAL_CUTOFF = 0.7f;

    private final GameState gameState;
    private final ChoreAttributes attributes;
    private final Vector2 homePosition;

    private float urgency;
    private float timeSinceDamage;
    private State state = State.GOOD;

    private final List<Spoon> assignedSpoons = new ArrayList<>();

    private Drawable goodTexture;
    private Drawable badTexture;
    private Drawable criticalTexture;

    public ImageChore(GameState gameState, Vector2 homePosition, ChoreAttributes attributes) {
        this.gameState = gameState;
        this.homePosition = homePosition;
        this.attributes = attributes;

        setPosition(homePosition.x, homePosition.y);
    }

    protected void setTextures(Drawable goodTexture, Drawable badTexture, Drawable criticalTexture) {
        this.goodTexture = goodTexture;
        this.badTexture = badTexture;
        this.criticalTexture = criticalTexture;

        // Start with the good one.
        setDrawable(goodTexture);
    }

    @Override
    public ChoreAttributes getAttributes() {
        return this.attributes;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timeSinceDamage += delta;

        if (assignedSpoons.isEmpty()) {
            modifyUrgency(attributes.getFrequency() * delta * 0.1);
        }

        updateState();

        if (state == State.CRITICAL && timeSinceDamage >= 5) {
            timeSinceDamage = 0;
            gameState.modifyHandling(-attributes.getImportance() * 0.1, homePosition);
        }
    }

    public float getUrgency() {
        return this.urgency;
    }

    @Override
    public boolean isComplete() {
        return urgency <= 0;
    }

    @Override
    public boolean canPerform() {
        return urgency >= BAD_CUTOFF;
    }

    private void updateState() {
        if (urgency > CRITICAL_CUTOFF) {
            if (state != State.CRITICAL) {
                setDrawable(criticalTexture);
                state = State.CRITICAL;
            }
        } else if (urgency > BAD_CUTOFF) {
            if (state != State.BAD) {
                setDrawable(badTexture);
                state = State.BAD;
            }
        } else if (state != State.GOOD) {
            setDrawable(goodTexture);
            state = State.GOOD;
        }
    }

    @Override
    public void reward() {
        gameState.modifySelfCare(attributes.getSelfCare(), homePosition);
        gameState.modifyHandling(0.05, homePosition);
    }

    @Override
    public void perform(Spoon spoon, float delta) {
        modifyUrgency((-1.0 + attributes.getDifficulty()) * delta);
    }

    private void modifyUrgency(double amount) {
        this.urgency += amount;
        this.urgency = Math.max(0.0f, Math.min(1.0f, this.urgency));
    }

    public void removeSpoon(Spoon spoon) {
        assignedSpoons.remove(spoon);
    }

    public void addSpoon(Spoon spoon) {
        assignedSpoons.add(spoon);
    }

    public void resetSpoons() {
        assignedSpoons.clear();
    }

    private enum State {
        GOOD,
        BAD,
        CRITICAL
    }
}
