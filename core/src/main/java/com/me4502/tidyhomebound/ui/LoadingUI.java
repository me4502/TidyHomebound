package com.me4502.tidyhomebound.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
        batch.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout(assetManager.get(Assets.DOGICA), "Loading...");
        assetManager.get(Assets.DOGICA).draw(
            batch,
            "Loading" + ".".repeat(dots),
            (Gdx.graphics.getWidth() - layout.width) / 2f,
            (Gdx.graphics.getHeight() + layout.height) / 2f
        );
        batch.end();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        super.dispose();
    }
}
