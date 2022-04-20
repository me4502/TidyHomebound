package com.me4502.tidyhomebound.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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

    private float playAgainTimer = 0;

    public GameOverScreen(TidyHomebound homebound, AssetManager assetManager, GameState gameState) {
        this.homebound = homebound;
        this.assetManager = assetManager;
        this.gameState = gameState;

        this.batch = new SpriteBatch();
        this.batch.getProjectionMatrix().setToOrtho2D(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);
    }

    @Override
    public void show() {
        playAgainTimer = 0;

        Gdx.input.setInputProcessor(new ClickListener());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        playAgainTimer += delta;

        batch.begin();
        BitmapFont font = assetManager.get(Assets.DOGICA);
        font.setColor(Color.WHITE);

        GlyphLayout layout = new GlyphLayout(font, "Game Over");
        font.draw(
            batch,
            layout,
            (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
            (TidyHomebound.GAME_HEIGHT + layout.height + 12) / 2f
        );

        layout.setText(font, "You lost on day " + gameState.getDay());
        font.draw(
            batch,
            layout,
            (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
            (TidyHomebound.GAME_HEIGHT - layout.height - 12) / 2f
        );

        if (playAgainTimer > 1) {
            var alphaColor = Color.WHITE.cpy();
            alphaColor.a = Math.min(1, playAgainTimer - 1.0f);
            font.setColor(alphaColor);
            layout.setText(font, "Click to play again");
            font.draw(
                batch,
                layout,
                (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
                (TidyHomebound.GAME_HEIGHT - layout.height - 104) / 2f
            );
            font.setColor(Color.WHITE);
        }

        batch.end();
    }

    class ClickListener extends InputAdapter {

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (playAgainTimer >= 2) {
                homebound.setScreen(homebound.getUi());
            }
            return true;
        }
    }
}
