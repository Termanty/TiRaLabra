/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import java.util.Arrays;

/**
 *
 * @author Tero Mäntylä
 */
public class BruteForce {
    
    private byte[][] p;
    private Puzzle puzzle;
    private byte[] answer;
    private boolean found;
    private int limit = 8;
   

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
        search(0, new byte[100], -1);    
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