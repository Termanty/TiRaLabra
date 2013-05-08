package fifteenpuzzle;

/**
 * 15-Puzzle solving algorithm
 * 
 * @author Tero Mäntylä
 */
public class FifteenPuzzle {
 
    /**
     * @param args the command line arguments
     */
    
    static byte[][] puzzle = {{ 1,  2,  3,  4},
                              { 5,  6,  7,  8},
                              { 9, 10, 11, 12},
                              {13, 14, 15,  0}};
    
    
    public static void main(String[] args) {
        Puzzle peli = new Puzzle();
        byte[][] p = peli.giveRandom();
        
        tulosta(p);       
    }
    
    private static void tulosta(byte[][] p) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                System.out.format("%3d", p[i][j]); 
            }
            System.out.println("");
        }
    }
    
}
