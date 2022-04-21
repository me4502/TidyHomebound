package com.me4502.tidyhomebound.room;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.me4502.tidyhomebound.game.actor.chore.ImageChore;
import com.me4502.tidyhomebound.ui.GameUI;

public class RoomRenderer {

    private final RoomLayout roomLayout;
    private final GameUI ui;

    // Rotational matrices for the isometric effect.
    private final Matrix4 floorMatrix = new Matrix4();
    private final Matrix4 rightWallMatrix = new Matrix4();
    private final Matrix4 leftWallMatrix = new Matrix4();

    private final SpriteBatch roomBatch;

    private ImageChore.State lastFloorState = ImageChore.State.GOOD;

    public RoomRenderer(AssetManager assetManager, GameUI ui) {
        floorMatrix.setToRotation(new Vector3(1, 0, 0), 90);
        rightWallMatrix.setToRotation(new Vector3(1, 0, 0), 180);
        leftWallMatrix.setToRotation(new Vector3(1, 0, 0), -180).rotate(new Vector3(0, 1, 0), -90);
        // The above matrices render a room with a 23.3 degree floor angle

        roomBatch = new SpriteBatch();

        this.roomLayout = new RoomLayout(assetManager, 6, 6);
        this.ui = ui;
    }

    public void render(Camera camera) {
        if (!roomLayout.isSetup()) {
            roomLayout.setupRoom();
        }
        if (lastFloorState != ui.getGameState().getFloorState()) {
            roomLayout.updateFloor(ui.getGameState());
            lastFloorState = ui.getGameState().getFloorState();
        }

        roomBatch.setProjectionMatrix(camera.combined);

        roomBatch.setTransformMatrix(floorMatrix);
        roomBatch.begin();
        for (var x = 0; x < roomLayout.getWidth(); x++) {
            for (var y = 0; y < roomLayout.getHeight(); y++) {
                var floorSprite = roomLayout.getFloor()[x][y];
                floorSprite.draw(roomBatch);
            }
        }
        roomBatch.end();

        roomBatch.setTransformMatrix(leftWallMatrix);
        roomBatch.begin();
        for (var x = 0; x < roomLayout.getLeftWall().length; x++) {
            roomLayout.getLeftWall()[x].draw(roomBatch);
        }
        roomBatch.end();

        roomBatch.setTransformMatrix(rightWallMatrix);
        roomBatch.begin();
        for (var x = 0; x < roomLayout.getRightWall().length; x++) {
            roomLayout.getRightWall()[x].draw(roomBatch);
        }
        roomBatch.end();
    }

    public void dispose() {
        roomBatch.dispose();
    }
}
