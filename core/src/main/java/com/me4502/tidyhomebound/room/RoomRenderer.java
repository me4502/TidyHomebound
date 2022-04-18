package com.me4502.tidyhomebound.room;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class RoomRenderer {

    private final RoomLayout roomLayout;

    // Rotational matrices for the isometric effect.
    private final Matrix4 floorMatrix = new Matrix4();
    private final Matrix4 rightWallMatrix = new Matrix4();
    private final Matrix4 leftWallMatrix = new Matrix4();

    private final SpriteBatch floorBatch;
    private final SpriteBatch leftWallBatch;
    private final SpriteBatch rightWallBatch;

    public RoomRenderer(AssetManager assetManager) {
        floorMatrix.setToRotation(new Vector3(1, 0, 0), 90);
        rightWallMatrix.setToRotation(new Vector3(1, 0, 0), 180);
        leftWallMatrix.setToRotation(new Vector3(1, 0, 0), -180).rotate(new Vector3(0, 1, 0), -90);

        floorBatch = new SpriteBatch();
        leftWallBatch = new SpriteBatch();
        rightWallBatch = new SpriteBatch();

        this.roomLayout = new RoomLayout(assetManager, 8, 8);
    }

    public void render(Camera camera) {
        if (!roomLayout.isSetup()) {
            roomLayout.setupRoom();
        }

        floorBatch.setProjectionMatrix(camera.combined);
        floorBatch.setTransformMatrix(floorMatrix);

        leftWallBatch.setProjectionMatrix(camera.combined);
        leftWallBatch.setTransformMatrix(leftWallMatrix);

        rightWallBatch.setProjectionMatrix(camera.combined);
        rightWallBatch.setTransformMatrix(rightWallMatrix);

        floorBatch.begin();
        for (var x = 0; x < roomLayout.getWidth(); x++) {
            for (var y = 0; y < roomLayout.getHeight(); y++) {
                roomLayout.getFloor()[x][y].draw(floorBatch);
            }
        }
        floorBatch.end();

        leftWallBatch.begin();
        for (var x = 0; x < roomLayout.getLeftWall().length; x++) {
            roomLayout.getLeftWall()[x].draw(leftWallBatch);
        }
        leftWallBatch.end();

        rightWallBatch.begin();
        for (var x = 0; x < roomLayout.getRightWall().length; x++) {
            roomLayout.getRightWall()[x].draw(rightWallBatch);
        }
        rightWallBatch.end();
    }

    public void dispose() {
        floorBatch.dispose();
        leftWallBatch.dispose();
        rightWallBatch.dispose();
    }
}
