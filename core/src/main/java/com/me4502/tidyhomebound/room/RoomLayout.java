package com.me4502.tidyhomebound.room;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.game.GameState;

public class RoomLayout {

    // Walls are on average 2.6 metres tall.
    public static final float WALL_HEIGHT = 2.6F;

    private final Sprite[][] floor;
    private final Sprite[] leftWall;
    private final Sprite[] rightWall;

    private final int width;
    private final int height;

    private final AssetManager assetManager;

    private boolean isSetup = false;

    public RoomLayout(AssetManager assetManager, int width, int height) {
        this.floor = new Sprite[width][height];
        this.leftWall = new Sprite[height];
        this.rightWall = new Sprite[width];

        this.width = width;
        this.height = height;

        this.assetManager = assetManager;
    }

    public void setupRoom() {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                var floorSprite = new Sprite(assetManager.get(Assets.FLOOR_BASE));
                floorSprite.setSize(1, 1);
                floorSprite.setPosition(x, y);
                this.floor[x][y] = floorSprite;
            }
        }

        for (var x = 0; x < width; x++) {
            var wallSprite = new Sprite(assetManager.get(x == 2 ? Assets.WALL_WINDOW : Assets.WALL_BASE));
            wallSprite.setSize(1, WALL_HEIGHT);
            wallSprite.setPosition(x, -WALL_HEIGHT);
            wallSprite.flip(false, true);
            this.rightWall[x] = wallSprite;
        }

        for (var x = 0; x < height; x++) {
            var wallSprite = new Sprite(assetManager.get(x == 4 ? Assets.DOOR_BASE : Assets.WALL_BASE));
            wallSprite.setSize(1, WALL_HEIGHT);
            wallSprite.setPosition(x - height, -WALL_HEIGHT);
            wallSprite.flip(false, true);
            this.leftWall[x] = wallSprite;
        }

        isSetup = true;
    }

    public void updateFloor(GameState gameState) {
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                var floorSprite = this.floor[x][y];
                switch (gameState.getFloorState()) {
                    case GOOD:
                        floorSprite.setTexture(assetManager.get(Assets.FLOOR_BASE));
                        break;
                    case BAD:
                        floorSprite.setTexture(assetManager.get(Assets.FLOOR_LIGHT_DIRT));
                        break;
                    case CRITICAL:
                        floorSprite.setTexture(assetManager.get(Assets.FLOOR_HEAVY_DIRT));
                        break;
                }
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isSetup() {
        return this.isSetup;
    }

    public Sprite[][] getFloor() {
        return this.floor;
    }

    public Sprite[] getLeftWall() {
        return this.leftWall;
    }

    public Sprite[] getRightWall() {
        return this.rightWall;
    }
}
