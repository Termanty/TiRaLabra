
package fifteenpuzzle.efficiencyTests;

import fifteenpuzzle.searchalgorithm.IdaStar;
import fifteenpuzzle.Puzzle;
import java.util.Arrays;

/**
 * AverageRunTime
 * 
 * @author termanty
 */
public class AverageRunTime {

    public void run() {
        Puzzle p = new Puzzle(4,4);                           
         
        p.shuffle(); 
        System.out.println("\nAVERAGE RUNNING TIME TEST:\n");  
        System.out.println("running...   this WILL take some time\n");
        
        IdaStar idaStar = new IdaStar(p);
        
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
