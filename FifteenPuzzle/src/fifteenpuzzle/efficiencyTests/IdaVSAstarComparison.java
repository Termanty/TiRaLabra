
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManDist_LinearConflict;
import fifteenpuzzle.searchalgorithm.Astar;
import fifteenpuzzle.searchalgorithm.IdaStar;
import java.util.Arrays;

/**
 * IdaVSAstarComparison class.
 * This class compares efficiency of IDA*-algorithm and A*-algorithm.
 * It uses both Manhattan Distance and Linear Conflict to estimate distance
 * to solution.
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
public class IdaVSAstarComparison {
    
    public void run(byte[] sequence) {
        Puzzle p = new Puzzle(4,4);                           
        p.setPuzzle(sequence);
        
        System.out.println("\nTEST PUZZLE:");
        System.out.println(p);
        
        IdaStar idaStar = new IdaStar(p, new ManDist_LinearConflict());   
        byte[] solution = idaStar.findSolution();
        
        System.out.println("IDA*-algorithm");
        System.out.println("Running time was " + idaStar.getRunningTime() + " ms");
        System.out.println("Movement needed for solution: " + solution.length); 
        System.out.println("Optimal sequence of the movements to solution:");    
        System.out.println(Arrays.toString(solution)+"\n");
        
        System.out.println("A*-algorithm");
        System.out.println("running ...");
        Astar aStar = new Astar(p, new ManDist_LinearConflict());
        byte[] aStarSolution = aStar.findSolution();

        System.out.println("Running time was " + aStar.getRunningTime() + " ms");
        System.out.println("Movement needed for solution: "+aStarSolution.length);
        System.out.println(Arrays.toString(aStarSolution));
    }  
}
