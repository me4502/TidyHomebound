package com.me4502.tidyhomebound.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.me4502.tidyhomebound.Assets;
import com.me4502.tidyhomebound.TidyHomebound;
import com.me4502.tidyhomebound.game.actor.Spoon;
import com.me4502.tidyhomebound.game.actor.chore.Bed;
import com.me4502.tidyhomebound.game.actor.chore.Fridge;
import com.me4502.tidyhomebound.game.actor.chore.ImageChore;
import com.me4502.tidyhomebound.game.actor.chore.ImageChoreDragTarget;
import com.me4502.tidyhomebound.game.actor.chore.Oven;
import com.me4502.tidyhomebound.game.actor.chore.PlantPot;
import com.me4502.tidyhomebound.game.actor.chore.Sink;
import com.me4502.tidyhomebound.ui.GameOverScreen;
import com.me4502.tidyhomebound.ui.GameUI;
import com.me4502.tidyhomebound.ui.dialog.EndOfDayDialog;
import com.me4502.tidyhomebound.util.DebugDragAndDropSource;

import java.util.ArrayList;
import java.util.List;

import static com.me4502.tidyhomebound.TidyHomebound.DEBUG_MODE;

public class GameState {

    private final static double TWILIGHT_BRIGHTNESS = 0.5;

    private final static double SELF_CARE_LOW = 0.5;
    private final static double SELF_CARE_CRITICAL = 0.2;
    private final static double SELF_CARE_AMBIENT_LOSS = 0.005;

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
    private double timeOfDay = 0;

    // Immediate state logic
    private int borrowedSpoons = 0;
    private double handling = 1;
    private double selfCare = 1;

    private final List<Spoon> spoonActors = new ArrayList<>();
    private final List<DragAndDrop.Source> dragAndDropSources = new ArrayList<>();
    private final List<ImageChore> furnitureItems = new ArrayList<>();

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

        createFurniture(new PlantPot(assetManager, this, new Vector2(60, 100)));
        createFurniture(new Bed(assetManager, this, new Vector2(267, 224.5f)));
        createFurniture(new Sink(assetManager, this, new Vector2(128, 184.5f)));
        createFurniture(new Oven(assetManager, this, new Vector2(68, 160f)));
        createFurniture(new Fridge(assetManager, this, new Vector2(5, 132.5f)));

        startNewDay();
    }

    private void createFurniture(ImageChore chore) {
        stage.addActor(chore);
        dragAndDrop.addTarget(new ImageChoreDragTarget(chore));
        furnitureItems.add(chore);

        if (DEBUG_MODE) {
            dragAndDrop.addSource(new DebugDragAndDropSource(chore));
        }
    }

    public void startNewDay() {
        day ++;
        timeOfDay = 0;

        // Spoon handler
        int spoonModifier = borrowedSpoons; // If spoons are borrowed, they lower the multiplier.
        borrowedSpoons = 0; // Reset borrowed spoons

        if (this.selfCare < SELF_CARE_LOW) {
            spoonModifier += this.selfCare < SELF_CARE_CRITICAL ? 2 : 1;
        }

        int spoons = baseSpoons - spoonModifier; // Set spoons to base spoons minus the modifier

        // Reset actors
        spoonActors.forEach(spoon -> spoon.addAction(Actions.removeActor()));
        spoonActors.clear();
        dragAndDropSources.forEach(dragAndDrop::removeSource);
        dragAndDropSources.clear();

        // Add new actors
        int leftBound = (baseSpoons + baseEmergencySpoons) * (32 + 2);
        for (int i = 0; i < spoons + baseEmergencySpoons; i++) {
            Spoon spoon = new Spoon(assetManager, this, new Vector2(TidyHomebound.GAME_WIDTH - 16 - (leftBound - (32 + 2) * i), TidyHomebound.GAME_HEIGHT - 137), i >= spoons);
            stage.addActor(spoon);
            spoonActors.add(spoon);
            var dragAndDropSource = new Spoon.SpoonDragSource(spoon);
            dragAndDrop.addSource(dragAndDropSource);
            dragAndDropSources.add(dragAndDropSource);
        }

        ui.setDialog(null);
    }

    public boolean isAlive() {
        return handling >= 0;
    }

    public void update(float delta) {
        timeOfDay += delta;
        if (timeOfDay >= dayLength - 1) {
            this.ui.setDialog(new EndOfDayDialog(this.ui, this));
            return;
        }
        if (handling <= 0) {
            this.homebound.setScreen(new GameOverScreen(this.homebound, this.assetManager, this));
            return;
        }

        modifySelfCare(-SELF_CARE_AMBIENT_LOSS * delta, null);

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

    public void modifyHandling(double modifier, Vector2 position) {
        if (modifier < 0 && selfCare < SELF_CARE_CRITICAL) {
            // Double negative coping score if selfCare is in struggle territory
            modifier *= 2;
        }

        this.handling += modifier;
        this.handling = Math.max(0.0, Math.min(1.0, this.handling));

        if (position != null) {
            // Show a popup thing
        }
    }

    /**
     * Gets the current self-care level.
     *
     * @return self-care level
     */
    public double getSelfCare() {
        return selfCare;
    }

    public void modifySelfCare(double modifier, Vector2 position) {
        this.selfCare += modifier;
        this.selfCare = Math.max(0.0, Math.min(1.0, this.selfCare));

        if (position != null) {
            // Show a popup thing
        }
    }

    public void logSpoonBorrowed() {
        borrowedSpoons ++;
    }
}
