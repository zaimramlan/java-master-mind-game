package Game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zaid on 09/05/2015.
 *
 * This is a test class that tests the computer.
 */

@RunWith(Parameterized.class)
public class ComputerTester {
    private static final int EASY = 4;
    private static final int MED = 6;
    private static final int HARD = 8;
    Computer c = new Computer();

    private int[] guess;
    private int[] answer;
    public ComputerTester(int[] pattern1, int[] pattern2, int[] answer){
        c.setPattern(pattern1);
        this.guess = pattern2;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][]{
            {new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}, new int[]{4, 0, 0}}, //pattern1, pattern2, answer
            {new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}, new int[]{0, 4, 0}}, //pattern1, pattern2, answer
            {new int[]{1, 2, 3, 4}, new int[]{5, 5, 5, 5}, new int[]{0, 0, 4}}, //pattern1, pattern2, answer
            {new int[]{1, 1, 1, 2}, new int[]{1, 1, 2, 1}, new int[]{2, 2, 0}}, //pattern1, pattern2, answer
            {new int[]{1, 1, 1, 1}, new int[]{2, 1, 2, 1}, new int[]{2, 0, 2}}, //pattern1, pattern2, answer
        });
    }

    @Test
    public void TestPatternChecker() throws Exception {
        assertArrayEquals(c.checkPattern(guess), answer);
    }

    // checking if generated pattern in range of 1 - difficulty
    @Test
    public void TestPattern() throws Exception {
        c.generatePattern(EASY);
        for (int i = 0; i < 4; i++) {
            assertEquals(true, (c.getPattern()[i] > 0 && c.getPattern()[i] <= EASY));
        }
        c.generatePattern(MED);
        for (int i = 0; i < 4; i++) {
            assertEquals(true, (c.getPattern()[i] > 0 && c.getPattern()[i] <= MED));
        }
        c.generatePattern(HARD);
        for (int i = 0; i < 4; i++) {
            assertEquals(true, (c.getPattern()[i] > 0 && c.getPattern()[i] <= HARD));
        }
    }
}
