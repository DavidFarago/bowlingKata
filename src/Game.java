import java.util.Arrays;

/**
 * Bowling kata with shippable quality:
 * * outer quality: correct, robust, usable
 * * inner quality: maintainable, extensible, readable (especially bowling "business rules").
 */
public final class Game {

    private Roll[] rolls;
    private int currentRoll;
    private boolean isFinished;
    private int currentEndScore;
    private String currentScoreBoard;

    public Game() {
        rolls = new Roll[22];
        currentRoll = 0;
        isFinished = false;
        currentEndScore = 0;
        currentScoreBoard = "";
    }

    public void roll(int pins) {
        if (isFinished || currentRoll >=22) {
            throw new IllegalStateException("Too many rolls, game already over (after " + currentRoll + " rolls)");
        }
        rolls[currentRoll++] = new Roll(pins);
        updateScoreBoardAndFinished();
    }

    public void rolls(int ... pinsArray) {
        for (int pins : pinsArray) {
            roll(pins);
        }
    }

    private void updateScoreBoardAndFinished() {
        StringBuilder firstLine = new StringBuilder();
        StringBuilder secondLine = new StringBuilder();
        int currentScore = 0;
        int rollIndex = 0;
        int currentFrame = 0;
        while (!isFinished && currentFrame < 10 && rollIndex < currentRoll) {
            if (isStrike(rollIndex)) {
                currentScore += 10 + getPins(rollIndex + 1) + getPins(rollIndex + 2);
                firstLine.append(makeFrameString(rolls[rollIndex], null));
                isFinished = endSituation(currentFrame, rollIndex + 2);
                rollIndex += 1;
            } else if (isSpare(rollIndex)) {
                currentScore += 10 + getPins(rollIndex + 2);
                firstLine.append(makeFrameString(rolls[rollIndex], rolls[rollIndex + 1]));
                isFinished = endSituation(currentFrame, rollIndex + 1);
                rollIndex += 2;
            } else {
                currentScore += getPins(rollIndex) + getPins(rollIndex + 1);
                firstLine.append(makeFrameString(rolls[rollIndex], rolls[rollIndex + 1]));
                isFinished = endSituation(currentFrame, rollIndex + 1);
                rollIndex += 2;
            }
            secondLine.append("|").append(String.format("%3s", currentScore)).append("|");
            currentFrame += 1;
            System.out.println("Is finished: " + isFinished);
        }
        currentScoreBoard = firstLine.toString() + "\n" + secondLine.toString() + "\n";
        currentEndScore = currentScore;
    }

    private boolean endSituation(int currentFrame, int lastRollIndex) {
        return (currentFrame == 9 && rolls[lastRollIndex] != null);
    }

    private int getPins(int rollIndex) {
        if (rolls[rollIndex] == null) {
            return 0;
        }
        return rolls[rollIndex].getPins();
    }

    private boolean isStrike(int rollIndex) {
        return getPins(rollIndex) == 10;
    }

    private boolean isSpare(int rollIndex) {
        return !isStrike(rollIndex) && (getPins(rollIndex) + getPins(rollIndex + 1)) == 10;
    }

    private String makeFrameString(Roll firstRoll, Roll secondRoll) {
        String firstString = " ";
        String secondString = " ";
        if (firstRoll != null) {
            firstString = firstRoll.toString();
        }
        if (secondRoll != null) {
            secondString = secondRoll.toString();
        }
        return "|" + firstString + " " + secondString + "|";
    }

    public int score(){
        return currentEndScore;
    }
}
