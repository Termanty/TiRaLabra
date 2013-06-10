
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 *
 * @author Tero Mäntylä
 */
public class ManhattanDistanceNaive implements HeuristicInterface {
    private Puzzle puzzle;
    private final int ROWS;
    private final int COLUMNS;

    public ManhattanDistanceNaive(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }

    @Override
    public int calculate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(int lastMove) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
