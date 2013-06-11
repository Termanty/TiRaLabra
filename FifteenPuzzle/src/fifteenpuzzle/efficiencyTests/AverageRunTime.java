
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.searchalgorithm.IdaStar;
import fifteenpuzzle.Puzzle;
import fifteenpuzzle.heuristics.*;
import java.util.Arrays;

/**
 * AverageRunTime Class.
 * This class runs 100 times IDA* with random puzzle. 
 * As a result we can get rough idea of average time to solve puzzle and
 * also we get average amount of movements to solve puzzle. More you can
 * read Test Document.
 * 
 * THIS CLASS IS NOT DOCUMENTIED BECAUSE ITS CREATED FOR TEST PURPOSES.
 * ALSO NO JUNIT TEST ARE DONE FOR THIS CLASS.
 * 
 * @author termanty
 */
public class AverageRunTime {
    Puzzle p = new Puzzle(4,4);
    HeuristicInterface h;

    public AverageRunTime(HeuristicInterface h) {
        p = new Puzzle(4,4);
        this.h = h;
    }

    public void run() {       
        System.out.println("\nAVERAGE RUNNING TIME TEST:\n");  
        System.out.println("running...   this WILL take some time\n");
        
        IdaStar idaStar = new IdaStar(p , h);
        
        int sumOfMoves = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            p.shuffle(); 
            byte[] solution = idaStar.findSolution();
            sumOfMoves += solution.length;
            System.out.println(i+":   "+(System.currentTimeMillis()-start)+"  moves "+solution.length);
            
        }
 
        System.out.println("\nAVARAGE TIME "+((System.currentTimeMillis()-start)/100));
        System.out.println("\navarage amount of moves "+(sumOfMoves/100));
       
    }
    
}
