
package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.datastructure.Node;
import fifteenpuzzle.datastructure.NodeComparator;
import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.*;
import java.util.Arrays;
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
    private MyMinHeap heap;
    private MyHashSet visited = new MyHashSet();
    private final int ROWS;
    private final int COLUMNS;
    
    long runningTime;
    
    private Node ready = new Node(new Puzzle(), 0);
    private Node u;
    
    
    public Astar(Puzzle puzzle) {
        this.starOrder = puzzle;
        this.heap = new MyMinHeap(2000000);
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }

    
    /**
     * Description of findSolution().
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */
    public byte[] findSolution() {    
        long start = System.currentTimeMillis();
        
        u = new Node(starOrder, manDist());
        heap.insert(u); 
        
        
        while (true) { 
            u = heap.removeMin();
            visited.insert(u);      
//            System.out.println(u.getCost()); 
            if (u.getPuzzle().isReady()) {
                break;
            }
            byte lastMove = u.getPuzzle().getLastMove();
            emptyUp();
            emptyDown();
            emptyLeft();
            emptyRight();
            u.getPuzzle().lastMove = lastMove;
        }
        
        System.out.println("visit "+visited.getCounter());
        
        runningTime = System.currentTimeMillis() - start;
        
        return solution();
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
        pv.setLastMove(u.getPuzzle().getLastMove());
        Node v = new Node(pv, cost);
        if (!visited.contains(v)) {
            heap.insert(v);
            v.setPath(u);
        }
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
     * @param dRow      empty places difference of row position to previous one.
     * @param dColumn   empty places difference of column position to previous one.
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

    private byte[] solution() {
        int index = 0;
        byte[] tmp = new byte[100];
        while(u.getPath() != null) {
            Node next = u.getPath();
            int row = u.getPuzzle().getEmptyRow();
            int col = u.getPuzzle().getEmptyCol();
            if (next != null) {
                tmp[index] = next.getPuzzle().getNumberInCell(row, col);
                u = next;
                index++;
            } else {
                break;
            }
        }
        byte[] path = new byte[index];
        int j = index;
        for (int i = 0; i < path.length; i++) {
            path[i] = tmp[--index]; 
        }
        return path;
    }
    
    
    
}
