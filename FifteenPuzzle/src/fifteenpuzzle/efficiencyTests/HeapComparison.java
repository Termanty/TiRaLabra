
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.datastructure.MyMinHeap;
import fifteenpuzzle.datastructure.Node;
import fifteenpuzzle.datastructure.NodeComparator;
import fifteenpuzzle.Puzzle;
import java.util.PriorityQueue;


/**
 * HeapComparison class.
 * This class compares efficiency of Java's PriorityQueue class and Heap class
 * More you can read from Test Document.
 *
 * 
 * 
 *  !!!    THIS CLASS IS NOT DOCUMENTIED BECAUSE ITS CREATED FOR TEST PURPOSES.   !!!
 *  !!!    ALSO NO JUNIT TEST ARE DONE FOR THIS CLASS.                            !!!
 * 
 * 
 * 
 * 
 * @author termanty
 */
public class HeapComparison {
    
    public void run() {
        Puzzle p = new Puzzle(4, 4);
        p.shuffle();
        
        System.out.println("\nHEAP EFFICIENCY TEST");
        
        int size = 1000;
        for (int i = 0; i < 4; i++) {
            size = size * 10;
            int[] num = new int[size];
            for (int j = 0; j < num.length; j++) {
                num[j] = (int) (Math.random() * 100);
            }
            System.out.println("\n"+size+" nodes to heap");
            System.out.println("MyMinHeap \t"+heapTime(size, num, p)+" ms");
            System.out.println("PriorityQueue \t"+priotityQueTime(size, num, p)+" ms");
        }      
    }

    private long heapTime(int size, int[] num, Puzzle p) {
        MyMinHeap h = new MyMinHeap(size+1);
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
