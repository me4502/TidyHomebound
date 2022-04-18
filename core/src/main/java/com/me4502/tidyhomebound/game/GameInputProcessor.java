package com.me4502.tidyhomebound.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.me4502.tidyhomebound.ui.GameUI;
import com.me4502.tidyhomebound.ui.dialog.PauseDialog;

public class GameInputProcessor extends InputAdapter {
    private final GameUI ui;
    private final GameState gameState;

    public GameInputProcessor(GameUI ui, GameState gameState) {
        this.ui = ui;
        this.gameState = gameState;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            ui.setDialog(new PauseDialog(ui));
            return true;
        }

        return super.keyDown(keycode);
    }
}
