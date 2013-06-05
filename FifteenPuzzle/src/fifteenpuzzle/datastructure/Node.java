
package fifteenpuzzle.datastructure;

import fifteenpuzzle.Puzzle;
import java.util.Objects;


public class Node {
    private Puzzle puzzle;
    private int cost;
    private Node path;
    private Node next;

    public Node(Puzzle puzzle, int cost) {
        this.puzzle = puzzle;
        this.cost = cost;
        this.path = null;
        this.next = null;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public int getCost() {
        return cost;
    }

    public Node getPath() {
        return path;
    }

    public Node getNext() {
        return next;
    }

    public void setPath(Node path) {
        this.path = path;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    

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
