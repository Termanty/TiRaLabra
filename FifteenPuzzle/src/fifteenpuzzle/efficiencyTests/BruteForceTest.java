
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.searchalgorithm.BruteForce;

/**
 * BruteForceTest Class.
 * This class does test for BruteForce search.
 * This class is here only for testing and comparing it against more state of art
 * algorithms like IDA* and A*. More you can read from Test Document.
 *
 * 
 * 
 *  !!!    THIS CLASS IS NOT DOCUMENTIED BECAUSE ITS CREATED FOR TEST PURPOSES.   !!!
 *  !!!    ALSO NO JUNIT TEST ARE DONE FOR THIS CLASS.                            !!!
 * 
 * 
 * 
 * 
 * @author Tero Mäntylä
 */
public class BruteForceTest {
    
    public void run(byte[] sequence) {
        Puzzle p = new Puzzle();
        p.setPuzzle(sequence);
        
        BruteForce bf = new BruteForce(p);
        bf.solve();
    }
    
}
