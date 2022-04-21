package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Toast extends Image {

    private static final float TOAST_DURATION = 3f;
    private static final float FADE_DELAY = 1f;

    public Toast(Texture texture, Vector2 position) {
        super(texture);

        setTouchable(Touchable.disabled);
        setPosition(position.x - getWidth() / 2, position.y - getHeight() / 2);
        addAction(Actions.moveBy(0, 100, TOAST_DURATION));
        addAction(Actions.sequence(Actions.delay(FADE_DELAY), Actions.fadeOut(TOAST_DURATION - FADE_DELAY), Actions.removeActor(this)));
    }
}
