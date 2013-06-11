
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
    
    
    /**
     * Description of setUp().
     * This method sets value for set-variable by creating instance of MyHashSet class
     */
    @Before
    public void setUp() {
        set = new MyHashSet();
    }
    
    
    /**
     * Description of constructorIntializesCounter().
     * This test method is quite useless because counter is 
     * not really used any real purpose in this program.
     */
    @Test
    public void constructorIntializesCounter() { 
        assertEquals("Counter has wrong value", set.getCounter(), 0);
    }
    
    
    /**
     * Description of counterIncreasesCorrectly().
     * This test method is quite useless because counter is 
     * not really used any real purpose in this program.
     */
    @Test
    public void counterIncreasesCorrectly() {
        for (int i = 0; i < 10; i++) {
            set.insert(new Node(new Puzzle(), i));
            assertEquals("Counter has wrong value", set.getCounter(), i + 1);
        }
    }
    
    
    /**
     * Description of contains_methodReturnsRightBooleanValue().
     * This test method finds out if contain()-method really
     * finds searched node from the HashTable or not.
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
     * Description of putStaffToHashTable(int amount).
     * This method creates nodes with rundos state of puzzle and puts
     * them in to the Hash Table.
     * 
     * @param amount        number of nodes put in to the Hash Table
     */
    private void putStaffToHashTable(int amount) {
        for (int i = 0; i < amount; i++) {
            Node n = new Node(new Puzzle(), i);
            n.getPuzzle().shuffle();
            set.insert(n);
        }
    } 
}
