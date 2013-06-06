
package fifteenpuzzle.datastructure;

import fifteenpuzzle.Puzzle;
import java.util.Objects;

/**
 * Node Class.
 * Every unique permutation of the puzzle is a node. Although only nodes which
 * are met during running A*-algorithm are created. This because puzzle has
 * 10 trillion nodes. There is already problem to run out of memory 
 * with tough puzzles in A*.
 * 
 * @author termanty
 */
public class Node {
    private Puzzle puzzle;
    private int cost;
    private Node path;
    private Node next;

    
    /**
     * Description of constructor
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
     * Description of getPuzzle() 
     * 
     * @return      cost = heuristics + distance from start 
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Description of getCost() 
     * 
     * @return      cost = heuristics + distance from start 
     */
    public int getCost() {
        return cost;
    }
    

    /**
     * Description of getPath() 
     * 
     * @return      node to arrive this node
     */
    public Node getPath() {
        return path;
    }
    

    /**
     * Description of getNext() 
     * 
     * @return      next node in linked list in MyHashSet Class
     */
    public Node getNext() {
        return next;
    }
    
    
    /**
     * Description of setPath(Node path) 
     * 
     * @param path      previous node to arrive this node
     */
    public void setPath(Node path) {
        this.path = path;
    }
    
    
    /**
     * Description of setNext(Node next)
     * 
     * @param next      next node in linked list in MyHashSet Class
     */
    public void setNext(Node next) {
        this.next = next;
    }
    
    
    /**
     * Description of equals(Object obj)
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
