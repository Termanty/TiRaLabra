
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 *
 * @author Tero Mäntylä
 */
public class ManhattanDistanceNaive implements HeuristicInterface {
    private HeuristicInterface MD = new ManhattanDistance();
    private Puzzle puzzle;
  
    /**
     * Description of calculate().
     * 
     * @return  estimated distance to solution by Manhattan Distance heuristic
     */ 
    @Override
    public int calculate(Puzzle puzzle) {
        this.puzzle = puzzle; 
        return MD.calculate(puzzle);
    }

    
    /**
     * Description of update(int lastMove).
     * Because this is naive version, Manhattan Distance is calculated for hole puzzle.
     * 
     * @param lastMove  most recent move (0...3)
     * @return  estimated distance to solution by Manhattan Distance heuristic
     */
    @Override
    public int update(int lastMove) {
        return MD.calculate(puzzle);
    }
    
}
