package com.me4502.tidyhomebound;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.me4502.tidyhomebound.ui.GameUI;
import com.me4502.tidyhomebound.ui.LoadingUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class TidyHomebound extends Game {

	private GameUI ui;
	private AssetManager assetManager;

	public static float GLOBAL_SCALE = 1.0f;
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 640;

	public static boolean DEBUG_MODE = true;

	@Override
	public void create() {
		this.assetManager = new AssetManager();
		Assets.loadImmediateAssets(this.assetManager);

		GLOBAL_SCALE = Gdx.graphics.getWidth() / (float) GAME_WIDTH;

		this.ui = new GameUI(this, this.assetManager);

		setScreen(new LoadingUI(this, this.assetManager, this.ui));
	}

	@Override
	public void render() {
		super.render();
	}

	public GameUI getUi() {
		return ui;
	}

	@Override
	public void dispose() {
		ui.dispose();
		assetManager.dispose();
	}
}
