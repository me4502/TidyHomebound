package com.me4502.tidyhomebound.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.actor.Spoon;
import com.me4502.tidyhomebound.game.actor.chore.ImageChoreDragTarget;
import com.me4502.tidyhomebound.game.actor.chore.PlantPot;
import com.me4502.tidyhomebound.ui.GameOverScreen;
import com.me4502.tidyhomebound.ui.GameUI;
import com.me4502.tidyhomebound.ui.dialog.EndOfDayDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {

    private final static Random RANDOM = new Random();
    private final static double TWILIGHT_BRIGHTNESS = 0.5;

    // Everything that is set for the whole game
    private final GameUI ui;
    private final Stage stage;
    private final AssetManager assetManager;
    private final TidyHomebound homebound;
    private final DragAndDrop dragAndDrop;
    private final int baseSpoons;
    private final int baseEmergencySpoons;
    private final double dayLength;

    // High-level state logic (eg, which day it is)
    private int day = 0;
    private int spoonModifier = 0;
    private double timeOfDay = 0;
    private int spoons = 0;

    // Immediate state logic (eg, how many spoons are left)
    private int borrowedSpoons = 0;
    private double handling = 1;
    private double selfCare = 1;

    private final List<Spoon> spoonActors = new ArrayList<>();

    public GameState(TidyHomebound homebound, GameUI ui, Stage stage, AssetManager assetManager) {
        this.homebound = homebound;
        this.ui = ui;
        this.stage = stage;
        this.assetManager = assetManager;
        this.dragAndDrop = new DragAndDrop();
        // Offset drags to correctly be under the mouse cursor
        this.dragAndDrop.setDragActorPosition(16, -16);

        baseSpoons = 5;
        baseEmergencySpoons = 2;
        dayLength = 60 * 2; // 2 minutes per day.

        startNewDay();

        PlantPot pot = new PlantPot(assetManager, this, new Vector2(60, 100));
        stage.addActor(pot);
        dragAndDrop.addTarget(new ImageChoreDragTarget(pot));
    }

    public void startNewDay() {
        day ++;
        timeOfDay = 0;

        // Spoon handler
        spoonModifier = -borrowedSpoons; // If spoons are borrowed, they lower the multiplier.
        borrowedSpoons = 0; // Reset borrowed spoons
        spoons = baseSpoons - spoonModifier; // Set spoons to base spoons minus the modifier

        // Reset actors
        spoonActors.forEach(spoon -> spoon.addAction(Actions.removeActor()));
        spoonActors.clear();
        this.dragAndDrop.clear();

        // Add new actors
        int leftBound = (baseSpoons + baseEmergencySpoons) * (32 + 2);
        for (int i = 0; i < spoons; i++) {
            Spoon spoon = new Spoon(assetManager, new Vector2(TidyHomebound.GAME_WIDTH - 16 - (leftBound - (32 + 2) * i), TidyHomebound.GAME_HEIGHT - 137), false);
            stage.addActor(spoon);
            spoonActors.add(spoon);
            dragAndDrop.addSource(new Spoon.SpoonDragSource(spoon));
        }
        if (spoonModifier == 0) {
            // Show borrowed spoons
            for (int i = 0; i < baseEmergencySpoons; i++) {
                Spoon spoon = new Spoon(assetManager, new Vector2(TidyHomebound.GAME_WIDTH - 16 - (leftBound - (32 + 2) * (i + spoons)), TidyHomebound.GAME_HEIGHT - 137), true);
                stage.addActor(spoon);
                spoonActors.add(spoon);
                dragAndDrop.addSource(new Spoon.SpoonDragSource(spoon));
            }
        }

        ui.setDialog(null);
    }

    public boolean isAlive() {
        return true;
    }

    /**
     * Triggers an adverse event, something needs attention.
     */
    private void triggerEvent() {

    }

    public void update(float delta) {
        timeOfDay += delta;
        if (timeOfDay >= dayLength) {
            this.ui.setDialog(new EndOfDayDialog(this.ui, this));
            return;
        }
        if (handling <= 0) {
            this.homebound.setScreen(new GameOverScreen(this.homebound, this.assetManager, this));
            return;
        }

        stage.act(delta);
    }

    /**
     * What percentage night time it is.
     *
     * @return 0 - 1 on how dark it is
     */
    public double getNightPercentage() {
        double dayStart = this.dayLength * 0.05; // 5% of the day
        double twilightStart = this.dayLength - this.dayLength * 0.2; // Final 20% of the day.
        double dayEnd = this.dayLength - this.dayLength * 0.05; // Final 5% of the day.

        if (this.timeOfDay <= dayStart) {
            return Math.max(Math.min(1.0 - timeOfDay / dayStart, 1.0), 0.0);
        }
        if (this.timeOfDay >= twilightStart && this.timeOfDay < dayEnd) {
            return Math.min((timeOfDay - twilightStart) / (dayLength * 0.05), TWILIGHT_BRIGHTNESS);
        }
        if (this.timeOfDay >= dayEnd) {
            return Math.min(TWILIGHT_BRIGHTNESS + (timeOfDay - dayEnd) / (dayLength * 0.05), 1.0);
        }

        return 0;
    }

    public double getNormalisedTimeOfDay() {
        return timeOfDay / dayLength;
    }

    /**
     * Gets the current day number.
     *
     * @return The day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Gets the current handling/coping level.
     *
     * @return coping level
     */
    public double getHandling() {
        return handling;
    }

    /**
     * Gets the current self-care level.
     *
     * @return self-care level
     */
    public double getSelfCare() {
        return selfCare;
    }
}
