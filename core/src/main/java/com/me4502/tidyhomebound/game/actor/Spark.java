package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Spark extends Image {

    private static final float DURATION = 0.7f;
    private static final float FADE_DELAY = 0.4f;

    public Spark(Texture texture, Vector2 position) {
        super(texture);

        setSize(16f, 5f);
        setOrigin(8f, 2.5f);
        setTouchable(Touchable.disabled);
        setPosition(position.x - getWidth() / 2, position.y - getHeight() / 2);
        addAction(Actions.moveBy(0, -5, DURATION));
        addAction(Actions.sequence(Actions.delay(FADE_DELAY), Actions.fadeOut(DURATION - FADE_DELAY), Actions.removeActor(this)));
    }
}
