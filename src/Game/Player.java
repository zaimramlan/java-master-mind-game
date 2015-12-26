package Game;

/**
 * Created by Zaid on 10/05/2015.
 *
 * Super class for Codemaker and Codebreaker
 */
public abstract class Player {
    protected final int SIZE = 4;
    protected final int CORRECT = 0;
    protected final int MISSED = 1;
    protected final int WRONG = 2;
    protected final double score = 100;   // The original score
    protected double penalty;             // cumulative penalty of a round
    protected double highscore = 0;       // current session highscore
    protected int[] currentPoint;         // current point of guess

    public Player(){
        reset();
    }

    /**
     * The score is obtained by penalizing the original score.
     */
    public double getScore() {
        return score - penalty;
    }

    public void reset(){
        penalty = 0;
        currentPoint = new int[3];
    }

    /**
     * Set highest score then resets the player.
     */
    public void endGame(){
        updateHighscore();
        reset();
    }

    public double getHighscore() {
        return highscore;
    }

    /**
     * Updates session highest score if it is lower than
     * current score.
     */
    private void updateHighscore() {
        if(getScore() > highscore)
            highscore = getScore();
    }

    public int[] getCurrentPoint() {
        return currentPoint;
    }

    /**
     * Updates the score every time the player moves.
     */
    public void setCurrentPoint(int[] currentPoint) {
        this.currentPoint = currentPoint.clone();
    }
}
