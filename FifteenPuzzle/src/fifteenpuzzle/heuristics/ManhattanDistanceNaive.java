
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * ManhattanDistanceNaive Class.
 * This class is called naive because is dum enough to calculate 
 * Manhattan Distanceevery for hole puzzle every time when movement is done.
 * This heuristic is admissable so it never over estimate distance. 
 * Alone this is little weak function but it can make IDA* to solve average
 * random puzzle mostly in a minute.
 * @author Tero Mäntylä
 */
public class ManhattanDistanceNaive implements HeuristicInterface {
    private HeuristicInterface md = new ManhattanDistance();
    private Puzzle puzzle;
  
    /**
     * Description of calculate().
     * This method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return  estimated distance to solution by Manhattan Distance heuristic
     */ 
    @Override
    public int calculate(Puzzle puzzle) {
        this.puzzle = puzzle;
        return md.calculate(puzzle);
    }

    
    /**
     * Description of update(int lastMove).
     * Because this is naive version, Manhattan Distance is calculated for hole puzzle.
     * 
     * @param dRow  change in row
     * @param dCol  change in col
     * @return  estimated distance to solution by Manhattan Distance heuristic
     */
    @Override
    public int update(int h, int dRow, int dCol) {
        return md.calculate(puzzle);
    }
    
    
    /**
     * Description of setPuzzle(Puzzle puzzle).
     * This method gives puzzle class variable link to new puzzle.
     * Method is used only in A*-algorithm.
     * 
     * @param puzzle
     */   
    @Override
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
}
