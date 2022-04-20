package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Toast extends Image {

    public Toast(Texture texture, Vector2 position) {
        super(texture);

        setTouchable(Touchable.disabled);
        setPosition(position.x - getWidth() / 2, position.y - getHeight() / 2);
        addAction(Actions.moveBy(0, 100, 2f));
        addAction(Actions.sequence(Actions.fadeOut(2f), Actions.removeActor(this)));
    }
}
