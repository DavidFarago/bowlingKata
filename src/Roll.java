import java.util.Objects;

/**
 * Represents an immutable bowling ball roll with 0 to 10 pins bowled down.
 */
public final class Roll {
    private int pins;

    public Roll(int pinsFallen) {
        if (pinsFallen < 0 || pinsFallen > 10) {
            throw new IllegalArgumentException("A roll must bowl down 0 to 10 pins");
        }
        pins = pinsFallen;
    }

    public int getPins() {
        return pins;
    }

    @Override
    public String toString() {
        if (getPins() != 10) {
            return Integer.toString(pins);
        } else {
            return "X";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roll roll = (Roll) o;
        return getPins() == roll.getPins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPins());
    }
}
