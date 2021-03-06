package com.me4502.tidyhomebound.game.actor.chore;

import com.me4502.tidyhomebound.game.actor.Spoon;

public interface Chore {

    ChoreAttributes getAttributes();

    boolean isComplete();

    boolean canPerform();

    void reward();

    void perform(Spoon spoon, float delta);

    void removeSpoon(Spoon spoon);

    void addSpoon(Spoon spoon);

    void resetSpoons();
}
