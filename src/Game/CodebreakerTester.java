package Game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zaid on 09/05/2015.
 *
 * This is a test class that tests the codebreaker.
 */

@RunWith(Parameterized.class)
public class CodebreakerTester {
    Codebreaker g = new Codebreaker();

    private double score;
    public CodebreakerTester(int[] point, double score){
        g.setCurrentPoint(point);
        this.score = score;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][]{
                {new int[]{4, 0, 0}, 100}, //point, score
                {new int[]{0, 4, 0}, 95}, //point, score
                {new int[]{0, 0, 4}, 90}, //point, score
                {new int[]{1, 1, 2}, 93.75}, //point, score
                {new int[]{1, 2, 1}, 95}, //point, score
                {new int[]{2, 1, 1}, 96.25}, //point, score
                {new int[]{0, 20, 20}, 25}, //point, score
                {new int[]{0, 0, 40}, 0}, //point, score
        });
    }

    // checking if score is correct
    @Test
    public void TestMethod() throws Exception {
        assertEquals(score, g.getScore(), 0.0);
    }
}
