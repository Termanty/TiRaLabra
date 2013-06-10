package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.HeuristicInterface;
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
    private HeuristicInterface heuristicFuction;
    
    private long runningTime;
    
    
    /**
     * Description of constructor IdaStar(Puzzle puzzle).
     * 
     * @param puzzle  
     */   
    public IdaStar(Puzzle puzzle, HeuristicInterface heuristic) {
        this.puzzle = puzzle;
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
        this.heuristicFuction = heuristic;
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
        int h = heuristicFuction.calculate();
        limit = h + evenOrOddsolutionExtra(h);
        
        long timeAtStar = System.currentTimeMillis(); 
        while (!found) { 
            DFS(0, new byte[80], -1, h);
            limit += 2;
        }       
        runningTime = System.currentTimeMillis() - timeAtStar; 
        return optimalSolution;
    }

    
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
        if (depth + h > limit) {
            return;
        }
        path[depth] = puzzle.lastMove;  
        if (h == 0) {
            optimalSolution = Arrays.copyOfRange(path, 1, depth + 1);
            found = true;
            return;
        }   
 
        if (!found && lastMove != 1 && puzzle.up()) {
            DFS(depth + 1, path, 0, h + heuristicFuction.update(0));
            puzzle.down();
        }      
        if (!found && lastMove != 0 && puzzle.down()) {
            DFS(depth + 1, path, 1, h + heuristicFuction.update(1));
            puzzle.up();
        }        
        if (!found && lastMove != 3 && puzzle.left()) {
            DFS(depth + 1, path, 2, h + heuristicFuction.update(2));
            puzzle.right();
        }        
        if (!found && lastMove != 2 && puzzle.right()) {
            DFS(depth + 1, path, 3, h + heuristicFuction.update(3));
            puzzle.left();
        }    
    }
  
    
    /**
     * Description of evenOrOddsolutionExtra(int h).
     * Amount of the movements to solution is either even or odd.
     * It can be seen from the placement of the empty place in puzzle
     * are the amount of the movements to solution is either even or odd.
     * When we know this we can do iteration by adding two to limit instead of one.
     * Beginning limit comes from Manhattan distance. Adding one is needed if
     * Manhattan distance is odd and solution will be even or vise versa.
     * Method finds this out.
     * 
     * @param h     heuristic fuction value for puzzle
     * @return      +1 if MD and solution are not not both even or odd
     */    
    private int evenOrOddsolutionExtra(int h) {
        int extra = 0;
        if (evenSolution()) {
            if (h % 2 != 0) {
                extra++;
            }
        } else {
            if (h % 2 == 0) {
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
