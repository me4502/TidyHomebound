package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.actor.chore.Chore;

public class Spoon extends Image {

    private final Vector2 homePosition;

    private Chore target;

    public Spoon(AssetManager assetManager, Vector2 homePosition, boolean borrowed) {
        super(borrowed ? assetManager.get(Assets.BORROWED_SPOON) : assetManager.get(Assets.SPOON));

        this.homePosition = homePosition;

        setPosition(homePosition.x, homePosition.y);
        setSize(32, 32);
    }

    public Vector2 getHomePosition() {
        return homePosition;
    }

    public void setTarget(Chore target) {
        this.target = target;
        if (target == null) {
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
            target.perform(this);
            if (target.isComplete()) {
                target.removeSpoon(this);
                setTarget(null);
            }
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
                return null;
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
                spoon.setTarget((Chore) target.getActor());
                ((Chore) target.getActor()).addSpoon(spoon);
            }
            super.dragStop(event, x, y, pointer, payload, target);
        }
    }
}
