package fifteenpuzzle;

/**
 * Puzzle Class.
 * This is the core of the hole program. This datasructure is responsible of the
 * all needed functionality of the fifteen puzzle. It knows the current state of
 * puzzle, it can handle all the movements and check if they can be done or not.
 * This class also remember most recent move and can randomly shuffle puzzle. 
 * Position of the empty place is remembered all the time to make class work faster
 * and to keep code more simple.
 * 
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
     * Description of constructor.
     * This is defaul size of the puzzle. In future size of the puzzle can
     * different, but in this work we will stayt in 4*4 puzzle.
     */
    public Puzzle() {
        this(4, 4);
    }

    
    /**
     * Description of constructor.
     * Although default size 4*4 is used in this work other size puzzles have
     * been made possible in this constructor, which gives values final variables.
     * Constructor puts values for all the cells in using initializePuzzle-method . 
     * Numbers are 0 - 15. Number 15 means empty cell or place, which 
     * traditionaly is in down-right corner at solution state of the puzzle.
     *
     * @param rows    number of rows in puzzle
     * @param columns number of columns in puzzle
     */
    public Puzzle(int rows, int columns) {
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.EMPTY = (byte)(ROWS*COLUMNS - 1);
        initializePuzzle();
    }
    
    
    /**
     * Description of initializePuzzle().
     * Method creates 4*4 byte[][]-array to save the state of puzzle.
     * At the begging numbers are put to solution state, where numbers run
     * from 0 to 15 from up-left corner row by row to down-right corner.
     * lastMove variable gets value -1, because now movement is done yet.
     */   
    private void initializePuzzle() {
        byte numberInCell = 0;
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
     * Description of getNumberOfColumns().
     * This method returns number of the collumns in the puzzle.
     * At default it will be 4, but other size is also possible.
     *
    * @return           number of columns in puzzle
     */
    public int getNumberOfColumns() {
        return COLUMNS;
    }
    
    
    /**
     * Description of getNumberOfRows().
     * This mehtod return number of the rows in the puzzle.
     * Ath default it will be 4, but other size is also possible.
     *
     * @return          number of rows in puzzle
     */
    public int getNumberOfRows() {
        return ROWS;
    }
    

    /**
     * Description of getPuzzle().
     * This mehtod returns byte[][]-array, which contains current
     * state of the puzzle.
     *
     * @return          gives puzzle in byte[][] form
     */
    public byte[][] getPuzzle() {
        return puzzle;
    }
    
    
    /**
     * Description of getEmpty().
     * This method return numerical value for empty.
     * At default 4*4 puzzle it is 15, but it can be something else too
     * depending size of the puzzle.
     *
    * @return           gives number which present empty place in puzzle
     */
    public byte getEmpty() {
        return EMPTY;
    }
    
    
    /**
     * Description of getEmptyCol().
     * This method return column where empty place is situated.
     *
     * @return          gives vertical position of empty place in puzzle 
     */
    public int getEmptyCol() {
        return emptyInCol;
    }
    
    
    /**
     * Description of getEmptyRow().
     * This method return row where empty place is situated.
     * 
     * @return          gives horizontal position of empty place in puzzle 
     */
    public int getEmptyRow() {
        return emptyInRow;
    }
    
    
    /**
     * Description of getLastMove().
     * This method return most recent move. Lets say that number 5 was moved done
     * to empty place under it. So then lastMove-variable have value 5.
     *
     * @return          gives number which has been most recently moved
     */
    public byte getLastMove() {
        return lastMove;
    }
    
    
    /**
     * Description of getNumberInCell().
     * This method return number in certain cell.
     * 
     * @param row       horizontal position of the cell
     * @param col       verticl position of the cell
     * @return          gives number which is positioned to asked row and column
     */ 
    public byte getNumberInCell(int row, int col) {
        return puzzle[row][col];
    }
    
    
    /**
     * Description of getCordinates().
     * This method return array with two values. Firt number tells in which
     * row number is and second number tells column.
     *
     * @param num       number which position in puzzle is searched.
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
     * Description of setPuzzle(byte[] newSequence).
     * This method puts puzzle to state given by byte[]-array. 
     * Mainly this method is used in testing purposes, where we want to
     * run searcing algorithms with specific permutation of the puzzle.
     * Attention! because newSequence contains presentation of puzzle in 
     * form where numbers run from 1 to 16. When this numbers are added
     * to  puzzle[][]-array one is subtracted. Same with check of the empty.
     *
     * @param newSequence       puzzle permutation in byte[] form
     */
    public void setPuzzle(byte[] newSequence) {
        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                puzzle[row][col] = (byte)(newSequence[index] - 1);
                if (newSequence[index] - 1 == EMPTY) {
                    emptyInCol = col;
                    emptyInRow = row;
                }
                index++;
            }
        }  
    }
    
    
    /**
     * Description of setPuzzle(byte[][] newSequence).
     * This method puts puzzle to state given by byte[][]-array.
     * Method is used in A*-algorithm
     *
     * @param newSequence       puzzle permutation in byte[][] form
     */
    public void setPuzzle(byte[][] newSequence) {
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
     * Description of setCell(int row, int col).
     * This method changes value in particular cell. For normally there is no
     * need for this. Use of it creates just a mesh. This is only for
     * certain calculation in LinearConflict Class, because I did not find any
     * more sensible way to do it. So please don't use this method.
     *
     * @param row       row in the puzzle
     * @param col       column in the puzzle
     */
    public void setCell(int row, int col, byte value) {
        puzzle[row][col] = value;
    }
    
    
    /**
     * Description of setLastMove(byte lastMove).
     * This method set new value for lastMove-variable. Normally there is
     * no need to it, because setting this value is automatically done when
     * movement is done. But A*-algorithm needs it. You don't!
     *
     * @param lastMove       number which has most recent move.
     */
    public void setLastMove(byte lastMove) {
        this.lastMove = lastMove;
    }


    /**
     * Description of up().
     * This method 'slides' empty place up. And moves number above it down.
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
     * Description of down().
     * This method 'slides' empty place donwn. And number under it up.
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
     * Description of left().
     * This method 'slides' empty place left. And number left from it to right.
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
     * Description of right().
     * This method 'slides' empty place right. And number right from it to left.
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
     * This method swaps empty place and number. 
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
     * This method does random moves to shuffle the puzzle. 
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
     * This method does given amount of random moves to shuffle the puzzle. 
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
     * This method checks if puzzle is solved. Puzzle is solved when nubers
     * run 0 to 15 from up-right corner row by row to down-left corner.
     * 
     * @return      true if puzzle is solved otherwise false
     */
    public boolean isReady() {
        byte numberInCell = 0;
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
     * This method returns string presentation of the state of the puzzle.
     * Because in fifteen puzzle numbers are from 1 to 15 and empty. And
     * puzzle uses 0 to 14 and empty (15). One is added every number in string
     * presentation and empty gets ".." sign.
     * 
     * @return      string presentation of the puzzle
     */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                sb.append(" ");
                if (puzzle[row][col] + 1 < 10) {
                    sb.append(" ");
                }
                if (puzzle[row][col] == EMPTY) {
                    sb.append("..");
                } else {
                    sb.append(puzzle[row][col] + 1);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    } 
    
    
    /**
     * Description of hashCode(). 
     * This method return hash of byte[][]-array.
     * It is needed in MyHashSet Class which is needed in A*-algorithm.
     * 
     * @return      hash of byte[][] arrays
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.puzzle);
    }
    
    
    /**
     * Description of equals(Object obj).
     * This method compares two puzzles and finds out if numbers in this
     * puzzles are in same order.
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
