import java.util.Arrays;

/**
 * Bowling kata with shippable quality:
 * * outer quality: correct, robust, usable
 * * inner quality: maintainable, extensible, readable (especially bowling "business rules").
 */
public final class Game {

    private Roll[] rolls;
    private int currentRoll;

    public Game() {
        rolls = new Roll[20];
        currentRoll = 0;
    }

    public void roll(int pins) {
        if (currentRoll >=20) {
            throw new IllegalStateException("Too many rolls, game already over");
        }
        rolls[currentRoll++] = new Roll(pins);
    }

    public void rolls(int ... pinsArray) {
        if (currentRoll + pinsArray.length > 20) {
            throw new IllegalStateException("Too many rolls, game already over");
        }
        for (int pins : pinsArray) {
            roll(pins);
        }
    }

    public int score(){
        return Arrays.stream(rolls).mapToInt(Roll::getPins).sum();
    }
}
