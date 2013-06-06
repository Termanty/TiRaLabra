
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
 * NodeTest Class
 * 
 * @author termanty
 */
public class NodeTest {
    
    private Puzzle p;
    private Node n;
    
    
    /**
     * Description of setUp() 
     */
    @Before
    public void setUp() {
        p = new Puzzle();
        n = new Node(p, 0);
    }
    
    
    /**
     * Description of contructorIntializesVariables(). 
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
     */
    @Test
    public void getPuzzle_methodReturnsPuzzle() {
        assertArrayEquals("Method returns wrong array", p.getPuzzle(), n.getPuzzle().getPuzzle());  
    }
    
    
    /**
     * Description of setPath_methodSetRightValue(). 
     */
    @Test
    public void setPath_methodSetRightValue() {
        Node n1 = new Node(new Puzzle(), 5);
        n.setPath(n1);
        assertEquals("Mehtod sets value incorrectly", n1, n.getPath());  
    }
    
    
    /**
     * Description of getPuzzle_methodReturnsPuzzle(). 
     */
    @Test
    public void setNext_methodSetRightValue() {
        Node n1 = new Node(new Puzzle(), 5);
        n.setNext(n1);
        assertEquals("Mehtod sets value incorrectly", n1, n.getNext());  
    }
    
    
    /**
     * Description of equals_methodReturnFalseIfNodesNotEqual().
     * Nodes are not equal if byte[][] arrays are different
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
     * Nodes are equal if byte[][] arrays are equal
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
