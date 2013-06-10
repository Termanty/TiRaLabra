
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * ManhattanDistance
 * 
 * @author Tero Mäntylä
 */
public class ManhattanDistance implements HeuristicInterface {
    
    private Puzzle puzzle;
    private int rows;
    private int columns;
    
    
    /**
     * Description of calculate().
     * method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return          estimated amount of movements to solution
     */    
    @Override
    public int calculate(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.rows = puzzle.getNumberOfRows();
        this.columns = puzzle.getNumberOfColumns();
        int sumOfMDs = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int num = puzzle.getNumberInCell(row, col);
                if (num == puzzle.getEmpty()) {
                    continue;
                }
                int[] cordinates = puzzle.getCordinates(num);
                sumOfMDs += Math.abs(cordinates[0] - (num - 1) / rows);
                sumOfMDs += Math.abs(cordinates[1] - (num - 1) % columns);
            }
        }
        return sumOfMDs;
    }
    
    
    /**
     * Description of update(int lastMove).
     * method calculates change in  Manhattan distance .
     * 
     * @param lastMove  most recent move (0 - up, 1 - down, 2 - left, 3 - right)
     * @return    change in estimation value compared previous situation
     */
    @Override
    public int update(int lastMove) {
        if (lastMove < 2) {
            int dRow = lastMove == 0 ? -1 : +1;
            int rowTargetPos = (puzzle.lastMove - 1) / rows;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int dColumn = lastMove == 2 ? -1 : +1;
            int colTargetPos = (puzzle.lastMove - 1) % columns;
            return Math.abs(puzzle.getEmptyCol() - dColumn - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
    }

}
