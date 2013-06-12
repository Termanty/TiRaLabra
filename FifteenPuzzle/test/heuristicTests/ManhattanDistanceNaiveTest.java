
package heuristicTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManhattanDistanceNaive;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * ManhattanDistanceNaiveTest Class.
 * This class does test to make sure that Manhattan Distance is calculated
 * correctly.
 *
 * @author termanty
 */
public class ManhattanDistanceNaiveTest {
    
    static byte[] test1 = {1,2,3,4,5,6,7,8,9,14,10,12,13,16,11,15};
    static byte[] test2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    static byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15}; 
    public ManhattanDistanceNaiveTest() {
    }
    
    /**
     * Description of calculate_mehtodReturnsRightValue().
     * Tests checks that method returns right Manhattan Distance to solution.
     */ 
    @Test 
    public void calculate_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[] results = {4, 18, 8};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManhattanDistanceNaive mdnaive = new ManhattanDistanceNaive();
            assertEquals("Manhattan Distance is wrongly calculated for test"+(i+1)+": ", results[i], mdnaive.calculate(p));
        }
    }
    
    /**
     * Description of update_mehtodReturnsRightValue().
     * Tests checks that method returns same values that previous test did
     */ 
    @Test 
    public void update_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[] results = {4, 18, 8};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManhattanDistanceNaive mdnaive = new ManhattanDistanceNaive();
            assertEquals("Manhattan Distance is wrongly calculated for test"+(i+1)+": ", results[i], mdnaive.calculate(p));
        }
    }
}
