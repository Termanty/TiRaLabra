
package fifteenpuzzle.datastructure;

import fifteenpuzzle.Puzzle;
import java.util.Objects;

/**
 * Node Class.
 * This class is datastructure to store neccessary information of the node in
 * network of unique permutations of the puzzle. Although only nodes which
 * are encountered during running A*-algorithm are created. This because puzzle 
 * network has 10 trillion nodes. Still there is problem to run out of memory 
 * with tough puzzles. Memory usage could be more efficient. Needeed information
 * is possible to pack less than a half what is used now.
 * 
 * @author termanty
 */
public class Node {
    private Puzzle puzzle;
    private int cost;
    private Node path;
    private Node next;

    
    /**
     * Description of constructor.
     * This constructor creates instance of Node class. 
     * 
     * @param puzzle    unique permutation of the puzzle
     * @param cost      = heuristics + distance from start
     */
    public Node(Puzzle puzzle, int cost) {
        this.puzzle = puzzle;
        this.cost = cost;
        this.path = null;
        this.next = null;
    }
    
    
    /**
     * Description of getPuzzle().
     * This method return link to puzzle class instance.
     * 
     * @return      permutation of the puzzle in this node 
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Description of getCost().
     * This method return cost which is sum of the amount of movements done
     * so far and value of heuristic function for this permutation.
     * 
     * @return      cost = heuristics + distance from start 
     */
    public int getCost() {
        return cost;
    }
    

    /**
     * Description of getPath().
     * This method return node that was used to arrive this node.
     * 
     * @return      node to arrive this node
     */
    public Node getPath() {
        return path;
    }
    

    /**
     * Description of getNext().
     * This method return node which will be next in linked list.
     * It will be used by MyHashSet class when collision happens.
     * 
     * @return      next node in linked list in MyHashSet Class
     */
    public Node getNext() {
        return next;
    }
    
    
    /**
     * Description of setPath(Node path).
     * This method set path-variable on value that tells from which node
     * to arrive this node.
     * 
     * @param path      previous node to arrive this node
     */
    public void setPath(Node path) {
        this.path = path;
    }
    
    
    /**
     * Description of setNext(Node next).
     * This method set next-variable on value that tells which is next
     * node in linked list. If it's value is null, then this node is
     * the last node in the list.
     * 
     * @param next      next node in linked list in MyHashSet Class
     */
    public void setNext(Node next) {
        this.next = next;
    }
    
    
    /**
     * Description of equals(Object obj).
     * This method return true if compared nodes have same permutation which 
     * means that in byte[][]-arrays numbers are in same order.
     * 
     * @param obj      object to compare this node
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return this.puzzle.equals(other.puzzle);
    }
   
}
