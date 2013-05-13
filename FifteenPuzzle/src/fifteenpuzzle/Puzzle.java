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
    private int emptyX;
    private int emptyY;
    public byte lastMove = 0;

   
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
        byte num = 1;
        puzzle = new byte[higth][length];
        for (int i = 0; i < higth; i++) {
            for (int j = 0; j < length; j++) {
                puzzle[i][j] = num++;     
            }
        }
        emptyX = length - 1;
        emptyY = higth - 1;
        puzzle[emptyY][emptyX] = 0;
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
    
    
    
    
 /**
 * Methods up, down, right, left "slides" number to empty hole (swaps with 0 number).
 * Method name indicates the direction from empty hole to find the sliding number.
 * If movement was done methods return TRUE. If the movent is impossible return value 
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
 * Slide method swaps empty place (0) and number.
 * Arguments gives the direction of the number from empty place.
 * example: dx = 0, dy = +1 means number under the empty place.
 */
    
    private void slide(int dx, int dy) {
        lastMove = puzzle[emptyY + dy][emptyX + dx];               
        puzzle[emptyY][emptyX] = lastMove;
        puzzle[emptyY + dy][emptyX + dx] = 0;
        emptyX += dx;
        emptyY += dy;
    }  
  
    
 /**
 * Suffle method does given amount of Random moves to suffle the puzzle.
 * If no argument is given there will be done ether 1000 or 1001 moves.
 * This because with only even or odd amount of the moves you can't get
 * all the permutations of the suffle.
 */     
    
    public void suffle() {
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
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < higth; j++) {
                if (i == length - 1 && j == higth - 1) {
                    if (puzzle[i][j] != 0) {
                        return false;
                    }
                } else if (puzzle[i][j] != num) {
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
                if (puzzle[i][j] < 10) sb.append(" ");
                sb.append(puzzle[i][j]); 
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
}
