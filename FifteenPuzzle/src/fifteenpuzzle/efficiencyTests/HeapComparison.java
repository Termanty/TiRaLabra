
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.Heap;
import fifteenpuzzle.Node;
import fifteenpuzzle.NodeComparator;
import fifteenpuzzle.Puzzle;
import java.util.PriorityQueue;


/**
 * HeapComparison class
 * 
 * @author termanty
 */
public class HeapComparison {
    
    
    /**
     * Description of run().
     * method compares efficiency of Java's PriorityQueue class and Heap class.
     */
    public void run() {
        Puzzle p = new Puzzle(4, 4);
        p.shuffle();
        
        int size = 1000000;
        int[] num = new int[size];
        for (int i = 0; i < num.length; i++) {
            num[i] = (int)(Math.random()*1000);  
        }
        
        System.out.println(heapTime(size, num, p));
        System.out.println(priotityQueTime(size, num, p));       
    }

    private long heapTime(int size, int[] num, Puzzle p) {
        Heap h = new Heap(size+1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < num.length; i++) {
            h.insert(new Node(p, num[i]));
        }
        for (int i = 0; i < num.length; i++) {
            h.removeMin();
        }      
        long end = System.currentTimeMillis();        
        return end - start;
    }
    
    private long priotityQueTime(int size, int[] num, Puzzle p) {
        PriorityQueue<Node> que = new PriorityQueue<>(size+1, new NodeComparator());
        long start = System.currentTimeMillis();
        for (int i = 0; i < num.length; i++) {
            que.add(new Node(p, num[i]));
        }
        for (int i = 0; i < num.length; i++) {
            que.poll();
        }      
        long end = System.currentTimeMillis();        
        return end - start;
    }
    
}
