
package searchalgorithmtests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.searchalgorithm.Astar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * AstarTest Class
 *
 * @author termanty
 */
public class AstarTest {
    
    static byte[] test1 = {1,2,3,4,5,6,7,8,9,14,10,12,13,16,11,15};
    static byte[] test2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    static byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};
    static byte[] test4 = {7,15,5,2,10,6,8,9,14,16,13,3,12,4,1,11};

    
    /**
     * Description of findSolution_methodFindsOptimalSequence().
     * Test uses preselected permutations test1-4 to check that the solution
     * really has the right sequence to solve puzzle. Test moves parts of the
     * puzzle one by one following the given solution. After last move test
     * makes check that puzzle is ready.
     */   
    @Test
    public void findSolution_methodFindsOptimalSequence() {
        byte[][] tests = {test1, test2, test3, test4};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            Astar idaStar = new Astar(p);
            byte[] solution = idaStar.findSolution();
            for (int j = 0; j < solution.length; j++) {
                int[] numPos = p.getCordinates(solution[j]);
                if (p.getEmptyRow() == numPos[0]) {
                    if (p.getEmptyCol() < numPos[1]) {
                        p.right();                        
                    } else {
                        p.left();                        
                    }
                } else {
                    if (p.getEmptyRow() < numPos[0]) {
                        p.down();                        
                    } else {
                        p.up();                       
                    }
                }
            }            
            assertEquals("Solution was wrong: test case " + (i+1), true, p.isReady());
        }
    }
}
