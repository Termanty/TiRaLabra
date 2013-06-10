
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * ManhattanDistance
 * 
 * @author Tero Mäntylä
 */
public class ManhattanDistance implements HeuristicInterface {
    
    private Puzzle puzzle;
    private final int ROWS;
    private final int COLUMNS;
    
    
    public ManhattanDistance(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }


    
    
    
    /**
     * Description of calculate().
     * method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return          sum of Manhattan distances
     */    
    @Override
    public int calculate() {
        int sumOfMDs = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int num = puzzle.getNumberInCell(row, column);
                if (num == puzzle.getEmpty()) {
                    continue;
                }
                int[] cordinates = puzzle.getCordinates(num);
                sumOfMDs += Math.abs(cordinates[0] - (num - 1) / ROWS);
                sumOfMDs += Math.abs(cordinates[1] - (num - 1) % COLUMNS);
            }
        }
        return sumOfMDs;
    }
    
    
    /**
     * Description of update(int lastMove).
     * method calculates change in  Manhattan distance .
     * 
     * @param lastMove
     * @return  
     */
    @Override
    public int update(int lastMove) {
        if (lastMove < 2) {
            int dRow = lastMove == 0 ? -1 : +1;
            int rowTargetPos = (puzzle.lastMove - 1) / ROWS;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int dColumn = lastMove == 2 ? -1 : +1;
            int colTargetPos = (puzzle.lastMove - 1) % COLUMNS;
            return Math.abs(puzzle.getEmptyCol() - dColumn - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
    }

}
