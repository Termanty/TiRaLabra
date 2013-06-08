
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * HeuristicInterface Class
 * 
 * @author Tero Mäntylä
 */
public interface HeuristicInterface {
    
    public int calculate();
    public int update(int lastMove);
}
