
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 * HeuristicInterface Class.
 * This interface makes possible to use different heuristic fuctions in
 * IDA*-algorithm.
 * 
 * @author Tero Mäntylä
 */
public interface HeuristicInterface {
    
    public void setPuzzle(Puzzle puzzle);
    public int calculate(Puzzle puzzle);
    public int update(int dRow, int dCol);
}
