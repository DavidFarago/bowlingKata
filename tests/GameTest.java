import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Given a bowling game")
class GameTest {

    private Game sut;

    @BeforeEach
    void setUp() {
        sut = new Game();
    }

    @Nested
    @DisplayName("When a simple game is played")
    class WhenSimpleGame {

        @Test
        @DisplayName("Then all rolls 0 pins should yield 0 score")
        void all0Pins() {
            int[] all10Frames = new int[20];
            Arrays.fill(all10Frames, 0);

            sut.rolls(all10Frames);
            assertThat(sut.score(), is(0));
        }

        @Test
        @DisplayName("Then all rolls 1 pin should yield 20 score")
        void all1Pin() {
            int[] all10Frames = new int[20];
            Arrays.fill(all10Frames, 1);

            sut.rolls(all10Frames);
            assertThat(sut.score(), is(20));
        }
    }

}