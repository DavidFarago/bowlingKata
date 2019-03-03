/**
 * Represents a bowling frame with 1 to 2 rolls and 0 to 2 bonuses.
 */
public final class Frame {
    Roll firstRoll;
    Roll optionalSecondRoll;
    Roll optionalFirstBonus;
    Roll optionalSecondBonus;

    public void roll(Roll roll) {
        if (firstRoll == null) {
            firstRoll = roll;
        } else {
            if (isStrike()) {
                throw new IllegalStateException("No second roll in a frame after strike");
            }
            if (isFull()) {
                throw new IllegalStateException("No third roll in a frame");
            }
            optionalSecondRoll = roll;
        }
    }

    public boolean isStrike() {
        return (firstRoll != null) && (firstRoll.getPins() == 10);
    }

    public boolean isSpare() {
        return (!isStrike() && isFull() && getPins() == 10);
    }

    public boolean isFull() {
        return (isStrike() || optionalSecondRoll != null);
    }

    public int getPins() {
        return (firstRoll == null ? 0 : firstRoll.getPins())
                + (optionalSecondRoll == null ? 0 : optionalSecondRoll.getPins());
    }
}
