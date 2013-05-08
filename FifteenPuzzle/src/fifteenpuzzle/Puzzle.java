package fifteenpuzzle;

/**
 * Puzzle Class
 * @author Tero Mäntylä
 */

import java.util.Random;

public class Puzzle {
    private byte[][] puzzle = {{ 1,  2,  3,  4},
                               { 5,  6,  7,  8},
                               { 9, 10, 11, 12},
                               {13, 14, 15,  0}};
    private int length = 4;
    private int higth = 4;   

    
    public void suffle() {
        int[] locEmptyBlock = {3, 3};
        int amountOfRandomMoves = 1000;
        if (Math.random() < 0.5) {
            amountOfRandomMoves++;
        }
        for (int i = 0; i < amountOfRandomMoves; i++) {
            locEmptyBlock = moveOnePlateRandomDirection(locEmptyBlock[0], locEmptyBlock[1]);
        }
    }
    
    private int[] moveOnePlateRandomDirection(int x, int y) {
        int moveX;
        int moveY;
        
        do {
            moveX = 0;
            moveY = 0;
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    moveX = 1;
                    break;
                case 1:
                    moveX = -1;
                    break;
                case 2:
                    moveY = 1;
                    break;
                case 3:
                    moveY = -1;
                    break;
            }   
        } while (!(moveX + x >= 0 && moveX + x <= 3 && moveY + y >= 0 && moveY + y <= 3));
        
        puzzle[y][x] = puzzle[moveY + y][moveX + x];
        puzzle[moveY + y][moveX + x] = 0;
        
        return new int[]{moveX + x, moveY + y};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                sb.append(" ");
                if (puzzle[i][j] < 10) sb.append(" ");
                sb.append(puzzle[i][j]); 
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
}
