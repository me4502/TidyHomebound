package com.me4502.tidyhomebound.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.GameInputProcessor;
import com.me4502.tidyhomebound.game.GameState;
import com.me4502.tidyhomebound.game.actor.Clock;
import com.me4502.tidyhomebound.game.actor.CopingBar;
import com.me4502.tidyhomebound.game.actor.SelfCareBar;
import com.me4502.tidyhomebound.room.RoomRenderer;
import com.me4502.tidyhomebound.ui.dialog.*;

public class GameUI extends ScreenAdapter implements DialogHolder {

    private static final float FAKE_ASPECT_RATIO = 1.33f;

    private final OrthographicCamera roomCamera;
    private final OrthographicCamera stageCamera;
    private final SpriteBatch batch;
    private final RoomRenderer roomRenderer;
    private final AssetManager assetManager;
    private final ShapeRenderer shapeRenderer;
    private final TidyHomebound homebound;

    private GameState gameState;
    private Dialog dialog;
    private InputMultiplexer inputProcessor;
    private Stage backgroundStage;
    private Stage stage;

    private Sprite backgroundSprite;
    private Sound backgroundMusic;

    public GameUI(TidyHomebound homebound, AssetManager assetManager) {
        this.homebound = homebound;
        this.assetManager = assetManager;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.roomRenderer = new RoomRenderer(assetManager, this);

        this.batch.getProjectionMatrix().setToOrtho2D(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);
        this.shapeRenderer.getProjectionMatrix().setToOrtho2D(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);

        this.roomCamera = new OrthographicCamera(5.4f, 5.4f * ((TidyHomebound.GAME_HEIGHT * FAKE_ASPECT_RATIO) / (float)TidyHomebound.GAME_WIDTH));
        roomCamera.position.set(9.5f, 10, 9.5f);
        roomCamera.direction.set(-1, -1, -1);
        roomCamera.near = 0.1f;
        roomCamera.far = 100;
        roomCamera.zoom = 1.6f;

        this.stageCamera = new OrthographicCamera();

        Assets.loadGameAssets(this.assetManager);
    }

    @Override
    public void show() {
        this.backgroundSprite = new Sprite(assetManager.get(Assets.BACKGROUND_BASE));
        this.backgroundStage = new Stage(new ScalingViewport(Scaling.fill, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT), this.batch);

        this.stage = new Stage(new ScalingViewport(Scaling.fit, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT, this.stageCamera), this.batch);
        this.gameState = new GameState(homebound, this, stage, assetManager);
        this.inputProcessor = new InputMultiplexer(new GameInputProcessor(this, this.gameState), stage);

        stage.addActor(new Clock(assetManager, gameState, new Vector2(TidyHomebound.GAME_WIDTH - 57, TidyHomebound.GAME_HEIGHT - 57)));
        stage.addActor(new CopingBar(assetManager, gameState, new Vector2(18, TidyHomebound.GAME_HEIGHT - 50), TidyHomebound.GAME_WIDTH - 205, 32));
        stage.addActor(new SelfCareBar(assetManager, gameState, new Vector2(18, TidyHomebound.GAME_HEIGHT - 125), 250, 32));

        setDialog(new StartDialog(this, startDialog -> {
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
            backgroundMusic = assetManager.get(Assets.getBackgroundMusic());
            backgroundMusic.stop();
            backgroundMusic.loop(0.6f);
        }));
    }

    @Override
    public void hide() {
        this.backgroundMusic.stop();
    }

    private void renderBackground() {
        batch.begin();

        // 10 x 10 bricks
        float widthScaleFactor = TidyHomebound.GAME_WIDTH / 320f;
        float heightScaleFactor = TidyHomebound.GAME_HEIGHT / 320f;

        for (var x = 0; x < TidyHomebound.GAME_WIDTH; x += 32) {
            for (var y = 0; y < TidyHomebound.GAME_HEIGHT; y += 32) {
                batch.draw(backgroundSprite, x, y, 32 * widthScaleFactor, 32 * heightScaleFactor);
            }
        }
        batch.end();
    }

    private void renderNightTime(float nightPercentage) {
        // Handle night cycle
        if (nightPercentage > 0f) {
            backgroundStage.getViewport().apply();

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            var color = Color.BLACK.cpy();
            color.a = nightPercentage;

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(0, 0, TidyHomebound.GAME_WIDTH, TidyHomebound.GAME_HEIGHT);
            shapeRenderer.end();
            shapeRenderer.setColor(Color.WHITE);

            Gdx.gl.glDisable(GL20.GL_BLEND);

            stage.getViewport().apply();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (dialog == null) {
            // Do not update while a dialog is up.
            gameState.update(delta);
            if (!gameState.isAlive()) {
                backgroundMusic.stop();
                homebound.setScreen(new GameOverScreen(homebound, assetManager, gameState));
                return;
            }
        }

        backgroundStage.getViewport().apply();
        renderBackground();

        stage.getViewport().apply();
        roomCamera.update();

        // Render the room first
        roomRenderer.render(roomCamera);

        batch.begin();
        DialogRenderer.drawDialog(
            assetManager,
            batch,
            5,
            TidyHomebound.GAME_HEIGHT - (TidyHomebound.GAME_HEIGHT / 3) + 5,
            TidyHomebound.GAME_WIDTH - 10,
            (TidyHomebound.GAME_HEIGHT / 3) - 10
        );
        batch.end();

        // Draw the stage
        stage.draw();

        float nightPercentage = (float) gameState.getNightPercentage();
        if (dialog != null) {
            renderNightTime(Math.max(0.8f, nightPercentage));
            dialog.render(batch, assetManager);
        } else {
            renderNightTime(nightPercentage);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        if (this.stage != null) {
            this.stage.getViewport().update(width, height, true);
        }
        if (this.backgroundStage != null) {
            this.backgroundStage.getViewport().update(width, height, true);
        }
    }

    @Override
    public void resume() {
        super.resume();

        if (getDialog() == null) {
            setDialog(new PauseDialog(this));
        }
    }

    public void dispose() {
        batch.dispose();
        roomRenderer.dispose();
        if (stage != null) {
            stage.dispose();
        }
        if (backgroundStage != null) {
            backgroundStage.dispose();
        }
        shapeRenderer.dispose();
    }

    @Override
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
        if (this.dialog == null) {
            Gdx.input.setInputProcessor(inputProcessor);
        } else {
            Gdx.input.setInputProcessor(dialog.getInputProcessor());
        }
    }

    @Override
    public Dialog getDialog() {
        return this.dialog;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Stage getStage() {
        return stage;
    }

    public boolean useLargeTouchTargets() {
        return Gdx.app.getType() == Application.ApplicationType.iOS;
    }
}
