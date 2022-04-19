package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.Spoon;

import java.util.ArrayList;
import java.util.List;

public class ImageChore extends Image implements Chore {

    private final AssetManager assetManager;
    private final GameState gameState;
    private final ChoreAttributes attributes;
    private final Vector2 homePosition;

    private float urgency;

    private final List<Spoon> assignedSpoons = new ArrayList<>();

    public ImageChore(AssetManager assetManager, GameState gameState, Vector2 homePosition, ChoreAttributes attributes) {
        this.assetManager = assetManager;
        this.gameState = gameState;
        this.homePosition = homePosition;
        this.attributes = attributes;

        setPosition(homePosition.x, homePosition.y);
    }

    @Override
    public ChoreAttributes getAttributes() {
        return this.attributes;
    }

    public Vector2 getHomePosition() {
        return this.homePosition;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (assignedSpoons.isEmpty()) {
            this.urgency += attributes.getFrequency();
        }
    }

    public float getUrgency() {
        return this.urgency;
    }

    @Override
    public boolean isComplete() {
        return urgency < 0;
    }

    @Override
    public boolean canPerform() {
        return urgency > 0.1;
    }

    @Override
    public void perform(Spoon spoon) {
        this.urgency -= (1.0 - attributes.getDifficulty());
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
}
