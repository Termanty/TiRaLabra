
package fifteenpuzzle;

import java.util.HashSet;
import java.util.PriorityQueue;


/**
 * Astar Class
 * Class uses A* algorithm to find the least amount of movements to solve a puzzle.
 * 
 * @author Tero Mäntylä
 */

public class Astar {
    
//        Astar(G,w,a,b)
    
    private Puzzle puzzle;
    private PriorityQueue<Node> heap;
    private final int ROWS;
    private final int COLUMNS;
    
    private byte[] optimalSolution;
    
    long runningTime;
    
    
    
//        // G tutkittava verkko, a lähtösolmu, b kohdesolmu ja w kaaripainot kertova funktio
    
    public Astar(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.heap = new PriorityQueue(1000000, new NodeComparator());
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }
    
    
    // INTIATION no need to do. This has to do in real time.
//        1 for kaikille solmuille v joukossa V
//        2 alkuun[v] = 1 
    //          tehtyjen siirtojen määrä
//        3 loppuun[v] = arvioi suora etäisyys v:stä  b:hen
    //          manhattan etäisyys perille
//        4 polku[v] = NIL
    //       
//        5 alkuun[a] = 0
    
    
    //  SEARCH
//        6 S = tyhjä joukko 
//        7 while ( solmu b ei ole vielä joukossa S )
//        8 valitse solmu u 2 V \ S, jolle alkuun[v]+loppuun[v] on pienin
//        9 S = S [ {u}
//        10 for jokaiselle solmulle v 2 Adj[u] // kaikille u:n vierussolmuille v
//        11 if alkuun[v] > alkuun[u] + w(u,v)
//        12 alkuun[v] = alkuun[
    
    
    
    /**
     * Description of findSolution().
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */
    
    public byte[] findSolution() {
        HashSet<Node> visited = new HashSet();
        
        int md = manhattanDistance();
        
        
        long timeAtStar = System.currentTimeMillis();
        
        heap.add(new Node(puzzle, md));
        
        while (md > 0) { 
            
        }
        
        runningTime = System.currentTimeMillis() - timeAtStar;
        
        return optimalSolution;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Description of manhattanDistance().
     * method calculates sum of every numbers distance to their own place.
     * 
     * @return          Manhattan distance
     */
    
    private int manhattanDistance() {
        int sumOfMDs = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int num = puzzle.getNumberInCell(row, column);
                if (num == puzzle.getEmpty()) {
                    continue;
                }
                int[] cordinates = puzzle.getCordinates(num);
                sumOfMDs += Math.abs(cordinates[0] - (num - 1) / ROWS);
                sumOfMDs += Math.abs(cordinates[1] - (num - 1) % COLUMNS);
            }
        }
        return sumOfMDs;
    }
    
    
    /**
     * Description of changeMD(int dRow, int dColumn).
     * method calculates change in  Manhattan distance .
     * 
     * @param dRow
     * @param dColumn 
     * @return          change
     */
    
    private int changeMD(int dRow, int dColumn) {
        if (dColumn == 0) {
            int rowTargetPos = (puzzle.lastMove - 1) / ROWS;
            return Math.abs(puzzle.getEmptyRow() - dRow - rowTargetPos) - Math.abs(puzzle.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = (puzzle.lastMove - 1) % COLUMNS;
            return Math.abs(puzzle.getEmptyCol() - dColumn - colTargetPos) - Math.abs(puzzle.getEmptyCol() - colTargetPos);
        }
    }
    
    
    
}
