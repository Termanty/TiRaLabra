
package heuristicTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManhattanDistance;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * ManhattanDistanceTest Class.
 * This class does test to make sure that Manhattan Distance is calculated
 * correctly and when movement is done also change value for Manhattan Distance
 * is properly calculated.
 *
 * @author termanty
 */
public class ManhattanDistanceTest {
    
    static byte[] test1 = {1,2,3,4,5,6,7,8,9,14,10,12,13,16,11,15};
    static byte[] test2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    static byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};   
        
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
            ManhattanDistance md = new ManhattanDistance();
            assertEquals("Manhattan Distance is wrongly calculated for test"+(i+1)+": ", results[i], md.calculate(p));
        }
    }
    
    /**
     * Description of update_mehtodReturnsRightValue().
     * Tests checks that method returns right change to
     * Manhattan Distance.
     */ 
    @Test 
    public void update_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[][] results = {{-1, 1, 1, -1}, {-1, 1, 1, -1}, {-1, 1, 1, 1}};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManhattanDistance md = new ManhattanDistance();
            md.calculate(p);
            p.up();
            assertEquals("Move up created wrong change in Manhattan distance in test"+(i+1), results[i][0], md.update(-1, 0));
            p.down();
            assertEquals("Move down created wrong change in Manhattan distance in test"+(i+1), results[i][1], md.update(1, 0));
            p.left();
            assertEquals("Move left created wrong change in Manhattan distance in test"+(i+1), results[i][2], md.update(0, -1));
            p.right();
            assertEquals("Move right created wrong change in Manhattan distance in test"+(i+1), results[i][3], md.update(0, 1));
        }
    }
}
