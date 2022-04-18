package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameState;

public class EndOfDayDialog extends Dialog {

    private final GameState gameState;
    private final Stage stage;
    private final InputProcessor multiplexedInput;

    public EndOfDayDialog(DialogHolder parent, GameState gameState) {
        super(parent);
        this.gameState = gameState;
        this.stage = new Stage(new StretchViewport(TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT));
        this.multiplexedInput = new InputMultiplexer(stage, this);
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        batch.begin();
        DialogRenderer.drawDialog(
            assetManager,
            batch,
            5,
            5,
            TidyHomebound.GAME_WIDTH - 10,
            TidyHomebound.GAME_HEIGHT - 10
        );
        BitmapFont font = assetManager.get(Assets.DOGICA);
        GlyphLayout layout = new GlyphLayout(font, "End of Day " + gameState.getDay());
        font.setColor(Color.BLACK);
        font.draw(batch, layout, (TidyHomebound.GAME_WIDTH - layout.width) / 2, TidyHomebound.GAME_HEIGHT - 35);
        batch.end();

        this.stage.act();
        this.stage.draw();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return this.multiplexedInput;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            gameState.startNewDay();
            return true;
        }

        return super.keyDown(keycode);
    }
}
