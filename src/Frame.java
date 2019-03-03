/**
 * Represents a bowling frame with 1 to 2 rolls and 0 to 2 bonuses.
 */
public final class Frame {
    private Roll firstRoll;
    private Roll optionalSecondRoll;
    private Roll optionalFirstBonus;
    private Roll optionalSecondBonus;
    private int currentScore = 0;

    public void roll(int pins) {
        if (firstRoll == null) {
            firstRoll = new Roll(pins);
        } else {
            if (isStrike()) {
                throw new IllegalStateException("No second roll in a frame after strike");
            }
            if (isFull()) {
                throw new IllegalStateException("No third roll in a frame");
            }
            if (firstRoll.getPins() + pins > 10) {
                throw new IllegalArgumentException("No more pins than 10 in a frame");
            }
            optionalSecondRoll = new Roll(pins);
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

    public int getBonuses() {
        return (optionalFirstBonus == null ? 0 : optionalFirstBonus.getPins())
                + (optionalSecondBonus == null ? 0 : optionalSecondBonus.getPins());
    }

    public int getScore() {
        return currentScore;
    }

    public void setScoreBasedOnPredecessorScore(int predecessorScore) {
        this.currentScore = predecessorScore + getPins() + getBonuses();
    }

    Roll getFirstRoll() {
        return firstRoll;
    }

    Roll getSecondRoll() {
        return optionalSecondRoll;
    }

    Roll getFirstBonus() {
        return optionalFirstBonus;
    }

    void setFirstBonus(Roll optionalFirstBonus) {
        this.optionalFirstBonus = optionalFirstBonus;
    }

    Roll getSecondBonus() {
        return optionalSecondBonus;
    }

    void setSecondBonus(Roll optionalSecondBonus) {
        this.optionalSecondBonus = optionalSecondBonus;
    }

    @Override
    public String toString() {
        String firstString = " ";
        String secondString = " ";
        if (getFirstRoll() != null) {
            firstString = getFirstRoll().toString();
        }
        if (getSecondRoll() != null) {
            secondString = getSecondRoll().toString();
        }
        return "|" + firstString + " " + secondString + "|";
    }
}
