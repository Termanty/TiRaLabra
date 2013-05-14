package fifteenpuzzle;

/**
 * Puzzle Class
 * @author Tero Mäntylä
 */

import java.util.Random;


public class Puzzle {
    
    private byte[][] puzzle;
    private int length;
    private int higth;
    private byte empty;
    private int emptyX;
    private int emptyY;
    public byte lastMove;

   
 /**
 * Constructors and assisting method intializePuzzle() for creating
 * puzzle which numbers are in right order.
 */
    
    public Puzzle() {
        this.length = 4;
        this.higth = 4;
        intializePuzzle();
    }

    public Puzzle(int length, int higth) {
        this.length = length;
        this.higth = higth;
        intializePuzzle();
    }
    
    private void intializePuzzle() {
        byte num = 0;
        puzzle = new byte[higth][length];
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                puzzle[i][j] = ++num;     
            }
        }
        empty = num;
        lastMove = num;
        emptyX = length - 1;
        emptyY = higth - 1;
    }

    public int getLength() {
        return length;
    }

    public int getHigth() {
        return higth;
    }

    public byte[][] getPuzzle() {
        return puzzle;
    }

    public byte getEmpty() {
        return empty;
    }

    public int getEmptyX() {
        return emptyX;
    }

    public int getEmptyY() {
        return emptyY;
    } 
    
    public byte getNumber(int i, int j) {
        return puzzle[i][j];
    }
    
    public int[] getCoordinates(int find) {
        int coordinates[] = new int[2];
        int num = 1;
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                if (find == puzzle[i][j]) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                }  
            }
        }
        return coordinates;
    }

    public void setPuzzle(byte[] order) {
        int n = 0;
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                puzzle[i][j] = order[n];
                if (order[n] == empty) {
                    emptyX = j;
                    emptyY = i;
                }
                n++;
            }
        }  
    }
    
    
    
    
    
    
 /**
 * Methods up, down, right, left "slides" number to empty hole (swaps with 0 number).
 * Method name indicates the direction from empty hole to find the sliding number.
 * If movement was done methods return TRUE. If the movement is impossible return value 
 * will be FALSE.
 */
    
    public boolean up() {
        if (emptyY > 0) {
            slide(0, -1);
            return true;
        }
        return false;
    }
    
    public boolean down() {
        if (emptyY < higth - 1) {
            slide(0, +1);
            return true;
        }
        return false;
    }
    
    public boolean right() {
        if (emptyX > 0) {
            slide(-1, 0);
            return true;
        }
        return false;
    }
    
    public boolean left() {
        if (emptyX < length - 1) {
            slide(+1, 0);
            return true;
        }
        return false;
    }
    
    
 /**
 * Slide method swaps empty place and number.
 * Arguments gives the direction of the number from empty place.
 * example: dx = 0, dy = +1 means number under the empty place.
 */
    
    private void slide(int dx, int dy) {
        lastMove = puzzle[emptyY + dy][emptyX + dx];               
        puzzle[emptyY][emptyX] = lastMove;
        puzzle[emptyY + dy][emptyX + dx] = empty;
        emptyX += dx;
        emptyY += dy;
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
        byte num = 1;
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                if (puzzle[i][j] != num) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                sb.append(" ");
                if (puzzle[i][j] < 10) {
                    sb.append(" ");
                }
                if (puzzle[i][j] == empty) {
                    sb.append("..");
                } else {
                    sb.append(puzzle[i][j]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
}
