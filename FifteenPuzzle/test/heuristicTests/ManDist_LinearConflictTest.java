
package heuristicTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManDist_LinearConflict;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * ManDist_LinearConflictTest Class
 * This class does test to make sure that Linear Conflict is calculated
 * correctly and when movement is done also change value for Linear Conflict
 * is properly calculated.
 *
 * @author termanty
 */
public class ManDist_LinearConflictTest {
    
    static byte[] test1 = {1,4,3,2,5,6,7,8,9,14,10,12,15,16,11,13};
    static byte[] test2 = {5,3,1,2,7,15,16,4,9,6,10,11,13,8,14,12};
    static byte[] test3 = {5,6,3,4,9,2,7,8,12,1,16,10,13,14,11,15};
    
    @Before
    public void setUp() {
    }
    
    /**
     * Description of calculate_mehtodReturnsRightValue().
     * Tests checks that method returns right combined 
     * estimation of Linear Conflict and Manhattan Distance to solution.
     */ 
    @Test 
    public void calculate_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[] results = {18, 25, 18};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManDist_LinearConflict lc = new ManDist_LinearConflict();
            assertEquals("Distance is wrongly calculated for test"+(i+1)+": ", results[i], lc.calculate(p));
        }
    }
    
    /**
     * Description of update_mehtodReturnsRightValue().
     * Tests checks that method returns right change to
     *  Linear Conflict and Manhattan Distance.
     */ 
    @Test 
    public void update_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[][] results = {{1, -1, -1, 1}, {-1, 1, -1, 1}, {1, -1, 1, -1}};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManDist_LinearConflict lc = new ManDist_LinearConflict();
            lc.calculate(p);
            p.up();
            assertEquals("Move up created wrong change in test"+(i+1), results[i][0], lc.update(-1, 0));
            p.down();
            assertEquals("Move down created wrong change in test"+(i+1), results[i][1], lc.update(1, 0));
            p.left();
            assertEquals("Move left created wrong change in test"+(i+1), results[i][2], lc.update(0, -1));
            p.right();
            assertEquals("Move right created wrong change in test"+(i+1), results[i][3], lc.update(0, 1));
        }
    }
    
}
