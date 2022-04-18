package com.me4502.tidyhomebound.ui;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameState;

public class GameOverScreen extends ScreenAdapter {

    private final TidyHomebound homebound;
    private final AssetManager assetManager;
    private final GameState gameState;

    public GameOverScreen(TidyHomebound homebound, AssetManager assetManager, GameState gameState) {
        this.homebound = homebound;
        this.assetManager = assetManager;
        this.gameState = gameState;
    }
}
