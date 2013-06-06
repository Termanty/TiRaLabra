/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private Node n;
    
    
    @Before
    public void setUp() {
        n = new Node(new Puzzle(), 0);
    }
    
    
    /**
     * Description of contructorIntializesVariables() 
     */
    @Test
    public void contructorIntializesVariables() {
        assertNotNull(n.getPuzzle());
        assertEquals("Cost variable has wrong value", n.getCost(), 0);
        assertEquals("Path variable has wrong value", n.getPath(), null);
        assertEquals("Next variable has wrong value", n.getNext(), null);
        
    }
}
