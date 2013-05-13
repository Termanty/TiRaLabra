package fifteenpuzzle;

/**
 * 15-Puzzle program. 
 * This program can create random shuffle of 15-Puzzle 
 * and find minimal sequence to solve it.
 * 
 * @author Tero Mäntylä
 */


public class FifteenPuzzle {
 
    /**
     * @param args the command line arguments
     */   
    
    public static void main(String[] args) {
        Puzzle peli = new Puzzle();
        
        System.out.println(peli);
        peli.suffle(6);
        System.out.println(peli);
        BruteForce bf = new BruteForce(peli);
        bf.solve(10);
        System.out.println(peli);
        
    }

}
