package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import java.util.Arrays;

/**
 * IdaStar Class
 * Class uses IDA* algorithm to find the least amount of movements to solve a puzzle.
 * 
 * @author Tero Mäntylä
 */
public class IdaStar {
    
    private Puzzle puzzle;
    private final int ROWS;
    private final int COLUMNS;
    
    private boolean found;
    private byte[] optimalSolution;
    private int limit;
    
    private int md;
    private int lc;
    private long runningTime;
    
    
    /**
     * Description of constructor IdaStar(Puzzle puzzle).
     * 
     * @param puzzle  
     */   
    public IdaStar(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }
    
    
    /**
     * Description of getManhattanDistance().
     * 
     * @return      sum of distances of every number to their own place
     */
    public int getManhattanDistance() {
        return md;
    }
    
    
    /**
     * Description of getLinearConflict().
     * 
     * @return      amount of conflicts times two in puzzle
     */
    public int getLinearConflict() {
        return lc;
    }
    
    
    /**
     * Description of getRunningTime().
     * 
     * @return      execution time of findSolution method
     */
    public long getRunningTime() {
        return runningTime;
    }
    
    
    /**
     * Description of findSolution().
     * 
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */   
    public byte[] findSolution() {
        found = false;      
        md = manhattanDistance();
        lc = linearConflict();    
        int h = md + lc;
        limit = h + evenOrOddsolutionExtra();
        
        long timeAtStar = System.currentTimeMillis(); 
        while (!found) { 
//            System.out.print("limit " + limit + " ... ");
            stack = 0;
            DFS(0, new byte[100], -1, h);
            limit += 2;
//            System.out.println(System.currentTimeMillis() - timeAtStar + " ms");
            System.out.println("stack " + stack);
        }       
        runningTime = System.currentTimeMillis() - timeAtStar; 
        return optimalSolution;
    }
    
    private int stack = 0;
    private int cut = 2;
    
    /**
     * Description of DFS(int depth, byte[] path, int lastMove, int md).    
     * This method is basicly Depth First Search with heuristic to cut branches.
     * 
     * @param depth     recursion depth of search method
     * @param path      movements done so far
     * @param lastMove  variable remembers last done movement
     * @param h         evalueted distance to solution
     */   
    private void DFS(int depth, byte[] path, int lastMove, int h) {  
        
        if (depth > cut) {
            cut = depth;
        }
        
        if (depth + h > limit) {
            if (depth == cut - 1) {
                stack++;
            }

            return;
        }
        path[depth] = puzzle.lastMove;  
        if (h == 0) {
            optimalSolution = Arrays.copyOfRange(path, 1, depth + 1);
            found = true;
            return;
        }     
        if (!found && lastMove != 1 && puzzle.up()) {
            DFS(depth + 1, path, 0, h + changeMD(-1, 0) + updateLC(0));
            puzzle.down();
        }      
        if (!found && lastMove != 0 && puzzle.down()) {
            DFS(depth + 1, path, 1, h + changeMD(1, 0)+ updateLC(1));
            puzzle.up();
        }        
        if (!found && lastMove != 3 && puzzle.left()) {
            DFS(depth + 1, path, 2, h + changeMD(0, -1)+ updateLC(2));
            puzzle.right();
        }        
        if (!found && lastMove != 2 && puzzle.right()) {
            DFS(depth + 1, path, 3, h + changeMD(0, 1)+ updateLC(3));
            puzzle.left();
        }
//        
//        
//        if (!found && lastMove != 1 && puzzle.up()) {
//            DFS(depth + 1, path, 0, h + changeMD(-1, 0));
//            puzzle.down();
//        }      
//        if (!found && lastMove != 0 && puzzle.down()) {
//            DFS(depth + 1, path, 1, h + changeMD(1, 0));
//            puzzle.up();
//        }        
//        if (!found && lastMove != 3 && puzzle.left()) {
//            DFS(depth + 1, path, 2, h + changeMD(0, -1));
//            puzzle.right();
//        }        
//        if (!found && lastMove != 2 && puzzle.right()) {
//            DFS(depth + 1, path, 3, h + changeMD(0, 1));
//            puzzle.left();
//        }
    }
    
    
    /**
     * Description of manhattanDistance().
     * method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return          sum of Manhattan distances
     */    
    private int manhattanDistance() {
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
     * Description of changeMD(int dRow, int dColumn).
     * method calculates change in  Manhattan distance .
     * 
     * @param dRow
     * @param dColumn 
     * @return          change
     */   
    private int changeMD(int dRow, int dColumn) {
        if (dColumn == 0) {
            int rowTargetPos = (puzzle.lastMove - 1) / ROWS;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = (puzzle.lastMove - 1) % COLUMNS;
            return Math.abs(puzzle.getEmptyCol() - dColumn - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
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
    private int linearConflict() {
        int linearConflict = 0;
        for (int row = 0; row < ROWS; row++) {
            linearConflict += calHorizontal(row);
        }
        for (int col = 0; col < COLUMNS; col++) {
            linearConflict += calVertical(col);
        }    
        return linearConflict;
    }
    
    
    /**
     * Description of calHorizontal(int row).
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
//                        System.out.println("LC change in row " + row);
                    }
                }
        }
        return linearConflict;
    }
    
    
    /**
     * Description of calVertical(int col).
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
//                    System.out.println("LC change in col " + col);
                }
            }
        }
        return linearConflict;
    }
    
    
    /**
     * Description of updateLC(int lastMove).
     * method 
     * 
     * NOT READY YET!!!!
     * 
     * @param lastMove
     * @return
     */
    private int updateLC(int lastMove) {
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
     * Description of evenOrOddsolutionExtra().
     * Amount of the movements to solution is either even or odd.
     * It can be seen from the placement of the empty place in puzzle
     * are the amount of the movements to solution is either even or odd.
     * When we know this we can do iteration by adding two to limit instead of one.
     * Beginning limit comes from Manhattan distance. Adding one is needed if
     * Manhattan distance is odd and solution will be even or vise versa.
     * Method finds this out.
     * 
     * @return      +1 if MD and solution are not not both even or odd
     */    
    private int evenOrOddsolutionExtra() {
        int extra = 0;
        if (evenSolution()) {
            if (md % 2 != 0) {
                extra++;
            }
        } else {
            if (md % 2 == 0) {
                extra++;
            }
        }
        return extra;
    }
    
    
    /**
     * Description of evenSolution().
     * method finds out if the amount of the movements to the solution will be 
     * even or odd.
     * 
     * @return      true if solution is even ether false
     */    
    private boolean evenSolution() {
        if (puzzle.getEmptyRow() % 2 == 0) {
            if (puzzle.getEmptyCol() % 2 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (puzzle.getEmptyCol() % 2 == 0) {
                return false;
            } else {
                return true;
            }
        }
    }
}
