package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me4502.tidyhomebound.game.GameState;

public class EndOfDayDialog extends Dialog {

    private final GameState gameState;
    private final Stage stage;
    private final InputProcessor multiplexedInput;

    public EndOfDayDialog(DialogHolder parent, GameState gameState) {
        super(parent);
        this.gameState = gameState;
        this.stage = new Stage();
        this.multiplexedInput = new InputMultiplexer(stage, this);
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return this.multiplexedInput;
    }
}
