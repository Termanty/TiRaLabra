
package fifteenpuzzle;


public class Node {
    private Puzzle puzzle;
    private int cost;
    private Node path;

    public Node(Puzzle puzzle, int cost) {
        this.puzzle = puzzle;
        this.cost = cost;
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
    
}
