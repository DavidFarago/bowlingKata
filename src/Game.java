import java.util.Arrays;

/**
 * Bowling kata with shippable quality:
 * * outer quality: correct, robust, usable
 * * inner quality: maintainable, extensible, readable (especially bowling "business rules").
 */
public final class Game {

    private Frame[] frames;
    private int currentFrame;

    public Game() {
        frames = new Frame[10];
        for(int i = 0; i < 10; i++)
        {
            frames[i] = new Frame();
        }
        currentFrame = 0;
    }

    public void roll(int pins) {
        if (currentFrame < 10) {
            frames[currentFrame].roll(pins);
            if (frames[currentFrame].isFull()) {
                ++currentFrame;
            }
        } else {
            bonusRoll(pins);
        }
        updateBonusesAndScore();
    }

    // for full failure atomicity, make defensive copy
    public void rolls(int ... pinsArray) {
        if (currentFrame + (pinsArray.length / 2) > 10) {
            throw new IllegalStateException("Too many rolls, game already over");
        }
        for (int pins : pinsArray) {
            roll(pins);
        }
    }

    public int score() {
        return frames[Math.min(currentFrame, 9)].getScore();
    }

    private void bonusRoll(int pins) {
        if (frames[9].isStrike()) {
            if (frames[9].getFirstBonus() == null) {
                frames[9].setFirstBonus(new Roll(pins));
            } else if (frames[9].getSecondBonus() == null) {
                frames[9].setSecondBonus(new Roll(pins));
            } else {
                throw new IllegalStateException("Too many rolls, game already over");
            }
        } else if (frames[9].isSpare()) {
            if (frames[9].getFirstBonus() == null) {
                frames[9].setFirstBonus(new Roll(pins));
            } else {
                throw new IllegalStateException("Too many rolls, game already over");
            }
        } else {
            throw new IllegalStateException("Too many rolls, game already over");
        }
    }

    private void updateBonusesAndScore() {
        int startFrame = 0;
        int predecessorScore = 0;
        if (currentFrame > 3) {
            startFrame = currentFrame - 3;
            predecessorScore = frames[currentFrame - 4].getScore();
        }
        for (int index = startFrame; index <= Math.min(currentFrame, 9); index++) {
            updateBonuses(index);
            frames[index].setScoreBasedOnPredecessorScore(predecessorScore);
            predecessorScore = frames[index].getScore();
        }
    }

    private void updateBonuses(int index) {
        if (index < 9) {
            if (frames[index].isSpare()) {
                frames[index].setFirstBonus(frames[index + 1].getFirstRoll());
            } else if (frames[index].isStrike()) {
                frames[index].setFirstBonus(frames[index + 1].getFirstRoll());
                frames[index].setSecondBonus(getSecondBonusAfter(index));
            }
        }
    }

    private Roll getSecondBonusAfter(int index) {
        if (frames[index + 1].isStrike()) {
            if (index < 8) {
                return frames[index + 2].getFirstRoll();
            } else {
                return frames[index + 1].getFirstBonus();
            }
        } else {
            return frames[index + 1].getSecondRoll();
        }
    }
}
