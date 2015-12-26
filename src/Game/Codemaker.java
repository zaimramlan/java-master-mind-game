package Game;

/**
 * Created by Zaid on 09/05/2015.
 *
 * This class is responsible in providing the code to be broken
 * and evaluating codebreaker's guess.
 */
public class Codemaker extends Player {
    private int[] pattern;          // The code pattern to be broken

    /**
     * The scoring is based on score - penalty.
     * For every correct guess, the codebreaker is penalized by 1.25 points
     * while every missed guess costs 2.5 points.
     */
    private void calculatePoint(){
        penalty += currentPoint[MISSED] * 1.25;
        penalty += currentPoint[CORRECT] * 2.5;
    }

    public int[] getPattern() {
        return pattern;
    }

    public void setPattern(int[] pattern) {
        this.pattern = pattern.clone();
    }


    /**
     * Compares guessed pattern with original pattern one by one left to right.
     * For every correct guess, where the guessed value is exactly the same
     * with the one on the original pattern, the correct point is increased by 1.
     * For every missed guess, where the value guessed exists in the original
     * pattern but is not on the same position, the missed point is increased by 1.
     * For every wrong guess, where the value guessed does not exist in the
     * original pattern, the wrong point is increased by 1.
     */
    public int[] checkPattern(int[] pattern){
        int[] points = new int[3];
        int[] tempPattern = this.pattern.clone();
        int[] guessPattern = pattern.clone();

        /**
         * Finds the correct guess by iterating both the guess and real code.
         * If the element is equal at an index, the correct point is increased and
         * the value is changed to -1.
         */
        for (int i = 0; i < SIZE; i++) {
            if(tempPattern[i] == guessPattern[i]){
                points[CORRECT]++;
                tempPattern[i] = -1;
                guessPattern[i] = -1;
            }
        }

        /**
         * Finds the missed guess by iterating both the guess and real code.
         * For each element in the guessed pattern, it will find the same element in the
         * real code and when found, the missed point is increased and the value is changed to -1.
         */
        for (int i = 0; i < SIZE; i++) {
            if(guessPattern[i] == -1) continue;
            for (int j = 0; j < SIZE; j++) {
                if(tempPattern[j] == -1) continue;
                if(tempPattern[j] == guessPattern[i]) {
                    points[MISSED]++;
                    tempPattern[j] = -1;
                    guessPattern[i] = -1;
                    continue;
                }
            }
        }

        /**
         * Since the total point for each turn is 4, it is safe to assume that
         * number of wrong equals to 4 - (correct plus missed guesses).
         */
        points[WRONG] = SIZE - points[CORRECT] - points[MISSED];

        this.currentPoint = points.clone();
        calculatePoint();
        return points;
    }

    /**
     * Updates the score every time the player moves.
     */
    public void setCurrentPoint(int[] currentPoint) {
        this.currentPoint = currentPoint.clone();
        calculatePoint();
    }
}
