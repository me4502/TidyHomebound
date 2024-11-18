package com.me4502.tidyhomebound.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.me4502.tidyhomebound.TidyHomebound;
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
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.PAUSE) {
            ui.setDialog(new PauseDialog(ui));
            return true;
        }

        if (TidyHomebound.DEBUG_MODE) {
            // Debug keys
            if (keycode == Input.Keys.NUM_1) {
                gameState.modifyHandling(100, null);
                return true;
            } else if (keycode == Input.Keys.NUM_2) {
                gameState.modifySelfCare(100, null);
                return true;
            } else if (keycode == Input.Keys.NUM_3) {
                gameState.getChores().forEach(chore -> chore.perform(null, 10));
                return true;
            } else if (keycode == Input.Keys.NUM_4) {
                gameState.startNewDay();
                return true;
            } else if (keycode == Input.Keys.EQUALS) {
                gameState.update(10f);
                return true;
            } else if (keycode == Input.Keys.GRAVE) {
                ui.getStage().setDebugAll(!ui.getStage().isDebugAll());
                return true;
            }
        }

        return super.keyDown(keycode);
    }
}
