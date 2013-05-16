
import fifteenpuzzle.IdaStar;
import fifteenpuzzle.Puzzle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for IdaStar class
 * 
 * @author Tero Mäntylä
 */
public class IdaStarTest {
    static byte[] esim1 = {2,4,10,9,8,3,1,5,11,13,12,14,7,16,6,15};
    static byte[] esim2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    static byte[] esim3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};
    static byte[] esim4 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1};

    
 /**
 * Tests for find method.
 * First will be test .
 */
    
    @Test
    public void findPath_methodFindsSolution() {
        byte[][] tests = {esim1, esim2, esim3, esim4};
        Puzzle p = new Puzzle();
        for (int i = 0; i < tests.length; i++) {
            p.setPuzzle(tests[i]);
            IdaStar idas = new IdaStar(p);
            byte[] solution = idas.findPath();
            for (int j = 0; j < solution.length; j++) {
                int[] numPos = p.getCordinates(solution[j]);
                if (p.getEmptyRow() == numPos[0]) {
                    if (p.getEmptyCol() > numPos[1]) {
                        p.right();
                    } else {
                        p.left();
                    }
                } else {
                    if (p.getEmptyRow() > numPos[0]) {
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
