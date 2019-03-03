/**
 * Bowling kata with shippable quality:
 * * outer quality: correct, robust, usable
 * * inner quality: maintainable, extensible, readable (especially bowling "business rules").
 */
public final class Game {

    public void roll(int pins) {

    }

    public void rolls(int ... pinsArray) {
        for (int pins : pinsArray) {
            roll(pins);
        }
    }

    public int score(){
        return 0;
    }
}
