package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me4502.tidyhomebound.Assets;

public class Spoon extends Image {

    private final Vector2 homePosition;

    public Spoon(AssetManager assetManager, Vector2 homePosition) {
        super(assetManager.get(Assets.SPOON));

        this.homePosition = homePosition;

        setPosition(homePosition.x, homePosition.y);
        setSize(32, 32);
    }

    public Vector2 getHomePosition() {
        return homePosition;
    }
}
