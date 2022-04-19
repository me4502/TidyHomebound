package com.me4502.tidyhomebound.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class DebugDragAndDropSource extends DragAndDrop.Source {
    public DebugDragAndDropSource(Actor actor) {
        super(actor);
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        DragAndDrop.Payload payload = new DragAndDrop.Payload();
        payload.setDragActor(getActor());
        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        super.dragStop(event, x, y, pointer, payload, target);

        System.out.println("Actor dragged to: " + getActor().getX() + "," + getActor().getY());
    }
}
