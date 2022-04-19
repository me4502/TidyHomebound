package com.me4502.tidyhomebound.game.actor.chore;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class ImageChoreDragTarget extends DragAndDrop.Target {
    private final ImageChore imageChore;

    public ImageChoreDragTarget(ImageChore actor) {
        super(actor);

        this.imageChore = actor;
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        return imageChore.canPerform();
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
    }
}
