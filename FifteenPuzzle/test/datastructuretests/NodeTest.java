
package datastructuretests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * NodeTest Class.
 * This class does tests to make sure that all methods are doing
 * as planed.
 * 
 * @author termanty
 */
public class NodeTest {
    
    private Puzzle p;
    private Node n;
    
    
    /**
     * Description of setUp().
     * This method creates instances of Puzzle and Node classes.
     */
    @Before
    public void setUp() {
        p = new Puzzle();
        n = new Node(p, 0);
    }
    
    
    /**
     * Description of contructorIntializesVariables().
     * This test method checks that consructor initializes class variables
     * with correct values.
     */
    @Test
    public void contructorIntializesVariablesAndGettersReturnsRightValues() {
        assertNotNull(n.getPuzzle());
        assertEquals("Cost variable has wrong value", 0, n.getCost());
        assertEquals("Path variable has wrong value", null, n.getPath());
        assertEquals("Next variable has wrong value", null, n.getNext());   
    }
    
    
    /**
     * Description of getPuzzle_methodReturnsPuzzle(). 
     * This test method check return values correctness.
     */
    @Test
    public void getPuzzle_methodReturnsPuzzle() {
        assertArrayEquals("Method returns wrong array", p.getPuzzle(), n.getPuzzle().getPuzzle());  
    }
    
    
    /**
     * Description of setPath_methodSetRightValue(). 
     * This test method makes sure that correct value is set for class
     * variable path.
     */
    @Test
    public void setPath_methodSetRightValue() {
        Node n1 = new Node(new Puzzle(), 5);
        n.setPath(n1);
        assertEquals("Mehtod sets value incorrectly", n1, n.getPath());  
    }
    
    
    /**
     * Description of setNext_methodSetRightValue(). 
     * This test method makes sure that correct value is set for class
     * variable next.
     */
    @Test
    public void setNext_methodSetRightValue() {
        Node n1 = new Node(new Puzzle(), 5);
        n.setNext(n1);
        assertEquals("Mehtod sets value incorrectly", n1, n.getNext());  
    }
    
    
    /**
     * Description of equals_methodReturnFalseIfNodesNotEqual().
     * This test method makes sure that false is returned
     * if nodes are not equal which means that byte[][]-arrays are different.
     */
    @Test
    public void equals_methodReturnFalseIfNodesNotEqual() {
        Puzzle p1 = new Puzzle();
        p1.shuffle();
        Node n1 = new Node(p1, 5);
        assertFalse("Method returns True even nodes are not equal", n.equals(n1)); 
    }
    
    
    /**
     * Description of equals_methodReturnFalseIfNodesNotEqual().
     * This test method makes sure that true is returned
     * if nodes are equal which means that byte[][]-arrays are same.
     */
    @Test
    public void equals_methodReturnTrueIfNodesEqual() {
        p.shuffle();
        Puzzle p1 = new Puzzle();
        p1.setPuzzle(p.getPuzzle());
        Node n1 = new Node(p1, 5);
        assertTrue("Method returns False but nodes are equal", n.equals(n1)); 
    }
    
}
