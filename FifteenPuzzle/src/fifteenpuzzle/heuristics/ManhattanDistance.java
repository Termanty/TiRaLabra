
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * ManhattanDistance Class.
 * This class uses Manhattan Distance to evaluate distance of puzzle to solution.
 * This heuristic is admissable so it never over estimate distance. 
 * Alone this is little weak function but it can make IDA* to solve average
 * random puzzle mostly under one minute.
 * 
 * @author Tero Mäntylä
 */
public class ManhattanDistance implements HeuristicInterface {
    
    private Puzzle puzzle;
    private int rows;
    private int columns;

    
    /**
     * Description of setPuzzle(Puzzle puzzle).
     * This method gives puzzle class variable link to new puzzle.
     * Method is used only in A*-algorithm.
     * 
     * @param puzzle
     */   
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    
    
    /**
     * Description of calculate(Puzzle puzzle).
     * This method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return          estimated amount of movements to solution
     */    
    @Override
    public int calculate(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.rows = puzzle.getNumberOfRows();
        this.columns = puzzle.getNumberOfColumns();
        int md = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int num = puzzle.getNumberInCell(row, col);
                if (num != puzzle.getEmpty()) {
                    int[] cordinates = puzzle.getCordinates(num);
                    md += Math.abs(cordinates[0] - num / rows);
                    md += Math.abs(cordinates[1] - num % columns);
                } 
            }
        }
        return md;
    }
    
    
    /**
     * Description of update(int lastMove).
     * This method calculates change in  Manhattan distance .
     * 
     * @param dRow  change in row
     * @param dCol  change in column
     * @return      change in estimation value compared previous situation
     */
    @Override
    public int update(int dRow, int dCol) {
        if (dCol == 0) {
            int rowTargetPos = puzzle.lastMove / rows;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = puzzle.lastMove % columns;
            return Math.abs(puzzle.getEmptyCol() - dCol - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
    }
}
