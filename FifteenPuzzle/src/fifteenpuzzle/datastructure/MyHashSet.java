
package fifteenpuzzle.datastructure;

import java.util.Arrays;

/**
 * MyHashSet Class.
 * This is very light Hash table implementation for A* use.
 * 
 * @author termanty
 */
public class MyHashSet {
    private final int SIZE = 1500007;
    private Node[] hashTable;
    private int counter;

    /**
     * Description of constructor.
     * Constructor creates hash table for nodes which are in linked.
     */
    public MyHashSet() {
        hashTable = new Node[SIZE];
        counter = 0;
    }
    
    /**
     * Description of getCounter() 
     * 
     * @return          amount of nodes in hash table
     */  
    public int getCounter() {
        return counter;
    }
    
    
    /**
     * Description of insert(Node node) 
     * 
     * @param node      node contains one unique permutation of puzzle 
     */
    public void insert(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (hashTable[key] == null) {
            hashTable[key] = node;
        } else {
            Node tmp = hashTable[key];
            hashTable[key] = node;
            node.setNext(tmp);
        }
        counter++;
    }
    
    
    /**
     * Description of contains(Node node) 
     * 
     * @param node      node contains one unique permutation of puzzle.
     * @return          true if this permutation is already in hash tabla and false if not.
     */
    public boolean contains(Node node) {
        int key = Math.abs(Arrays.deepHashCode(node.getPuzzle().getPuzzle()) % SIZE);
        if (hashTable[key] == null) {
            return false;
        }
        Node a = hashTable[key];
        while (a != null) {
            if (node.equals(a)) {
                return true;
            }
            a = a.getNext();
        }
        return false;
    }
  
}
