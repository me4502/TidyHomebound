package com.me4502.tidyhomebound.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me4502.tidyhomebound.ui.GameUI;
import com.me4502.tidyhomebound.ui.dialog.EndOfDayDialog;

import java.util.Random;

public class GameState {

    private final static Random RANDOM = new Random();
    private final static double TWILIGHT_BRIGHTNESS = 0.5;

    // Everything that is set for the whole game
    private final GameUI ui;
    private final Stage stage;
    private final int baseSpoons;
    private final double dayLength;

    // High-level state logic (eg, which day it is)
    private int day = 0;
    private int spoonModifier = 0;
    private double timeOfDay = 0;

    // Immediate state logic (eg, how many spoons are left)
    private int spoons = 0;
    private int borrowedSpoons = 0;
    private double handling = 1;
    private double selfCare = 1;

    public GameState(GameUI ui, Stage stage) {
        this.ui = ui;
        this.stage = stage;

        baseSpoons = 5;
        dayLength = 60 * 2; // 2 minutes per day.

        startNewDay();
    }

    public void startNewDay() {
        day ++;
        timeOfDay = 0;

        // Spoon handler
        spoonModifier = -borrowedSpoons; // If spoons are borrowed, they lower the multiplier.
        borrowedSpoons = 0; // Reset borrowed spoons
        spoons = baseSpoons - spoonModifier; // Set spoons to base spoons minus the modifier
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

        stage.act(delta);
    }

    /**
     * What percentage night time it is.
     *
     * @return 0 - 1 on how dark it is
     */
    public double getNightPercentage() {
        double dayStart = this.dayLength * 0.05; // 5% of the day
        double twilightStart = this.dayLength - (this.dayLength * 0.2); // Final 20% of the day.
        double dayEnd = this.dayLength - (this.dayLength * 0.05); // Final 5% of the day.

        if (this.timeOfDay <= dayStart) {
            return 1.0 - (timeOfDay / dayStart);
        }
        if (this.timeOfDay >= twilightStart && this.timeOfDay < dayEnd) {
            return Math.min((timeOfDay - twilightStart) / (dayLength * 0.05), TWILIGHT_BRIGHTNESS);
        }
        if (this.timeOfDay >= dayEnd) {
            return Math.min(TWILIGHT_BRIGHTNESS + (timeOfDay - dayEnd) / (dayLength * 0.05), 1.0);
        }

        return 0;
    }
}
