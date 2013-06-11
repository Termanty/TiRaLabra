
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
 * NodeComparatorTest Class.
 * This class test correct function of the NodeComparator class.
 * 
 * @author termanty
 */
public class NodeComparatorTest {
    
    NodeComparator nc;
    
    
    /**
     * Description of setUp().
     * This method creates instance of NodeComparator class.
     */
    @Before
    public void setUp() {
        nc = new NodeComparator();
    }

    
    /**
     * Description of comparatorReturnsZerowhenEqual(). 
     * This test method checks that return value is zero when
     * both nodes have same cost value.
     */
    @Test
    public void comparatorReturnsZerowhenEqual() {
        Node n1 = new Node(new Puzzle(), 2);
        Node n2 = new Node(new Puzzle(), 2);
        assertEquals("NodeCost values are equal, but retunr value is not zero: ", 0, nc.compare(n1, n2));
    }
    
    
    /**
     * Description of comparatorReturnsPositiveWhenCostOfFirstNodeIsBigger(). 
     * This test method checks that return value is positive when
     * first nodes have higher cost value than second node.
     */
    @Test
    public void comparatorReturnsPositiveWhenCostOfFirstNodeIsBigger() {
        Node n1 = new Node(new Puzzle(), 5);
        Node n2 = new Node(new Puzzle(), 2);
        assertEquals(3, nc.compare(n1, n2)); 
    }
    
    
    /**
     * Description of comparatorReturnsNegativeWhenCostOfFirstNodeIsSmaller(). 
     * This test method checks that return value is negative when
     * first node have lower cost value than second node.
     */
    @Test
    public void comparatorReturnsNegativeWhenCostOfFirstNodeIsSmaller() {
        Node n1 = new Node(new Puzzle(), 2);
        Node n2 = new Node(new Puzzle(), 7);
        assertEquals(-5, nc.compare(n1, n2)); 
    }
}
