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
            frames[currentFrame].roll(new Roll(pins));
            if (frames[currentFrame].isFull()) {
                ++currentFrame;
            }
        } else {
            bonusRoll(pins);
        }
    }

    private void bonusRoll(int pins) {
        if (frames[9].isStrike()) {
            if (frames[9].optionalFirstBonus == null) {
                frames[9].optionalFirstBonus = new Roll(pins);
            } else if (frames[9].optionalSecondBonus == null) {
                frames[9].optionalSecondBonus = new Roll(pins);
            } else {
                throw new IllegalStateException("Too many rolls, game already over");
            }
        } else if (frames[9].isSpare()) {
            if (frames[9].optionalFirstBonus == null) {
                frames[9].optionalFirstBonus = new Roll(pins);
            } else {
                throw new IllegalStateException("Too many rolls, game already over");
            }
        } else {
            throw new IllegalStateException("Too many rolls, game already over");
        }
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

    public int score(){
        return Arrays.stream(frames).mapToInt(Frame::getPins).sum();
    }
}
