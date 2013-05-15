package fifteenpuzzle;

import java.util.Arrays;

/**
 * IdaStar Class
 * Class uses IDA* algorithm to find the least amount of movements to solve a puzzle.
 * 
 * @author Tero Mäntylä
 */

public class IdaStar {
    private boolean found;
    private Puzzle puzzle; 
    private byte[] answer;
    private int limit;
    private final int ROWS;
    private final int COLUMNS;

    public IdaStar(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }
    
    public String findPath() {
        found = false;
        int md = manhattanDistance();
        int linearConflict = linearConflict();
        limit = md;
        System.out.println("MD at beginning: " + md);
        System.out.println("Linear conflict: " + linearConflict);
        while (!found) { 
            search(0, new byte[100], -1, md);
            limit++;
        }
        return null;
    }
    
    public void search(int depth, byte[] path, int lastMove, int md) {
//        System.out.println("Limit "+ limit);
//        System.out.println("md "+md);
//        System.out.println("MD "+manhattanDistance());
//        System.out.println("");
        if (depth + md > limit) {
            return;
        }

        path[depth] = puzzle.lastMove;

        if (puzzle.isReady()) {
            System.out.println("final limit: " + limit);
            System.out.println("final depth: " + depth +"\n");
            answer = Arrays.copyOfRange(path, 1, depth + 1);
            System.out.println(Arrays.toString(answer)+"\n");
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
        if (!found && lastMove != 3 && puzzle.right()) {
            search(depth + 1, path, 2, md + changeMD(0, -1));
            puzzle.left();
        }
        if (!found && lastMove != 2 && puzzle.left()) {
            search(depth + 1, path, 3, md + changeMD(0, 1));
            puzzle.right();
        }
    }
    
    public int manhattanDistance() {
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
    
    public int changeMD(int dRow, int dColumn) {
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
