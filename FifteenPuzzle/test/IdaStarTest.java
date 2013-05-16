
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
    private byte[] test1 = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13}; // 61 siirtoa
    private byte[] test2 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1}; // 62 siirtoa
    private byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};
    private byte[] test4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private byte[] test5 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    
    public IdaStarTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
 /**
 * Tests for Constructor checks that all class variables have right values.
 * First will be test default case 4*4, and then random size puzzles.
 */
    
    @Test
    public void constructorInitalizesClassVariables() {
        
        assertNotNull("byte[][] array is null:", p.getPuzzle());
    }
    
}
