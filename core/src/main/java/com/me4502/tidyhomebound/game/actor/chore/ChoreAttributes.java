package com.me4502.tidyhomebound.game.actor.chore;

public class ChoreAttributes {

    // Controls how slowly spoons do this chore
    private final double difficulty;

    // Controls how quickly the chore degrades
    private final double frequency;

    // Controls how badly this chore impacts coping
    private final double importance;

    // Controls how much completion of this chore improves self care score
    private final double selfCare;

    public ChoreAttributes(double difficulty, double frequency, double importance, double selfCare) {
        this.difficulty = difficulty;
        this.frequency = frequency;
        this.importance = importance;
        this.selfCare = selfCare;
    }

    public double getDifficulty() {
        return this.difficulty;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public double getImportance() {
        return this.importance;
    }

    public double getSelfCare() {
        return selfCare;
    }
}
