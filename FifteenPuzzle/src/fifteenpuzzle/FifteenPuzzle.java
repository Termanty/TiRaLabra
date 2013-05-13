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
        peli.suffle(100);
        System.out.println(peli);
        BruteForce bf = new BruteForce(peli);
        IdaStar idaStar = new IdaStar(peli);
//        
//        System.out.println(peli.lastMove);
//        System.out.println(idaStar.manhattanDistance());
//        System.out.println(idaStar.changeMD(-1, 0));
        
        idaStar.findPath();
//        bf.solve(15);
        System.out.println(peli);
        
    }

}
