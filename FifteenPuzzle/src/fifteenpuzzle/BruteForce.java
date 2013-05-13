/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteenpuzzle;

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
        if (!found && lastMove != 3 &&  puzzle.right()) {
            search(depth + 1, path, 2);
            puzzle.left();
        }
        if (!found && lastMove != 2 &&  puzzle.left()) {
            search(depth + 1, path, 3);
            puzzle.right();
        }
    }

    private boolean complete() {
        byte num = 1;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (i == p.length - 1 && j == p[i].length - 1) {
                    if (p[i][j] != 0) {
                        return false;
                    }
                } else if (p[i][j] != num) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }
    
    
}
