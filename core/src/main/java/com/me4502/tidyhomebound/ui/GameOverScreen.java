package com.me4502.tidyhomebound.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameState;

public class GameOverScreen extends ScreenAdapter {

    private final TidyHomebound homebound;
    private final AssetManager assetManager;
    private final GameState gameState;

    private final SpriteBatch batch;

    public GameOverScreen(TidyHomebound homebound, AssetManager assetManager, GameState gameState) {
        this.homebound = homebound;
        this.assetManager = assetManager;
        this.gameState = gameState;

        this.batch = new SpriteBatch();
        this.batch.getProjectionMatrix().setToOrtho2D(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        BitmapFont font = assetManager.get(Assets.DOGICA);
        font.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(font, "Game Over");
        font.draw(
            batch,
            layout,
            (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
            (TidyHomebound.GAME_HEIGHT + layout.height) / 2f
        );
        batch.end();
    }
}
