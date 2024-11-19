package com.me4502.tidyhomebound.ui.dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.CopingBar;
import com.me4502.tidyhomebound.game.actor.SelfCareBar;

public class EndOfDayDialog extends Dialog {

    private final GameState gameState;
    private final Stage stage;
    private final InputProcessor multiplexedInput;

    public EndOfDayDialog(AssetManager assetManager, DialogHolder parent, GameState gameState) {
        super(parent);
        this.gameState = gameState;
        this.stage = new Stage(new ScalingViewport(Scaling.fit, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT));
        stage.setDebugAll(TidyHomebound.DEBUG_MODE);
        this.multiplexedInput = new InputMultiplexer(stage, this);

        Table table = new Table();

        table.row();
        table.add(new Label("End of Day " + gameState.getDay(), new Label.LabelStyle(assetManager.get(Assets.DOGICA), Color.BLACK))).center().padBottom(10);

        table.row();
        table.add(new CopingBar(assetManager, gameState, new Vector2(45, TidyHomebound.GAME_HEIGHT - 100), TidyHomebound.GAME_WIDTH - 90, 32))
            .size(TidyHomebound.GAME_WIDTH - 90, 32 + 35).padBottom(10);

        table.row();
        table.add(new SelfCareBar(assetManager, gameState, null, new Vector2(45, TidyHomebound.GAME_HEIGHT - 175), TidyHomebound.GAME_WIDTH - 90, 32))
            .size(TidyHomebound.GAME_WIDTH - 90, 32 + 35).padBottom(10);

        table.row();
        table.add(new Label("Emergency Spoons Used: " + gameState.getBorrowedSpoons(), new Label.LabelStyle(assetManager.get(Assets.DOGICA), Color.BLACK))).left().padBottom(30);

        table.row();
        table.add(new Label("Self Care Spoon Penalty: " + gameState.getSelfCareSpoonPenalty(), new Label.LabelStyle(assetManager.get(Assets.DOGICA), Color.BLACK))).left().padBottom(30);

        table.row().growY();
        table.add(new Label("Spoons Available Tomorrow: " + gameState.getNextSpoons(), new Label.LabelStyle(assetManager.get(Assets.DOGICA), Color.BLACK))).left().growY().padBottom(200);

        table.row().bottom();
        var button = new TextButton("Continue", new TextButton.TextButtonStyle(new NinePatchDrawable(assetManager.get(Assets.BUTTON).createPatch("button")), null, null, assetManager.get(Assets.DOGICA)));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameState.startNewDay();
            }
        });
        table.add(button);

        Container<Table> tableContainer = new Container<>(table);
        tableContainer.padTop(35f);
        tableContainer.align(Align.top);
        tableContainer.setFillParent(true);
        stage.addActor(tableContainer);
    }

    @Override
    public void render(SpriteBatch batch, AssetManager assetManager) {
        this.stage.getViewport().apply();
        batch.begin();
        DialogRenderer.drawDialog(
            assetManager,
            batch,
            5,
            5,
            TidyHomebound.GAME_WIDTH - 10,
            TidyHomebound.GAME_HEIGHT - 10
        );
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
