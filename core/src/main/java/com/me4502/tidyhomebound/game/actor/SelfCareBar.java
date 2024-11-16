package com.me4502.tidyhomebound.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameState;

public class SelfCareBar extends Actor {

    private static final float SCALE_FACTOR = 1.5f;

    private final GameState gameState;
    private final Vector2 position;
    private final float width;
    private final float height;

    private final NinePatch emptyNinePatch, fullNinePatch;
    private final BitmapFont font;

    public SelfCareBar(AssetManager assetManager, GameState gameState, Vector2 position, float width, float height) {
        this.gameState = gameState;
        this.position = position;
        this.width = width;
        this.height = height;

        TextureAtlas emptyBar = assetManager.get(Assets.COPING_BAR_EMPTY);
        emptyNinePatch = emptyBar.createPatch("coping_bar_empty");

        TextureAtlas fullBar = assetManager.get(Assets.COPING_BAR_FULL);
        fullNinePatch = fullBar.createPatch("coping_bar_full");

        font = assetManager.get(Assets.DOGICA);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float scaleFactor = SCALE_FACTOR;

        font.setColor(Color.BLACK);
        GlyphLayout layout = new GlyphLayout(font, "Self Care");
        font.draw(batch, layout, position.x + 5, position.y + layout.height + 20);

        batch.setColor(Color.WHITE);

        emptyNinePatch.draw(
            batch,
            position.x,
            position.y - 16,
            0,
            0,
            width / scaleFactor,
            height / scaleFactor,
            scaleFactor,
            scaleFactor,
            0
        );
        batch.flush();

        Rectangle scissorRectangle = new Rectangle(
                position.x * TidyHomebound.GLOBAL_SCALE,
                0, // (position.y - 16) * TidyHomebound.GLOBAL_SCALE,
                ((float) (width * gameState.getSelfCare())) * TidyHomebound.GLOBAL_SCALE,
                Gdx.graphics.getHeight() // height * TidyHomebound.GLOBAL_SCALE
        );

        if (ScissorStack.pushScissors(scissorRectangle)) {
            fullNinePatch.draw(
                batch,
                position.x,
                position.y - 16,
                0,
                0,
                width / scaleFactor,
                height / scaleFactor,
                scaleFactor,
                scaleFactor,
                0
            );
            batch.flush();
            ScissorStack.popScissors();
        }
    }
}
