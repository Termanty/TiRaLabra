
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.searchalgorithm.BruteForce;

/**
 *
 * @author termanty
 */
public class BruteForceTest {
    
    public void run(byte[] sequence) {
        Puzzle p = new Puzzle();
        p.setPuzzle(sequence);
        
        BruteForce bf = new BruteForce(p);
        bf.solve();
    }
    
}
