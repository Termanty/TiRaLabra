
package datastructuretests;

import fifteenpuzzle.*;
import fifteenpuzzle.datastructure.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * myHashSetTest Class
 * 
 * @author Tero Mäntylä
 */
public class myHashSetTest {
    MyHashSet set;   
    
    @Before
    public void setUp() {
        set = new MyHashSet();
    }
    
    /**
     * Description of constructorIntializesCounter() 
     */
    @Test
    public void constructorIntializesCounter() { 
        assertEquals("Counter has wrong value", set.getCounter(), 0);
    }
    
    
    /**
     * Description of counterIncreasesCorrectly() 
     */
    @Test
    public void counterIncreasesCorrectly() {
        for (int i = 0; i < 10; i++) {
            set.insert(new Node(new Puzzle(), i));
            assertEquals("Counter has wrong value", set.getCounter(), i + 1);
        }
    }
    
    
    /**
     * Description of contains_methodReturnsRightBooleanValue() 
     */
    @Test
    public void contains_methodReturnsRightBooleanValue() {
        Node n1 = new Node(new Puzzle(), 0);
        Node n2 = new Node(new Puzzle(), 2);
        n1.getPuzzle().shuffle();
        n2.getPuzzle().shuffle();   
        set.insert(n2);
        assertEquals("Should return true", set.contains(n2), true);
        assertEquals("Should return false", set.contains(n1), false);  
    }
    
    
    /**
     * Description of putStaffToHashTable(int amount)
     * 
     * @param amount        number of nodes in Hash Table
     */
    private void putStaffToHashTable(int amount) {
        for (int i = 0; i < amount; i++) {
            Node n = new Node(new Puzzle(), i);
            n.getPuzzle().shuffle();
            set.insert(n);
        }
    }
    
    
}
