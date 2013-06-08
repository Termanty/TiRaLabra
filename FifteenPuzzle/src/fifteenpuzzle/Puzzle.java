package fifteenpuzzle;

/**
 * Puzzle Class
 * @author Tero Mäntylä
 */

import java.util.Arrays;
import java.util.Random;


public class Puzzle {
    
    private byte[][] puzzle;
    private final int ROWS;
    private final int COLUMNS;
    
    private final byte EMPTY;
    private int emptyInRow;
    private int emptyInCol;
    
    public byte lastMove;

    
    /**
     * Description of constructor
     * This constructor creates 4*4 puzzle
     */
    public Puzzle() {
        this(4, 4);
    }

    
    /**
     * Description of constructor
     *
     * @param rows number of rows in puzzle
     * @param columns number of columns in puzzle
     */
    public Puzzle(int rows, int columns) {
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.EMPTY = (byte)(ROWS*COLUMNS);
        initializePuzzle();
    }
    
    
    /**
     * Description of initializePuzzle() 
     * Method creates table with numbers 1 to size of puzzle.
     */   
    private void initializePuzzle() {
        byte numberInCell = 1;
        puzzle = new byte[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                puzzle[row][col] = numberInCell++;     
            }
        }
        lastMove = -1;
        emptyInCol = COLUMNS - 1;
        emptyInRow = ROWS - 1;
    }
    
    
    /**
     * Description of getNumberOfColumns()
     *
    * @return           number of columns in puzzle
     */
    public int getNumberOfColumns() {
        return COLUMNS;
    }
    
    
    /**
     * Description of getNumberOfRows()
     *
     * @return          number of rows in puzzle
     */
    public int getNumberOfRows() {
        return ROWS;
    }
    

    /**
     * Description of getPuzzle()
     *
     * @return          gives puzzle in byte[][] form
     */
    public byte[][] getPuzzle() {
        return puzzle;
    }
    
    
    /**
     * Description of getEmpty()
     *
    * @return           gives number which present empty place in puzzle
     */
    public byte getEmpty() {
        return EMPTY;
    }
    
    
    /**
     * Description of getEmptyCol()
     *
     * @return          gives vertical position of empty place in puzzle 
     */
    public int getEmptyCol() {
        return emptyInCol;
    }
    
    
    /**
     * Description of getEmptyRow()
     *
     * @return          gives horizontal position of empty place in puzzle 
     */
    public int getEmptyRow() {
        return emptyInRow;
    }
    
    
    /**
     * Description of getLastMove()
     *
     * @return          gives number which has been most recently moved
     */
    public byte getLastMove() {
        return lastMove;
    }
    
    
    /**
     * Description of getNumberInCell()
     * 
     * @param row       
     * @param col
     * @return          gives number which is positioned to asked row and column
     */ 
    public byte getNumberInCell(int row, int col) {
        return puzzle[row][col];
    }
    
    
    /**
     * Description of getCordinates()
     *
     * @param num       
     * @return          gives array which contains row and column cordinate of search number
     */ 
    public int[] getCordinates(int num) {
        int cordinates[] = new int[2];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (num == puzzle[row][col]) {
                    cordinates[0] = row;
                    cordinates[1] = col;
                }  
            }
        }
        return cordinates;
    }
    
    
    /**
     * Description of setPuzzle(byte[] newSequence)
     * Initializes puzzle with given order
     *
     * @param newSequence       puzzle permutation in byte[] form
     */
    public void setPuzzle(byte[] newSequence) {
        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                puzzle[row][col] = newSequence[index];
                if (newSequence[index] == EMPTY) {
                    emptyInCol = col;
                    emptyInRow = row;
                }
                index++;
            }
        }  
    }
    
    
    /**
     * Description of setPuzzle(byte[][] newSequence)
     * Initializes puzzle with given order
     *
     * @param newSequence       puzzle permutation in byte[][] form
     */
    public void setPuzzle(byte[][] newSequence) {
        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                puzzle[row][col] = newSequence[row][col];
                if (newSequence[row][col] == EMPTY) {
                    emptyInCol = col;
                    emptyInRow = row;
                }
            }
        }  
    }
    
    
    /**
     * Description of setCell(int row, int col)
     * This is for Linear Conflict calculation.
     *
     * @param row       row in the puzzle
     * @param col       column in the puzzle
     */
    public void setCell(int row, int col, byte value) {
        puzzle[row][col] = value;
    }
    
    
    /**
     * Description of setLastMove(byte lastMove)
     *
     * @param lastMove       number which has most recent move.
     */
    public void setLastMove(byte lastMove) {
        this.lastMove = lastMove;
    }


    /**
     * Description of up()
     * method 'slides' number above empty place down.
     *
     * @return              true if movement was done otherwise false
     */   
    public boolean up() {
        if (emptyInRow > 0) {
            slide(-1, 0);
            return true;
        }
        return false;
    }
    
    
    /**
     * Description of down()
     * method 'slides' number above empty place up.
     *
     * @return              true if movement was done otherwise false
     */
    
    public boolean down() {
        if (emptyInRow < ROWS - 1) {
            slide(+1, 0);
            return true;
        }
        return false;
    }
    
    
    /**
     * Description of left()
     * method 'slides' number above empty place right.
     *
     * @return              true if movement was done otherwise false
     */ 
    public boolean left() {
        if (emptyInCol > 0) {
            slide(0, -1);
            return true;
        }
        return false;
    }
    
    
    /**
     * Description of right()
     * method 'slides' number above empty place left.
     *
     * @return              true if movement was done otherwise false
     */   
    public boolean right() {
        if (emptyInCol < COLUMNS - 1) {
            slide(0, +1);
            return true;
        }
        return false;
    }
    
    
    /**
     * Description of slide(int dRow, int dCol).
     * Slide method swaps empty place and number. 
     * Arguments gives the direction of the number from empty place.
     * example: dCol = 0, dRow = +1 means number under the empty place.
     *
     * @param dRow
     * @param dCol
     */  
    private void slide(int dRow, int dCol) {
        lastMove = puzzle[emptyInRow + dRow][emptyInCol + dCol];               
        puzzle[emptyInRow][emptyInCol] = lastMove;
        puzzle[emptyInRow + dRow][emptyInCol + dCol] = EMPTY;
        emptyInRow += dRow;
        emptyInCol += dCol;    
    }
  
    
    /**
     * Description of shuffle().
     * Shuffle method does random moves to shuffle the puzzle. 
     * It will be do either 1000 or 1001 moves. 
     * This because with only even or odd amount of the moves you can't get
     * all the permutations of the shuffle. 
     */       
    public void shuffle() {
        int amountOfRandomMoves = 1000;
        if (Math.random() < 0.5) {
            amountOfRandomMoves++;
        }
        shuffle(amountOfRandomMoves);
    }
    
    
    /**
     * Description of shuffle(int numberOfMoves).
     * Shuffle method does given amount of random moves to shuffle the puzzle. 
     * 
     * @param numberOfMoves
     */   
    public void shuffle(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            moveRandomDirection();
        }   
    }
    
    
    /**
     * Description of moveRandomDirection().
     * This assisting method try as long as accepted 
     * random move is found and done
     */   
    private void moveRandomDirection() {
        while (true) {
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    if (up()) {
                        return;
                    }
                    break;
                case 1:
                    if (down()) {
                        return;
                    }
                    break;
                case 2:
                    if (left()) {
                        return;
                    }
                    break;
                case 3:
                    if (right()) {
                        return;
                    }
                    break;
            }
        }
    }
    
    
    /**
     * Description of isReady().  
     * method checks if puzzle is solved.
     * 
     * @return      true if puzzle is solved otherwise false
     */
    public boolean isReady() {
        byte numberInCell = 1;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (puzzle[row][col] != numberInCell) {
                    return false;
                }
                numberInCell++;
            }
        }
        return true;
    }
    
    
    /**
     * Description of toString().  
     * 
     * @return      string presentation of the puzzle
     */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                sb.append(" ");
                if (puzzle[row][col] < 10) {
                    sb.append(" ");
                }
                if (puzzle[row][col] == EMPTY) {
                    sb.append("..");
                } else {
                    sb.append(puzzle[row][col]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    } 
    
    
    /**
     * Description of hashCode().  
     * 
     * @return      hash of byte[][] arrays
     */
    @Override
    public int hashCode() {
            return Arrays.deepHashCode(this.puzzle);
    }
    
    
    /**
     * Description of equals(Object obj).  
     * 
     * @param obj   compared puzzle      
     * @return      hash of byte[][] arrays
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Puzzle other = (Puzzle) obj;
        if (!Arrays.deepEquals(this.puzzle, other.puzzle)) {
            return false;
        }
        return true;
    }
    
    
}
