package fifteenpuzzle;

import fifteenpuzzle.searchalgorithm.*;
import fifteenpuzzle.efficiencyTests.*;
import java.util.Arrays;

/**
 * 15-Puzzle program. 
 * This program creates random shuffle of 15-Puzzle 
 * and finds minimal sequence to solve it.
 * 
 * @author Tero Mäntylä
 */

public class FifteenPuzzle {  
    
    public static void main(String[] args) {
        Puzzle p = new Puzzle(4,4);                           
         
        p.shuffle();      
        p.setPuzzle(esim5);
        
        System.out.println(p);
        
//        new HeapComparison().run();
//        new AverageRunTime().run();
//        
        IdaStar idaStar = new IdaStar(p);   
        byte[] solution = idaStar.findSolution();
        
        System.out.println("Running time was " + idaStar.getRunningTime() + " ms");
        System.out.println("Movement needed for solution: " + solution.length); 
        System.out.println("Optimal sequence of the movements to solution:");    
        System.out.println(Arrays.toString(solution)+"\n\n");
//        
//        System.out.println(p);
//        
//        Astar aStar = new Astar(p);
//        byte[] aStarSolution = aStar.findSolution();
//        
//        System.out.println("moves "+aStarSolution.length);
//        System.out.println(Arrays.toString(aStarSolution));
//        System.out.println("Running time was " + aStar.getRunningTime() + " ms");
        
        
//        System.out.println("\nPermutation at the beginning of search:\n");
//        System.out.println(p);   
//        System.out.println("running...   in the worst case this might take some time");
//        
//        // Finds optimal solusotion for permutation.

//        
//        // Prints results
//        System.out.println("");
//        System.out.println("Manhattan Distance: " + idaStar.getManhattanDistance());
//        System.out.println("Running time was " + idaStar.getRunningTime() + " ms");
//        System.out.println("");
//        System.out.println("Movement needed for solution: " + solution.length); 
//        System.out.println("Optimal sequence of the movements to solution:");    
//        System.out.println(Arrays.toString(solution)+"\n\n");
    }
    
    
    // test permutations
    static byte[] esim1 = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13}; // 61 siirtoa
    static byte[] esim2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12}; // 28 siirtoa
    static byte[] esim3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15}; 
    static byte[] esim4 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1}; // 62 siirtoa
    static byte[] esim5 = {13,10,16,14,1,9,15,8,2,7,6,11,4,12,3,5}; // 62 siirtoa ja paha
    
}
