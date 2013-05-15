
import fifteenpuzzle.Puzzle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Puzzle class
 * 
 * @author Tero Mäntylä
 */

public class PuzzleClassTest {
    private Puzzle p;
    private byte[] test1 = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13}; // 61 siirtoa
    private byte[] test2 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1}; // 62 siirtoa
    
    public PuzzleClassTest() {
        p = new Puzzle();
    }
    
    @Test
    public void konstruktorCreatesPuzzle() {
        assertNotNull("byte[][] array is null:", p.getPuzzle());
    }
    
    @Test
    public void konstruktorIntializesVariablesWithRightValues() {
        int testWithRows = 4;
        int testWithColums = 4;
        Puzzle p1 = p;   // first testing constructor without parameters and then testing with random parameters.
        for (int i = 0; i < 10; i++) {            
            int rows = p1.getNumberOfRows();
            int columns = p1.getNumberOfColumns();
            int empty = p1.getEmpty();
            int emptyRow = p1.getEmptyRow();
            int emptyCol = p1.getEmptyCol();
            assertEquals("Class variable ROW has wrong value:", testWithRows, rows);
            assertEquals("Class variable COL has wrong value:", testWithColums, columns);
            assertEquals("Class variable empty has wrong value:", rows * columns, empty);
            assertEquals("Class variable emptyRow has wrong value:", rows - 1, emptyRow);
            assertEquals("Class variable emptyCol has wrong value:", columns - 1, emptyCol);
            testWithRows = (int)(Math.random()*10+2);
            testWithColums = (int)(Math.random()*10+2);
            p1 = new Puzzle(testWithRows, testWithColums);
        }
    }
    
    @Test
    public void CreatedPuzzleIsInRigthOrder() { 
        byte[][] puzzle = p.getPuzzle();   // first testing 4*4 puzzle then random size puzzle.
        for (int i = 0; i < 10; i++) {
            int rows = puzzle.length;
            int cols = puzzle[0].length;
            byte numberInCell = 1;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    assertEquals("Problem: "+rows+"*"+cols+"-Puzzle is not in right order:", numberInCell, puzzle[row][col]);
                    numberInCell++;
                }
            }
            int testWithRows = (int)(Math.random()*10+2);
            int testWithColums = (int)(Math.random()*10+2);
            puzzle = new Puzzle(testWithRows, testWithColums).getPuzzle();
        }
    }
    
    @Test
    public void getNumberInCell_methodReturnsRightValue() {
        assertArrayEquals("Method returned wrong cordinate for number 12: ", new int[]{2,3}, p.getCordinates(12));
    }
    
    @Test
    public void getCordinates_methodReturnsRightValues() {
        assertEquals("Method returned wrong value from the cell[1][3]:", 8, p.getNumberInCell(1, 3));
    }
    
    @Test
    public void setPuzzle_methodChangesPuzzleAndEmptyRowColVariables() {
        Puzzle p1 = testCase(test1); 
        assertEquals("emptyRow has wrong value! "+test1.toString(), p1.getEmptyRow(), 0);     
        assertEquals("emptyCol has wrong value! "+test1.toString(), p1.getEmptyCol(), 3);
        int index = 0;
        for (int row = 0; row < p1.getNumberOfRows(); row++) {
            for (int col = 0; col < p1.getNumberOfColumns(); col++) {
                assertEquals("Puzzle has wrong value in row "+row+" col "+col, test1[index], p1.getNumberInCell(row, col));
                index++;
            }  
        }   
    }
    
    @Test
    public void up_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
       Puzzle p1 = testCase(test2);
       assertTrue("Return false but shoud return true", p1.up());
       assertEquals("emptyRow has wrong value! "+test2.toString(), 1, p1.getEmptyRow());
       assertEquals("emptyCol has wrong value! "+test2.toString(), 2, p1.getEmptyCol());
       assertEquals("Puzzle has wrong value in row 2 col 3", 6 , p1.getNumberInCell(2, 2));
    }
    
    @Test
    public void down_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
       Puzzle p1 = testCase(test2);
       assertTrue("Return false but shoud return true", p1.down());
       assertEquals("emptyRow has wrong value! "+test2.toString(), 3, p1.getEmptyRow());
       assertEquals("emptyCol has wrong value! "+test2.toString(), 2, p1.getEmptyCol());
       assertEquals("Puzzle has wrong value in row 2 col 3", 9 , p1.getNumberInCell(2, 2));
    }
    
    @Test
    public void right_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
       Puzzle p1 = testCase(test2);
       assertTrue("Return false but shoud return true", p1.right());
       assertEquals("emptyRow has wrong value! "+test2.toString(), 2, p1.getEmptyRow());
       assertEquals("emptyCol has wrong value! "+test2.toString(), 1, p1.getEmptyCol());
       assertEquals("Puzzle has wrong value in row 2 col 3", 5 , p1.getNumberInCell(2, 2));  
    }
    
    @Test
    public void left_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
       Puzzle p1 = testCase(test2);
       assertTrue("Return false but shoud return true", p1.left());
       assertEquals("emptyRow has wrong value! "+test2.toString(), 2, p1.getEmptyRow());
       assertEquals("emptyCol has wrong value! "+test2.toString(), 3, p1.getEmptyCol());
       assertEquals("Puzzle has wrong value in row 2 col 3", 4 , p1.getNumberInCell(2, 2));  
    }
    
    private Puzzle testCase(byte[] test) {
        Puzzle p1 = new Puzzle();
        p1.setPuzzle(test);
        return p1;
    }
    
}
