package fifteenpuzzle;

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
    
    private int ManhattanDistance;
    private int LinearConflict;
    private long runningTime;
    
  
    
    public IdaStar(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }

    public int getManhattanDistance() {
        return ManhattanDistance;
    }

    public int getLinearConflict() {
        return LinearConflict;
    }

    public long getRunningTime() {
        return runningTime;
    }
    
    public byte[] findPath() {
        found = false;
        ManhattanDistance = manhattanDistance();
        LinearConflict = linearConflict();
        limit = ManhattanDistance;
        long timeAtStar = System.currentTimeMillis();
        while (!found) { 
            System.out.print("limit " + limit + " ... ");
            search(0, new byte[100], -1, ManhattanDistance);
            limit++;
            System.out.println(System.currentTimeMillis() - timeAtStar + " ms");
        }
        runningTime = System.currentTimeMillis() - timeAtStar;
        return optimalSolution;
    }
    
    private void search(int depth, byte[] path, int lastMove, int md) {
        if (depth + md > limit) {
            return;
        }

        path[depth] = puzzle.lastMove;

        if (puzzle.isReady()) {
            optimalSolution = Arrays.copyOfRange(path, 1, depth + 1);
            found = true;
            return;
        }

        if (!found && lastMove != 1 && puzzle.up()) {
            search(depth + 1, path, 0, md + changeMD(-1, 0));
            puzzle.down();
        }
        if (!found && lastMove != 0 && puzzle.down()) {
            search(depth + 1, path, 1, md + changeMD(1, 0));
            puzzle.up();
        }
        if (!found && lastMove != 3 && puzzle.left()) {
            search(depth + 1, path, 2, md + changeMD(0, -1));
            puzzle.right();
        }
        if (!found && lastMove != 2 && puzzle.right()) {
            search(depth + 1, path, 3, md + changeMD(0, 1));
            puzzle.left();
        }
    }
    
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
    
    private int changeMD(int dRow, int dColumn) {
        if (dColumn == 0) {
            int rowTargetPos = (puzzle.lastMove - 1) / ROWS;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = (puzzle.lastMove - 1) % COLUMNS;
            return Math.abs(puzzle.getEmptyCol() - dColumn - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
    }
    
    private int linearConflict() {
        int linearConflict = 0;
        for (int row = 0; row < ROWS; row++) {
            int max = -1;
            for (int column = 0; column < COLUMNS; column++) {
                int num = puzzle.getNumberInCell(row, column);
                if (num != puzzle.getEmpty() && (num - 1) / ROWS == row) {
                    if (num > max) {
                        max = num;
                    } else {
                        linearConflict += 2;
                    }
                }
            }
        }
        for (int collumn = 0; collumn < COLUMNS; collumn++) {
            int max = -1;
            for (int row = 0; row < ROWS; row++) {
                int num = puzzle.getNumberInCell(row, collumn);
                if (num != puzzle.getEmpty() && (num - 1) % COLUMNS == collumn) {
                    if (num > max) {
                        max = num;
                    } else {
                        linearConflict += 2;
                    }
                }
            }
        }    
        return linearConflict;
    }
    
    private int changeLinearConflict(int dRow, int dCollumn) {
        if (needToChangeLinearConflict(dRow, dCollumn)) {
            return linearConflict();
        }
        return 0;
    }
    
    private boolean needToChangeLinearConflict(int dRow, int dCollumn) {
        int num = puzzle.lastMove - 1;
        int rowTargetPos = (puzzle.lastMove - 1) % COLUMNS;
        int colTargetPos = (puzzle.lastMove - 1) / ROWS;
        
        
        if (num / ROWS == puzzle.getEmptyRow()) {
            return true;
        }
        if (num % COLUMNS == puzzle.getEmptyCol()) {
            return true;
        }
        if (dRow == 0) {
            if (num % COLUMNS == puzzle.getEmptyCol() + dRow) {
                return true;
            }
        } else {
            if (num / ROWS == puzzle.getEmptyRow() + dRow) {
                return true;
            }   
        }
        return false;
    }
    
    
}
