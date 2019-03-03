import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@DisplayName("Given a bowling frame")
class FrameTest {

    private Frame sut;

    @BeforeEach
    void setUp() {
        sut = new Frame();
    }

    @Nested
    @DisplayName("When a simple frame is filled")
    class WhenSimpleFrame {

        @Test
        @DisplayName("Then rolling 4 and 5 pins should yield 9 pins")
        void then45Yields9() {
            sut.roll(4);
            sut.roll(5);
            assertThat(sut.getFirstRoll().getPins(), is(4));
            assertThat(sut.getSecondRoll().getPins(), is(5));
            assertThat(sut.getPins(), is(9));
            assertThat(sut.isStrike(), is(false));
            assertThat(sut.isSpare(), is(false));
            assertThat(sut.isFull(), is(true));
            assertThat(sut.toString(), is("|4 5|"));
        }

        @Test
        @DisplayName("Then rolling 4 and 5 pins should yield 9 pins")
        void then90Yields9() {
            sut.roll(9);
            sut.roll(0);
            assertThat(sut.getFirstRoll().getPins(), is(9));
            assertThat(sut.getSecondRoll().getPins(), is(0));
            assertThat(sut.getPins(), is(9));
            assertThat(sut.isStrike(), is(false));
            assertThat(sut.isSpare(), is(false));
            assertThat(sut.isFull(), is(true));
            assertThat(sut.toString(), is("|9 0|"));
        }

        @Test
        @DisplayName("Then rolling 4 and 4 and 0 pins should throw")
        void then440Throws() {
            sut.roll(4);
            sut.roll(4);
            Throwable exception = Assertions.assertThrows(IllegalStateException.class,
                    () -> sut.roll(0));
            Assertions.assertEquals("No third roll in a frame", exception.getMessage());
        }

        @Test
        @DisplayName("Then rolling 9 and 2 pins should throw")
        void then92Throws() {
            sut.roll(9);
            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> sut.roll(2));
            Assertions.assertEquals("No more pins than 10 in a frame", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("When a frame with bonus is filled")
    class WhenFrameWithBonus {

        @Test
        @DisplayName("Then rolling 10 pins should yield a strike")
        void then10YieldsStrike() {
            sut.roll(10);
            assertThat(sut.getFirstRoll().getPins(), is(10));
            assertThat(sut.isStrike(), is(true));
            assertThat(sut.isSpare(), is(false));
            assertThat(sut.isFull(), is(true));
            assertThat(sut.getPins(), is(10));
            assertThat(sut.toString(), is("|X  |"));
        }

        @Test
        @DisplayName("Then rolling 1 and 9 pins should be a spare")
        void then19YieldsSpare() {
            sut.roll(1);
            sut.roll(9);
            assertThat(sut.getFirstRoll().getPins(), is(1));
            assertThat(sut.getSecondRoll().getPins(), is(9));
            assertThat(sut.isStrike(), is(false));
            assertThat(sut.isSpare(), is(true));
            assertThat(sut.isFull(), is(true));
            assertThat(sut.getPins(), is(10));
            assertThat(sut.toString(), is("|1 9|"));
        }

        @Test
        @DisplayName("Then rolling 0 and 10 pins should be a spare")
        void then010YieldsSpare() {
            sut.roll(0);
            sut.roll(10);
            assertThat(sut.getFirstRoll().getPins(), is(0));
            assertThat(sut.getSecondRoll().getPins(), is(10));
            assertThat(sut.isStrike(), is(false));
            assertThat(sut.isSpare(), is(true));
            assertThat(sut.isFull(), is(true));
            assertThat(sut.getPins(), is(10));
            assertThat(sut.toString(), is("|0 X|"));
        }

        @Test
        @DisplayName("Then rolling 10 and 0 pins should throw")
        void then100Throws() {
            sut.roll(10);
            Throwable exception = Assertions.assertThrows(IllegalStateException.class,
                    () -> sut.roll(0));
            Assertions.assertEquals("No second roll in a frame after strike", exception.getMessage());
        }

        @Test
        @DisplayName("Then rolling 9 and 1 and 0 pins should throw")
        void then910Throws() {
            sut.roll(9);
            sut.roll(1);
            Throwable exception = Assertions.assertThrows(IllegalStateException.class,
                    () -> sut.roll(0));
            Assertions.assertEquals("No third roll in a frame", exception.getMessage());
        }
    }
}