
package fifteenpuzzle.searchalgorithm;

import fifteenpuzzle.Puzzle;
import fifteenpuzzle.datastructure.*;
import fifteenpuzzle.heuristics.*;


/**
 * Astar Class.
 * this class uses A* algorithm to find the least amount of movements 
 * to solve the puzzle. It's like Dijkstra but it uses heuristic to 
 * go towards solutions. Here is used Manhattan Distance as heuristic
 * to evaluate direction.
 * 
 * @author Tero Mäntylä
 */
public class Astar {
    
    private Puzzle starOrder;
    private Node u;
    private MyMinHeap heap;
    private MyHashSet visited = new MyHashSet();
    private final int ROWS;
    private final int COLUMNS;
    private HeuristicInterface h;
    
    long runningTime;

    
    /**
     * Description of Constructor.
     * This constructor creates instance of MyMinHeap and set values
     * for some class variables.
     *
     * @param puzzle   for this we are trying to find solution.
     */ 
    public Astar(Puzzle puzzle, HeuristicInterface h) {
        this.h = h;
        this.starOrder = puzzle;
        this.heap = new MyMinHeap(1000000);
        this.ROWS = puzzle.getNumberOfRows();
        this.COLUMNS = puzzle.getNumberOfColumns();
    }
    
    
    /**
     * Description of getRunningTime().
     * This method return time to find minimum solution.
     * 
     * @return          time to find movement sequence to solution
     */
    public long getRunningTime() {
        return runningTime;
    }
    
    
    /**
     * Description of findSolution().
     * This method is main structure of A*-algorithm. 
     * 
     * @return          byte[] array containing sequence to solve puzzle
     */
    public byte[] findSolution() {    
        long start = System.currentTimeMillis();
        
        u = new Node(starOrder, h.calculate(starOrder));
        heap.insert(u); 
        
        while (true) { 
            u = heap.removeMin();
            visited.insert(u);      
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
        
        runningTime = System.currentTimeMillis() - start;    
        return solution();
    }
    
    
    /**
     * Description of emptyUp().
     * This method moves empty place up if it's possible and
     * adds this new state of puzzle to heap.
     */
    private void emptyUp() {
        if (u.getPuzzle().up()) {
            h.setPuzzle(u.getPuzzle());
            addToHeap(u.getCost() + 1 + h.update(0));
            u.getPuzzle().down();
        }
    }
    
    
    /**
     * Description of emptyDown().
     * This method moves empty place down if it's possible and
     * adds this new state of puzzle to heap.
     */
    private void emptyDown() {
        if (u.getPuzzle().down()) {
            h.setPuzzle(u.getPuzzle());
            addToHeap(u.getCost() + 1 + h.update(1));
            u.getPuzzle().up();
        }
    }
    
    
    /**
     * Description of emptyLeft().
     * This method moves empty place left if it's possible and
     * adds this new state of puzzle to heap.
     */
    private void emptyLeft() {
        if (u.getPuzzle().left()) {
            h.setPuzzle(u.getPuzzle());
            addToHeap(u.getCost() + 1 + h.update(2));
            u.getPuzzle().right();
        }
    }
    
    
    /**
     * Description of emptyRight().
     * This method moves empty place right if it's possible and
     * adds this new state of puzzle to heap.
     */
    private void emptyRight() {
        if (u.getPuzzle().right()) {
            h.setPuzzle(u.getPuzzle());
            addToHeap(u.getCost() + 1 + h.update(3));
            u.getPuzzle().left();
        }  
    }
    
    
    /**
     * Description of addToHeap(int cost).
     * This method puts this new node to Heap if it's not already there.
     * Method also sets path-variable to know from which node was arrived 
     * to this node.
     * 
     * @param cost  = heuristics + moves done since start
     */
    private void addToHeap(int cost) {
        Puzzle pv = copy(u.getPuzzle());
        pv.setLastMove(u.getPuzzle().getLastMove());
        Node v = new Node(pv, cost);
        if (!visited.contains(v)) {
            heap.insert(v);
            v.setPath(u);
        }
    }
    
    
    /**
     * Description of copy(Puzzle old).
     * 
     * @param old   puzzle which will be copied
     */
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
                sumOfMDs += Math.abs(cordinates[0] - num / ROWS);
                sumOfMDs += Math.abs(cordinates[1] - num % COLUMNS);
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
            int rowTargetPos = p.lastMove / ROWS;
            return Math.abs(p.getEmptyRow() - dRow - rowTargetPos) - Math.abs(p.getEmptyRow() - rowTargetPos);
        } else {
            int colTargetPos = p.lastMove % COLUMNS;
            return Math.abs(p.getEmptyCol() - dColumn - colTargetPos) - Math.abs(p.getEmptyCol() - colTargetPos);
        }
    }
    
    
    /**
     * Description of solution.
     * This method return path from solution node to start node.
     * Every node knows from where it was arrived except start node. 
     * That will be decoded path-array.
     * 
     * @return   moves to solution in byte[] array.
     */
    private byte[] solution() {
        int index = 0;
        byte[] path = new byte[80];  // 80 is the maxim number of the movements for minimum solution
        while(u.getPath() != null) {
            Node next = u.getPath();
            int row = u.getPuzzle().getEmptyRow();
            int col = u.getPuzzle().getEmptyCol();
            if (next != null) {
                path[index] = next.getPuzzle().getNumberInCell(row, col);
                u = next;
                index++;
            } else {
                break;
            }
        }
        path = reverse(index, path);
        return path;
    }
    
    
    /**
     * Description of reverse(int index, byte[] tmp).
     * This method reverses content of the array. Reason for this is
     * because we want to know path from start to solution. Not from
     * solution to start.
     * 
     * @param index     amount of the moves
     * @param solution  solution in reversed order
     * @return          moves to solution in byte[] array in right order.
     */
    private byte[] reverse(int index, byte[] solution) {
        byte[] path = new byte[index];
        int j = index;
        for (int i = 0; i < path.length; i++) {
            path[i] = solution[--index]; 
        }
        return path;
    }
    
}