package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.HeuristicInterface;
import java.util.Arrays;

/**
 * IdaStar Class
 * Class uses IDA* algorithm to find the least amount of movements 
 * to solve a puzzle. It's combination of iterative deepening and depth first
 * search with heuristic to cut branches. Iterative deepening makes sure that
 * what we find is minimum solution and heuristic makes algorithm much faster.
 * This class can use different heuristic function to find solution.
 * Some special features are that in iteration limit is increased in steps of two.
 * This is done because it's realy trivial to find out if the amount of the moves
 * to solution is even or odd.
 * 
 * @author Tero Mäntylä
 */
public class IdaStar {
    
    private Puzzle puzzle;
    private final int ROWS;
    private final int COLUMNS;
    
    private boolean found;
    private byte[] path = new byte[80];
    private byte[] solution;
    private int limit;
    private HeuristicInterface heuristicFuction;
    
    private long runningTime;
    
    
    /**
     * Description of constructor IdaStar(Puzzle puzzle, HeuristicInterface heuristic).
     * 
     * @param puzzle
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
     * This method return time to find minimum solution.
     * 
     * @return      execution time of findSolution method
     */
    public long getRunningTime() {
        return runningTime;
    }
    
    
    /**
     * Description of findSolution().
     * This method does iterative deepening part of IDA*-algorithm.
     * At first is find intial limit to start. It's find in two steps 
     * by estimating amount of movements to solution and figuring out if
     * the amount of the movements is even or odd.
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */   
    public byte[] findSolution() {
        found = false;      
        int h = heuristicFuction.calculate(puzzle);
        int e = evenOrOddsolutionExtra(h);
        limit = h + e;       
        long timeAtStar = System.currentTimeMillis(); 
        while (!found) { 
            DFS(0, -1, h);
            limit += 2;
//            System.out.println("limit: " + limit +"\t\t time: "+(System.currentTimeMillis()-timeAtStar));
        }       
        runningTime = System.currentTimeMillis() - timeAtStar; 
        return solution;
    }

    
    /**
     * Description of DFS(int depth, byte[] path, int lastMove, int md).    
     * This method is Depth First Search with heuristic to cut branches.
     * 
     * @param depth     recursion depth of search method
     * @param move      variable remembers last done movement
     * @param h         estimated distance to solution
     */   
    private void DFS(int depth, int move, int h) {         
        if (depth + h > limit) {
            return;
        }
        path[depth] = puzzle.lastMove; 
        if (h == 0) {     
            solution = Arrays.copyOfRange(path, 1, depth + 1);
            found = true;
            return;
        }
        depth++;
        if (!found && move != 1 && puzzle.up()) {
            DFS(depth, 0, h + heuristicFuction.update(-1, 0));
            puzzle.down();
        }      
        if (!found && move != 0 && puzzle.down()) {
            DFS(depth, 1, h + heuristicFuction.update(+1, 0));
            puzzle.up();
        }        
        if (!found && move != 3 && puzzle.left()) {
            DFS(depth, 2, h + heuristicFuction.update(0, -1));
            puzzle.right();
        }        
        if (!found && move != 2 && puzzle.right()) {
            DFS(depth, 3, h + heuristicFuction.update(0, +1));
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
     * @param h     estimated distance to solution
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
