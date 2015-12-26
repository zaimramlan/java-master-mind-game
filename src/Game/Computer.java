package Game;

import java.util.Random;

/**
 * Created by Zaid on 09/05/2015.
 *
 * This class extends the designer class to include
 * and additional method named generatePattern
 * which generates code randomly.
 */
public class Computer extends Codemaker {
    private Random rand = new Random();

    public void generatePattern(int difficulty){
        int[] pattern = new int[this.SIZE];

        /**
         *  Randomly generates a number for each index of the code
         *  in the range of 1 - X.
         *  X is defined as difficulty level with values of 4, 6 and 8
         *  for EASY, MED and HARD respectively.
         */
        for (int i = 0; i < this.SIZE; i++) {
            pattern[i] = rand.nextInt((difficulty - 1) + 1) + 1;
        }

        setPattern(pattern);
    }
}
