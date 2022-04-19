package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class ImageChoreDragTarget extends DragAndDrop.Target {
    private final ImageChore imageChore;

    private static final Color VALID_COLOR = Color.GREEN.cpy().lerp(Color.WHITE, 0.7f);
    private static final Color INVALID_COLOR = Color.RED.cpy().lerp(Color.WHITE, 0.7f);

    public ImageChoreDragTarget(ImageChore actor) {
        super(actor);

        this.imageChore = actor;
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        boolean canPerform = imageChore.canPerform();
        imageChore.setColor(canPerform ? VALID_COLOR : INVALID_COLOR);
        return canPerform;
    }

    @Override
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        super.reset(source, payload);
        imageChore.setColor(Color.WHITE);
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
    }
}
