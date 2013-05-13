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

    public IdaStar(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    public String findPath() {
        found = false;
        int md = manhattanDistance();
        limit = md;
        System.out.println("MD at beging: " + md);
        while (!found) { 
            search(0, new byte[100], -1, 0);
            limit++;
        }
        
        return null;
    }
    
    public void search(int depth, byte[] path, int lastMove, int md) {
        if (depth + manhattanDistance() > limit) {
            return;
        }
        
        path[depth] = puzzle.lastMove;

        if (puzzle.isReady()) {
            System.out.println("final limit: "+ limit);
            System.out.println("final depth: "+depth);
            answer = Arrays.copyOfRange(path, 1, depth+1);
            System.out.println(Arrays.toString(answer));
            System.out.println(puzzle.toString());
            found = true;
            return;
        }    
        
        if (!found && lastMove != 1 && puzzle.up()) {
            search(depth + 1, path, 0, md + changeMD(0,-1));
            puzzle.down();
        }
        if (!found && lastMove != 0 &&  puzzle.down()) {
            search(depth + 1, path, 1, md + changeMD(0,1));
            puzzle.up();
        }
        if (!found && lastMove != 3 &&  puzzle.right()) {
            search(depth + 1, path, 2, md + changeMD(-1,0));
            puzzle.left();
        }
        if (!found && lastMove != 2 &&  puzzle.left()) {
            search(depth + 1, path, 3, md + changeMD(1,0));
            puzzle.right();
        }
    }
    
    
    public int manhattanDistance() {
        int sumOfMDs = 0;
        for (int i = 0; i < puzzle.getHigth(); i++) {
            for (int j = 0; j < puzzle.getLength(); j++) {
                int num = puzzle.getNumber(i, j);
                if (num == puzzle.getEmpty()) continue;
                int[] cordinates = puzzle.getCoordinates(num);
                sumOfMDs += Math.abs(cordinates[0] - (num - 1)/puzzle.getHigth());
                sumOfMDs += Math.abs(cordinates[1] - (num - 1)%puzzle.getLength());
            }
        }
//        System.out.println(sumOfMDs);
        return sumOfMDs;
    }
    
    public int changeMD(int dx, int dy) {
        int change = 0;
//        int xTargetPos = (puzzle.lastMove - 1)%puzzle.getLength();
//        int yTargetPos = (puzzle.lastMove - 1)/puzzle.getHigth();
//        if (puzzle.getEmptyX() - dx < xTargetPos) {
//            change -= dx;
//        } else if (puzzle.getEmptyX() - dx > xTargetPos) {
//            change += dx;
//        } else {
//            change += Math.abs(dx);
//        }
//        if (puzzle.getEmptyY() - dy < yTargetPos) {
//            change -= dy;
//        } else if (puzzle.getEmptyY() - dy > yTargetPos) {
//            change += dy;
//        } else {
//            change += Math.abs(dy);
//        }
        return change;
    }
    
    
}
