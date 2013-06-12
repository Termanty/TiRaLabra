
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.ManDist_LinearConflict;
import fifteenpuzzle.heuristics.ManhattanDistance;
import fifteenpuzzle.heuristics.ManhattanDistanceNaive;
import fifteenpuzzle.searchalgorithm.IdaStar;
import java.util.Arrays;

/**
 * HeuristicComparison class.
 * This class compares efficiency of different heuristics in IDA*-algorithm.
 * Heuristics are
 * 1.   Manhattan Distance naive - calculates all the distances after every move.
 * 2.   Manhattan Distance - this is clever and it calculates only change part after movement.
 * 3.   Manhattan Distance and Linear Conflict
 * 
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
public class HeuristicComparison {
    
    public void run(byte[] sequence) {
        Puzzle p = new Puzzle(4,4);                           
        p.setPuzzle(sequence);
        
        System.out.println("\nEFFICIENCY TEST FOR DIFFERENT HEURISTICS");
        System.out.println("IDA*-algorithm is used for solution finding\n");
        System.out.println("puzzle");
        System.out.println(p);
        
        System.out.println("1. test - Manhattan Distance naive:");
        System.out.println("   Estimated distance: " + new ManhattanDistanceNaive().calculate(p));
        IdaStar idaStar1 = new IdaStar(p, new ManhattanDistanceNaive());   
        byte[] solution1 = idaStar1.findSolution();
        System.out.println("   running time: " + idaStar1.getRunningTime() + " ms");
        System.out.println("   moves "+ solution1.length);
        
        System.out.println("2. test - Manhattan Distance : \t");
        System.out.println("   Estimated distance: " + new ManhattanDistance().calculate(p));
        IdaStar idaStar2  = new IdaStar(p, new ManhattanDistance());   
        solution1 = idaStar2.findSolution();
        System.out.println("   running time: " + idaStar2.getRunningTime() + " ms");
        System.out.println("   moves "+ solution1.length);
        
        System.out.println("3. test - MD + Linear Conflict: \t ");
        System.out.println("   Estimated distance: " + new ManDist_LinearConflict().calculate(p));
        IdaStar idaStar3  = new IdaStar(p, new ManDist_LinearConflict());   
        solution1 = idaStar3.findSolution();
        System.out.println("   running time: " + idaStar3.getRunningTime() + " ms");
        System.out.println("   moves "+ solution1.length);
        
        System.out.println("\nOptimal sequence of the movements to solution:");    
        System.out.println(Arrays.toString(solution1));
        
    }
}
