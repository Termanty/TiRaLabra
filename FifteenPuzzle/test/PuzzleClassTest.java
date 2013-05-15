
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
                    assertEquals("Problem: "+rows+"*"+cols+"-Puzzle is not in right order:", puzzle[row][col], numberInCell);
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
        int[] expected = {2,3}; 
        assertArrayEquals("Method returned wrong cordinate for number 12: ",expected, p.getCordinates(12));
    }
    
    @Test
    public void getCordinates_methodReturnsRightValues() {
        assertEquals("Method returned wrong value from the cell[1][3]:", 8, p.getNumberInCell(1, 3));
    }
    
    @Test
    public void setPuzzle_methodChangesPuzzleAndEmptyRowColVariables() {
        Puzzle p1 = new Puzzle();
        byte[] test = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13};
        p1.setPuzzle(test);
        assertEquals("emptyRow has wrong value! "+test.toString(), p1.getEmptyRow(), 0);     
        assertEquals("emptyCol has wrong value! "+test.toString(), p1.getEmptyCol(), 3);
        int index = 0;
        for (int row = 0; row < p1.getNumberOfRows(); row++) {
            for (int col = 0; col < p1.getNumberOfColumns(); col++) {
                assertEquals("Puzzle has wrong value in row "+row+" col "+col, test[index] , p1.getNumberInCell(row, col));
                index++;
            }  
        }   
    }
    
    
   
    
}
