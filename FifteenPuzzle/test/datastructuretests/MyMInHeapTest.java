
package datastructuretests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.MyMinHeap;
import fifteenpuzzle.datastructure.Node;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * MyMinHeapTest Class
 *
 * @author termanty
 */
public class MyMInHeapTest {
    
    MyMinHeap heap;
    Node n;
    
    
    /**
     * Description of setUp() 
     */
    @Before
    public void setUp() {
        heap = new MyMinHeap(100);
        n = new Node(new Puzzle(), 5);
    }
   
    
    /**
     * Description of contructorIntializesVariables(). 
     */
    @Test
    public void removeMin_methodReturnsNullWhenEmpty() {
        assertNull(heap.removeMin());     
    }
    
    
    /**
     * Description of afterOneInsertHeapIsNotEmpty(). 
     */
    @Test
    public void afterOneInsertHeapIsNotEmpty() {
        heap.insert(n);
        assertNotNull("Heap is empty after inserting one node to it", heap.removeMin());     
    }
    
    
    /**
     * Description of heapCanTakeLotMoreStuffThanInitialSizeWithoutBreak(). 
     */
    @Test
    public void heapCanTakeLotMoreStuffThanInitialSizeWithoutBreak() {
        for (int i = 0; i < 1000; i++) {
            heap.insert(new Node(new Puzzle(), (int)(Math.random()*100)));
        }
        assertNotNull(heap.removeMin());     
    }
    
    
    /**
     * Description of removeMin_methodReturnsMinimunOfHeap(). 
     * This tests that minimum heap really works with random values. 
     */
    @Test
    public void removeMin_methodReturnsMinimunOfHeap() {
        heap = new MyMinHeap(2000);
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100+1);
            heap.insert(new Node(new Puzzle(), arr[i]));  
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            assertEquals("Heap has values in wrong order: ", arr[i], heap.removeMin().getCost());
        }
    
    }
    
}
