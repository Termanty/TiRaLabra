
package datastructuretests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.Node;
import fifteenpuzzle.datastructure.NodeComparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * NodeComparatorTest Class
 * 
 * @author termanty
 */
public class NodeComparatorTest {
    
    NodeComparator nc;
    
    
    /**
     * Description of setUp(). 
     */
    @Before
    public void setUp() {
        nc = new NodeComparator();
    }

    
    /**
     * Description of comparatorReturnsZerowhenEqual(). 
     */
    @Test
    public void comparatorReturnsZerowhenEqual() {
        Node n1 = new Node(new Puzzle(), 2);
        Node n2 = new Node(new Puzzle(), 2);
        assertEquals("NodeCost values are equal, but retunr value is not zero: ", 0, nc.compare(n1, n2));
    }
    
     @Test
    public void comparatorReturnsPositiveWhenCostOfFirstNodeIsBigger() {
        Node n1 = new Node(new Puzzle(), 5);
        Node n2 = new Node(new Puzzle(), 2);
        assertEquals(3, nc.compare(n1, n2)); 
    }
    
    
    /**
     * Description of comparatorReturnsNegativeWhenCostOfFirstNodeIsSmaller().
     */
    @Test
    public void comparatorReturnsNegativeWhenCostOfFirstNodeIsSmaller() {
        Node n1 = new Node(new Puzzle(), 2);
        Node n2 = new Node(new Puzzle(), 7);
        assertEquals(-5, nc.compare(n1, n2)); 
    }
}
