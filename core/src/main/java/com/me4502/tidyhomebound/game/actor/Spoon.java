package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.chore.Chore;

public class Spoon extends Image {

    private final GameState gameState;
    private final Vector2 homePosition;
    private final boolean borrowed;

    private Chore target;

    public Spoon(AssetManager assetManager, GameState gameState, Vector2 homePosition, boolean borrowed) {
        super(borrowed ? assetManager.get(Assets.BORROWED_SPOON) : assetManager.get(Assets.SPOON));

        this.gameState = gameState;
        this.homePosition = homePosition;
        this.borrowed = borrowed;

        setPosition(homePosition.x, homePosition.y);
        setSize(32, 32);
    }

    public Vector2 getHomePosition() {
        return homePosition;
    }

    public boolean isBorrowed() {
        return this.borrowed;
    }

    public void setTarget(Chore target, boolean snapBack) {
        this.target = target;
        if (target == null && snapBack) {
            setPosition(homePosition.x, homePosition.y);
        }
    }

    public Chore getTarget() {
        return this.target;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (target != null) {
            target.perform(this, delta);
            if (target.isComplete()) {
                target.reward();
                detatchSpoon(true);
            }
        }
    }

    public void detatchSpoon(boolean snapBack) {
        target.removeSpoon(this);
        setTarget(null, snapBack);
        if (isBorrowed()) {
            setVisible(false);
            gameState.logSpoonBorrowed();
        }
    }

    public static class SpoonDragSource extends DragAndDrop.Source {
        private final Spoon spoon;

        public SpoonDragSource(Spoon spoon) {
            super(spoon);

            this.spoon = spoon;
        }

        @Override
        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
            if (spoon.getTarget() != null) {
                if (spoon.isBorrowed()) {
                    // Can't drag a borrowed spoon
                    return null;
                }
                spoon.detatchSpoon(false);
            }
            DragAndDrop.Payload payload = new DragAndDrop.Payload();
            payload.setDragActor(spoon);
            return payload;
        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
            if (target == null) {
                spoon.setPosition(spoon.getHomePosition().x, spoon.getHomePosition().y);
            } else if (target.getActor() instanceof Chore) {
                spoon.setTarget((Chore) target.getActor(), true);
                ((Chore) target.getActor()).addSpoon(spoon);
            }
            super.dragStop(event, x, y, pointer, payload, target);
        }
    }
}
