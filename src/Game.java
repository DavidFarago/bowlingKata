/**
 * Bowling kata with shippable quality:
 * * outer quality: correct, robust, usable
 * * inner quality: maintainable, extensible, readable (especially bowling "business rules").
 */
public final class Game {

    private int score;

    public Game() {
        score = 0;
    }

    public void roll(int pins) {
        Roll roll = new Roll(pins);
        score +=roll.getPins();
    }

    public void rolls(int ... pinsArray) {
        for (int pins : pinsArray) {
            roll(pins);
        }
    }

    public int score(){
        return score;
    }
}
