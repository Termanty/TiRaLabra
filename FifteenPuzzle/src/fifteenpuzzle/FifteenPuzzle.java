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
        Puzzle peli = new Puzzle(4,4);                           
        
        peli.shuffle();     
        peli.setPuzzle(esim4);            
        System.out.println(peli);
        
//        BruteForce bf = new BruteForce(peli);
        IdaStar idaStar = new IdaStar(peli);    
        
        idaStar.findPath();
//        bf.solve(15);
        
    }
    
    static byte[] esim1 = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13}; // 61 siirtoa
    static byte[] esim2 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12}; // 28 siirtoa
    static byte[] esim3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};
    static byte[] esim4 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1}; // 62 siirtoa

}
