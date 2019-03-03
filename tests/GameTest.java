import org.junit.jupiter.api.*;

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

        @Test
        @DisplayName("Then too many rolls should throw")
        void tooMany1PinShouldThrow() {
            int[] all10Frames = new int[20];
            Arrays.fill(all10Frames, 1);

            sut.rolls(all10Frames);
            Throwable exception = Assertions.assertThrows(IllegalStateException.class,
                    () -> sut.roll(1));
            Assertions.assertEquals("Too many rolls, game already over", exception.getMessage());
        }
    }

}