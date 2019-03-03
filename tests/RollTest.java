import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Given a bowling roll")
class RollTest {

    @Test
    @DisplayName("Then bowling down 0 to 10 pins should be printed in the right format")
    void then0to10PinsShouldBeBowledDown() {
        Roll zero = new Roll(0);
        assertThat(zero.toString(), is("0"));

        Roll nine = new Roll(9);
        assertThat(nine.toString(), is("9"));

        Roll ten = new Roll(10);
        assertThat(ten.toString(), is("X"));
    }
}