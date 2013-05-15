package fifteenpuzzle;

/**
 * Puzzle Class
 * @author Tero Mäntylä
 */

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
 * Constructors and 
 * assisting method intializePuzzle() for creating
 * puzzle which numbers are in right order.
 */
    
    public Puzzle() {
        this(4, 4);
    }

    public Puzzle(int rows, int collumns) {
        this.COLUMNS = collumns;
        this.ROWS = rows;
        this.EMPTY = (byte)(ROWS*COLUMNS);
        intializePuzzle();
    }
    
    private void intializePuzzle() {
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
  * Getters and Setters
  */

    public int getNumberOfColumns() {
        return COLUMNS;
    }

    public int getNumberOfRows() {
        return ROWS;
    }

    public byte[][] getPuzzle() {
        return puzzle;
    }

    public byte getEmpty() {
        return EMPTY;
    }

    public int getEmptyCol() {
        return emptyInCol;
    }

    public int getEmptyRow() {
        return emptyInRow;
    } 
    
    public byte getNumberInCell(int row, int col) {
        return puzzle[row][col];
    }
    
    public int[] getCordinates(int find) {
        int cordinates[] = new int[2];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (find == puzzle[row][col]) {
                    cordinates[0] = row;
                    cordinates[1] = col;
                }  
            }
        }
        return cordinates;
    }

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
 * Methods up, down, right, left "slides" number to empty hole (swaps with Empty number).
 * Method name indicates the direction from empty hole to find the sliding number.
 * If movement was done methods return TRUE. If the movement is impossible return value 
 * will be FALSE.
 */
    
    public boolean up() {
        if (emptyInRow > 0) {
            slide(-1, 0);
            return true;
        }
        return false;
    }
    
    public boolean down() {
        if (emptyInRow < ROWS - 1) {
            slide(+1, 0);
            return true;
        }
        return false;
    }
    
    public boolean right() {
        if (emptyInCol > 0) {
            slide(0, -1);
            return true;
        }
        return false;
    }
    
    public boolean left() {
        if (emptyInCol < COLUMNS - 1) {
            slide(0, +1);
            return true;
        }
        return false;
    }
    
    
 /**
 * Slide method swaps empty place and number.
 * Arguments gives the direction of the number from empty place.
 * example: dCol = 0, dRow = +1 means number under the empty place.
 */
    
    private void slide(int dRow, int dCol) {
        lastMove = puzzle[emptyInRow + dRow][emptyInCol + dCol];               
        puzzle[emptyInRow][emptyInCol] = lastMove;
        puzzle[emptyInRow + dRow][emptyInCol + dCol] = EMPTY;
        emptyInCol += dCol;
        emptyInRow += dRow;
    }  
  
    
 /**
 * Shuffle method does given amount of Random moves to shuffle the puzzle.
 * If no argument is given there will be done ether 1000 or 1001 moves.
 * This because with only even or odd amount of the moves you can't get
 * all the permutations of the shuffle.
 */     
    
    public void shuffle() {
        int amountOfRandomMoves = 1000;
        if (Math.random() < 0.5) {
            amountOfRandomMoves++;
        }
        suffle(amountOfRandomMoves);
    }
    
    public void suffle(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            moveRandomDirection();
        }   
    }
    
    private void moveRandomDirection() {
        while (true) {
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    if (up()) return;
                    break;
                case 1:
                    if (down()) return;
                    break;
                case 2:
                    if (right()) return;
                    break;
                case 3:
                    if (left()) return;
                    break;
            }   
        } 
    }
    
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
    
    
}
