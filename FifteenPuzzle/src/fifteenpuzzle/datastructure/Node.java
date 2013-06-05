
package fifteenpuzzle.datastructure;

import fifteenpuzzle.Puzzle;
import java.util.Objects;


public class Node {
    private Puzzle puzzle;
    private int cost;
    private Node path;

    public Node(Puzzle puzzle, int cost) {
        this.puzzle = puzzle;
        this.cost = cost;
        this.path = null;
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

    public void setPath(Node path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
