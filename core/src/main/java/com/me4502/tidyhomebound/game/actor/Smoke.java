package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

public class Smoke extends Image {

    private static final Random RANDOM = new Random();

    private static final float DURATION = 1.5f;

    public Smoke(Texture texture, Vector2 position) {
        super(texture);

        setTouchable(Touchable.disabled);
        setPosition(position.x - getWidth() / 2, position.y - getHeight() / 2);
        addAction(Actions.moveBy(0, 100, DURATION));
        addAction(Actions.rotateBy(RANDOM.nextFloat() * 360 - 180, DURATION));
        addAction(Actions.sequence(Actions.fadeOut(DURATION), Actions.removeActor(this)));
    }
}
