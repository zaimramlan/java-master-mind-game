package Game;

/**
 * Created by Zaid on 09/05/2015.
 *
 * This class is responsible on guessing the code
 * provided by the codebreaker.
 */
public class Codebreaker extends Player {
    private int difficulty;             // The difficulty of the game
    private int[] currentGuess;         // current code guess

    /**
     * Resets the guesser information for a new game
     */
    @Override
    public void reset(){
        difficulty = 4;
        penalty = 0;
        currentGuess = new int[SIZE];
        currentPoint = new int[3];
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * The scoring is based on score - penalty.
     * For every missed guess, the guesser is penalized by 1.25 points
     * while every wrong guess costs 2.5 points.
     */
    private void calculatePoint(){
        penalty += currentPoint[MISSED] * 1.25;
        penalty += currentPoint[WRONG] * 2.5;
    }

    public int[] getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(int[] currentGuess) {
        this.currentGuess = currentGuess.clone();
    }

    /**
     * Updates the score every time the player moves.
     */
    public void setCurrentPoint(int[] currentPoint) {
        this.currentPoint = currentPoint.clone();
        calculatePoint();
    }
}