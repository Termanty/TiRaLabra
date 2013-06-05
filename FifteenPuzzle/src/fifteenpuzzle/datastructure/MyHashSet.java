
package fifteenpuzzle.datastructure;

import java.util.Arrays;

/**
 * MyHashSet Class
 * 
 * @author termanty
 */
public class MyHashSet {
    private final int SIZE = 1500007;
    private MyLinkedList[] setTable = new MyLinkedList[SIZE];
    private int counter = 0;

    public int getCounter() {
        return counter;
    }
    
    
    
    public void insert(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (setTable[key] == null) {
            setTable[key] = new MyLinkedList(node, null);
        } else {
            MyLinkedList tmp = setTable[key];
            setTable[key] = new MyLinkedList(node, tmp);
        }
        counter++;
    }
    
    public boolean contains(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (setTable[key] == null) {
            return false;
        }
        MyLinkedList a = setTable[key];
        while (a != null) {
            if (node.equals(a.getNode())) {
                return true;
            }
            a = a.getNext();
        }
        return false;
    }
    
   
}
