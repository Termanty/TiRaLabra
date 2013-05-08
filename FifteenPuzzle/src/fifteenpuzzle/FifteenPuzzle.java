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
        peli.suffle();
        System.out.println(peli);
               
    }

}
