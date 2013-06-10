
package fifteenpuzzle.heuristics;

import fifteenpuzzle.Puzzle;

/**
 *
 * @author Tero Mäntylä
 */
public class ManhattanDistanceNaive implements HeuristicInterface {
    private HeuristicInterface MD;

    public ManhattanDistanceNaive(Puzzle puzzle) {
        this.MD = new ManhattanDistance(puzzle);
    }

    @Override
    public int calculate() {
        return MD.calculate();
    }

    @Override
    public int update(int lastMove) {
        return MD.calculate();
    }
    
}
