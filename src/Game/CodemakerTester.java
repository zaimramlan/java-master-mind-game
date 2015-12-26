package Game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zaid on 12/05/2015.
 *
 * This is a test class that tests the codemaker
 */
@RunWith(Parameterized.class)
public class CodemakerTester {
    Codemaker g = new Codemaker();

    private double score;
    public CodemakerTester(int[] point, double score){
        g.setCurrentPoint(point);
        this.score = score;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][]{
                {new int[]{0, 0, 4}, 100}, //point, score
                {new int[]{0, 4, 0}, 95}, //point, score
                {new int[]{4, 0, 0}, 90}, //point, score
                {new int[]{2, 1, 1}, 93.75}, //point, score
                {new int[]{1, 2, 1}, 95}, //point, score
                {new int[]{1, 1, 2}, 96.25}, //point, score
                {new int[]{20, 20, 0}, 25}, //point, score
                {new int[]{40, 0, 0}, 0}, //point, score
        });
    }

    // checking if score is correct
    @Test
    public void TestMethod() throws Exception {
        assertEquals(score, g.getScore(), 0.0);
    }
}
