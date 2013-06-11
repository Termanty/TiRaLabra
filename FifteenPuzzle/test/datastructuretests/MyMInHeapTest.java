
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
 * MyMinHeapTest Class.
 * This class has tests to make sure that minheap implementation
 * works correctly in different situations.
 *
 * @author termanty
 */
public class MyMInHeapTest {
    
    MyMinHeap heap;
    Node n;
    
    
    /**
     * Description of setUp().
     * This method sets value for heap-variable by creating instance of MyMinHeap class.
     * It also creates instance of Node class.
     */
    @Before
    public void setUp() {
        heap = new MyMinHeap(100);
        n = new Node(new Puzzle(), 5);
    }
   
    
    /**
     * Description of contructorIntializesVariables(). 
     * This test method checks that removing node from empty heap
     * returns null.
     */
    @Test
    public void removeMin_methodReturnsNullWhenEmpty() {
        assertNull(heap.removeMin());     
    }
    
    
    /**
     * Description of afterOneInsertHeapIsNotEmpty(). 
     * This test method checks that heap is not empty after insert of
     * one node to empty heap.
     */
    @Test
    public void afterOneInsertHeapIsNotEmpty() {
        heap.insert(n);
        assertNotNull("Heap is empty after inserting one node to it", heap.removeMin());     
    }
    
    
    /**
     * Description of heapCanTakeLotMoreStuffThanInitialSizeWithoutBreak(). 
     * This test method makes sure that heap does not break even if
     * more nodes is added to heap than it can take.
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
     * This test method makes sure that minimum heap really works with random values. 
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
