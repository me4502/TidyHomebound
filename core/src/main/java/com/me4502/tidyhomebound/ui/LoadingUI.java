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

public class LoadingUI extends ScreenAdapter {

    private final TidyHomebound homebound;
    private final AssetManager assetManager;
    private final GameUI ui;

    private final SpriteBatch batch;

    public LoadingUI(TidyHomebound homebound, AssetManager assetManager, GameUI ui) {
        this.homebound = homebound;
        this.assetManager = assetManager;
        this.ui = ui;

        this.batch = new SpriteBatch();
        this.batch.getProjectionMatrix().setToOrtho2D(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);
    }

    String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Override
    public void render(float delta) {
        if (assetManager.update()) {
            homebound.setScreen(ui);
            return;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int dots = (int) (System.currentTimeMillis() / 1000 % 4);

        batch.begin();
        BitmapFont font = assetManager.get(Assets.DOGICA);
        font.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(font, "Loading...");
        font.draw(
            batch,
            "Loading" + repeat(".", dots),
            (TidyHomebound.GAME_WIDTH - layout.width) / 2f,
            (TidyHomebound.GAME_HEIGHT + layout.height) / 2f
        );
        batch.end();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        super.dispose();
    }
}
