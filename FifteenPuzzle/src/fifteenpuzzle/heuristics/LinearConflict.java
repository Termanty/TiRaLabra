
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * LinearConflict Class
 * 
 * @author Tero Mäntylä
 */
public class LinearConflict implements HeuristicInterface {
    
    private Puzzle puzzle;
    private final int ROWS;
    private final int COLUMNS;

    public LinearConflict(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }
    
    
    /**
     * Description of linearConflict().
     * method finds conflicting numbers which are 
     * they own row or column but reversed order.
     * Every conflict adds +2 to counter.
     * Why? Every conflict needs two extra movements to solution.
     * 
     * @return          amount of conflicts * 2
     */
    @Override
    public int calculate() {
        byte linearConflict = 0;
        for (int row = 0; row < ROWS; row++) {
            linearConflict += calHorizontal(row);
        }
        for (int col = 0; col < COLUMNS; col++) {
            linearConflict += calVertical(col);
        }    
        return linearConflict;
    }
    
    
    /**
     * Description of update(int lastMove).
     * 
     * @param lastMove
     * @return
     */
    @Override
    public int update(int lastMove) {
        int old = 0;
        int now = 0;
        if (lastMove < 2) {
            int numOwnRow = (puzzle.lastMove - 1) / 4;
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
            int numOwnCol = (puzzle.lastMove - 1) % 4;
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
        return now - old;
    }
    
    
    /**
     * Description of calCol(int row).
     * 
     * @param row
     * @return          amount of conflicts * 2
     */
    private int calHorizontal(int row) {
        int linearConflict = 0;
        int max = -1;
            for (int col = 0; col < COLUMNS; col++) {
                int num = puzzle.getNumberInCell(row, col);
                if (num != puzzle.getEmpty() && (num - 1) / ROWS == row) {
                    if (num > max) {
                        max = num;
                    } else {
                        linearConflict += 2;
                    }
                }
        }
        return linearConflict;
    }
    
    
    /**
     * Description of calRow(int col).
     * 
     * @param col
     * @return          amount of conflicts * 2
     */
    private int calVertical(int col) {
        int linearConflict = 0;
        int max = -1;
        for (int row = 0; row < ROWS; row++) {
            int num = puzzle.getNumberInCell(row, col);
            if (num != puzzle.getEmpty() && (num - 1) % COLUMNS == col) {
                if (num > max) {
                    max = num;
                } else {
                    linearConflict += 2;
                }
            }
        }
        return linearConflict;
    }

}
