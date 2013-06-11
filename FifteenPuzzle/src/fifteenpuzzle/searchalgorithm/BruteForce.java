
package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import java.util.Arrays;

/**
 * BruteForce Class.
 * This class does BruteForce search to find minimun amount of the movements
 * to solve puzzle. It needs iterative deepening to do so.
 * This class is here only for testing and comparing it agains more state of art
 * algorithms like IDA* and A*. More you can read from Test Document.
 * 
 * THIS CLASS IS NOT DOCUMENTIED BECAUSE ITS CREATED FOR TEST PURPOSES.
 * ALSO NO JUNIT TEST ARE DONE FOR THIS CLASS.
 * 
 * @author Tero Mäntylä
 */
public class BruteForce {
    
    private byte[][] p;
    private Puzzle puzzle;
    private byte[] answer;
    private boolean found;
    private int limit = 0;
   

    public BruteForce(Puzzle p) {
        this.puzzle = p;
        this.p = p.getPuzzle();
    }
    
    public byte[] solve(int limit) {
        this.limit = limit;
        return solve();
    }
    
    public byte[] solve() {       
        found = false;
        while(!found) {
            search(0, new byte[80], -1);
        }    
        return answer;
    }
    
    public void search(int depth, byte[] path, int lastMove) {
        if (depth > limit) {
            return;
        } 
        path[depth] = puzzle.lastMove;
        if (puzzle.isReady()) {
            System.out.println(limit);
            System.out.println(depth);
            answer = Arrays.copyOfRange(path, 1, depth+1);
            System.out.println(Arrays.toString(answer));
            System.out.println(puzzle.toString());
            found = true;
            return;
        }    
        if (!found && lastMove != 1 && puzzle.up()) {
            search(depth + 1, path, 0);
            puzzle.down();
        }
        if (!found && lastMove != 0 &&  puzzle.down()) {
            search(depth + 1, path, 1);
            puzzle.up();
        }
        if (!found && lastMove != 3 &&  puzzle.left()) {
            search(depth + 1, path, 2);
            puzzle.right();
        }
        if (!found && lastMove != 2 &&  puzzle.right()) {
            search(depth + 1, path, 3);
            puzzle.left();
        }
    }
    
}
