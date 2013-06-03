
package fifteenpuzzle;


public class Node {
    private Puzzle puzzle;
    private int ManhattanDistance;

    public Node(Puzzle puzzle, int ManhattanDistance) {
        this.puzzle = puzzle;
        this.ManhattanDistance = ManhattanDistance;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public int getManDist() {
        return ManhattanDistance;
    }
    
    

}
