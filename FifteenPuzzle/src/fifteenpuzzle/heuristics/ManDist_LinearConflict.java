
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * LinearConflict Class
 * 
 * @author Tero Mäntylä
 */
public class ManDist_LinearConflict implements HeuristicInterface {
    private ManhattanDistance MD = new ManhattanDistance();;
    private Puzzle puzzle;
    private int rows;
    private int columns;
    
    
    /**
     * Description of linearConflict().
     * method finds conflicting numbers which are they own row or column but reversed order.
     * Every conflict adds +2 to counter. Why? Every conflict needs two extra movements to solution.
     * 
     * @return     amount of conflicts * 2
     */
    @Override
    public int calculate(Puzzle puzzle) {
        this.puzzle = puzzle;
        rows = puzzle.getNumberOfRows();
        columns = puzzle.getNumberOfColumns();
        int md = MD.calculate(puzzle);
        int lc = 0;
        for (int row = 0; row < rows; row++) {
            lc += calHorizontal(row);
        }
        for (int col = 0; col < columns; col++) {
            lc += calVertical(col);
        }    
        return md + lc;
    }
    
    
    /**
     * Description of update(int lastMove).
     * method calculates change in Manhattan distance and Linear Conflict.
     * 
     * @param lastMove  most recent move (0 - up, 1 - down, 2 - left, 3 - right)
     * @return    change in estimation value compared previous situation
     */
    @Override
    public int update(int lastMove) {
        int mdUpdate = MD.update(lastMove);       
        int now = 0;
        int old = 0;
        if (lastMove < 2) {
            int numOwnRow = (puzzle.lastMove) / 4;
            if (puzzle.getEmptyRow() == numOwnRow) {
                now = calHorizontal(numOwnRow);
                puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getLastMove());
                old = calHorizontal(numOwnRow);
                puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getEmpty());
            } else {
                if (lastMove == 0) {
                    if (puzzle.getEmptyRow() + 1 == numOwnRow) {
                        now = calHorizontal(numOwnRow);
                        puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getEmpty());
                        old = calHorizontal(numOwnRow);
                        puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getLastMove());
                    }
                } else {
                    if (puzzle.getEmptyRow() - 1 == numOwnRow) {
                        now = calHorizontal(numOwnRow);
                        puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getEmpty());
                        old = calHorizontal(numOwnRow);
                        puzzle.setCell(numOwnRow, puzzle.getEmptyCol(), puzzle.getLastMove());
                    }
                }
            }       
        } else {
            int numOwnCol = (puzzle.lastMove) % 4;
            if (puzzle.getEmptyCol() == numOwnCol) {
                now = calVertical(numOwnCol);
                puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getLastMove());
                old = calVertical(numOwnCol);
                puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getEmpty());
            } else {
                if (lastMove == 2) {
                    if (puzzle.getEmptyCol() + 1 == numOwnCol) {
                        now = calVertical(numOwnCol);
                        puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getEmpty());
                        old = calVertical(numOwnCol);
                        puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getLastMove());
                    }
                } else {
                    if (puzzle.getEmptyCol() - 1 == numOwnCol) {
                        now = calVertical(numOwnCol);
                        puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getEmpty());
                        old = calVertical(numOwnCol);
                        puzzle.setCell(puzzle.getEmptyRow(), numOwnCol, puzzle.getLastMove());
                    }
                }
            }
        }
        return mdUpdate + now - old;      
    }
    
    
    /**
     * Description of calCol(int row).
     * 
     * @param row       
     * @return          amount of conflicts * 2
     */
    private int calHorizontal(int row) {
        int lc = 0;
        int max = -1;
            for (int col = 0; col < columns; col++) {
                int num = puzzle.getNumberInCell(row, col);
                if (num != puzzle.getEmpty() && num / rows == row) {
                    if (num > max) {
                        max = num;
                    } else {
                        lc += 2;
                    }
                }
        }
        return lc;
    }
    
    
    /**
     * Description of calRow(int col).
     * 
     * @param col
     * @return          amount of conflicts * 2
     */
    private int calVertical(int col) {
        int lc = 0;
        int max = -1;
        for (int row = 0; row < rows; row++) {
            int num = puzzle.getNumberInCell(row, col);
            if (num != puzzle.getEmpty() && num % columns == col) {
                if (num > max) {
                    max = num;
                } else {
                    lc += 2;
                }
            }
        }
        return lc;
    }

}
