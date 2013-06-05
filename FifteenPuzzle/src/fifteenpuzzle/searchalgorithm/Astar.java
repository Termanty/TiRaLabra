
package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.datastructure.Node;
import fifteenpuzzle.datastructure.NodeComparator;
import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.*;
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
    
    private Puzzle starOrder;
    private PriorityQueue<Node> heap;
    private MyHashSet visited = new MyHashSet();
    private final int ROWS;
    private final int COLUMNS;
    
    private byte[] optimalSolution;
    
    long runningTime;
    
    private Node ready = new Node(new Puzzle(), 0);
    
    
    
//        // G tutkittava verkko, a lähtösolmu, b kohdesolmu ja w kaaripainot kertova funktio
    
    public Astar(Puzzle puzzle) {
        this.starOrder = puzzle;
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
    
    
    private Node u;
    
    /**
     * Description of findSolution().
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */
    public byte[] findSolution() {    
        long start = System.currentTimeMillis();
        
        u = new Node(starOrder, manDist());
        heap.add(u); 
        
        
        while (true) { 
            u = heap.poll();
            visited.insert(u);      
//            System.out.println(u.getCost());    
            if (u.getPuzzle().isReady()) {
                break;
            }  
            emptyUp();
            emptyDown();
            emptyLeft();
            emptyRight();     
        }
        
        runningTime = System.currentTimeMillis() - start;
        System.out.println(u.getPuzzle().toString());
        return optimalSolution;
    }
    
    
    private void emptyUp() {
        if (u.getPuzzle().up()) {
            addToHeap(u.getCost() + 1 + changeMD(u.getPuzzle(), -1, 0));
            u.getPuzzle().down();
        }
    }
    
    
    private void emptyDown() {
        if (u.getPuzzle().down()) {
            addToHeap(u.getCost() + 1 + changeMD(u.getPuzzle(), +1, 0));
            u.getPuzzle().up();
        }
    }
    
    
    private void emptyLeft() {
        if (u.getPuzzle().left()) {
            addToHeap(u.getCost() + 1 + changeMD(u.getPuzzle(), 0, -1));
            u.getPuzzle().right();
        }
    }
    
    
    private void emptyRight() {
        if (u.getPuzzle().right()) {
            addToHeap(u.getCost() + 1 + changeMD(u.getPuzzle(), 0, +1));
            u.getPuzzle().left();
        }  
    }
    
    
    private void addToHeap(int cost) {
        Puzzle pv = copy(u.getPuzzle());
        pv.setLastMove(u.getPuzzle().getEmpty());
        Node v = new Node(pv, cost);
        if (!visited.contains(v)) {
            heap.add(v);
        }
        v.setPath(u);
    }
    
    
    private Puzzle copy(Puzzle old) {
        Puzzle copy = new Puzzle();
        copy.setPuzzle(old.getPuzzle());
        return copy;
    }
    
    
 
    
    
    
    
    
    
    /**
     * Description of manDist().
     * method calculates sum of Manhattan distances of the every
     * number in the puzzle to their own place.
     * 
     * @return          sum of Manhattan distances
     */
    private int manDist() {
        int sumOfMDs = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                int num = starOrder.getNumberInCell(row, column);
                if (num == starOrder.getEmpty()) {
                    continue;
                }
                int[] cordinates = starOrder.getCordinates(num);
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
    private int changeMD(Puzzle p, int dRow, int dColumn) {
        if (dColumn == 0) {
            int rowTargetPos = (p.lastMove - 1) / ROWS;
            return Math.abs(p.getEmptyRow() - dRow - rowTargetPos) - Math.abs(p.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = (p.lastMove - 1) % COLUMNS;
            return Math.abs(p.getEmptyCol() - dColumn - colTargetPos) - Math.abs(p.getEmptyCol() - colTargetPos);
        }
    }
    
    
    
}
