
package fifteenpuzzle.datastructure;

import java.util.Arrays;

/**
 * MyHashSet Class.
 * This is very light Hash table implementation for A* use.
 * It's lacking many features of normal hash table. You can only 
 * put nodes to it and check if some nodes are there already. You can't
 * remove any nodes from the hash table. If many nodes have same hash they are
 * linked together using Node Class next-variable.
 * Counter was used mainly test purposes for finding proper size of table.
 * It can be removed.
 * 
 * @author termanty
 */
public class MyHashSet {
    
    private final int SIZE = 1500007;
    private Node[] hashTable;
    private int counter;

    /**
     * Description of constructor.
     * Constructor creates hash table which size is found from experimental way.
     */
    public MyHashSet() {
        hashTable = new Node[SIZE];
        counter = 0;
    }
    
    
    /**
     * Description of getCounter().
     * This method return counter value which tells how many nodes is in table.
     * 
     * @return          amount of nodes in hash table
     */  
    public int getCounter() {
        return counter;
    }
    
    
    /**
     * Description of insert(Node node).
     * This method puts node to hash table. If there is collition of the hash values,
     * new node is put to the first place in the list of nodes.
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
     * Description of contains(Node node).
     * This method returns true if the searched node is in hash table and false
     * other vice. Nodes are same if the numbers in byte[][]-array are in same order.
     * 
     * @param node      node contains one unique permutation of puzzle.
     * @return          true if this permutation is already in hash table and false if not.
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
