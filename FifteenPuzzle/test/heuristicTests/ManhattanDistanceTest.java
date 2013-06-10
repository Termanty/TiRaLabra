
package heuristicTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManDist_LinearConflict;
import fifteenpuzzle.heuristics.ManhattanDistance;
import fifteenpuzzle.searchalgorithm.IdaStar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ManhattanDistanceTest Class
 *
 * @author termanty
 */
public class ManhattanDistanceTest {
    
    static byte[] test1 = {1,2,3,4,5,6,7,8,9,14,10,12,13,16,11,15};
    static byte[] test2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    static byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15}; 
    

    @Before
    public void setUp() {
    }
    
        
    /**
     * Test for calculate_mehtodReturnsRightValue() method.
     * Tests checks that method returns right distance to solution.
     */ 
    @Test 
    public void calculate_mehtodReturnsRightValue() {
        byte[][] tests = {test1, test2, test3};
        int[] results = {4, 18, 8};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            ManhattanDistance md = new ManhattanDistance();
            assertEquals("", results[i], md.calculate(p));
        }
    }
}
