
import fifteenpuzzle.Puzzle;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Puzzle class.
 * This class run tests for Puzzle class to make sure that
 * all methods works properly.
 * 
 * @author Tero Mäntylä
 */

public class PuzzleClassTest {  
    
    private Puzzle p;
    private byte[] test1 = {14,4,6,16,8,11,15,9,12,3,5,1,2,10,7,13}; 
    private byte[] test2 = {14,15,11,10,2,3,6,13,12,5,16,4,7,8,9,1};
    private byte[] test3 = {5,1,3,4,9,2,7,8,16,6,10,12,13,14,11,15};
    private byte[] test4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private byte[] test5 = {5,2,16,3,7,1,15,4,9,6,10,11,13,8,14,12};
    
    /**
     * Description of constructor.
     * this constructor creates instance of Puzzle class.
     */
    public PuzzleClassTest() {
        p = new Puzzle();
    }
    
    /**
     * Description of constructorCreatesPuzzle().
     * this method tests that puzzle-variable is not null.
     */
    @Test
    public void constructorCreatesPuzzle() {
        assertNotNull("byte[][] array is null:", p.getPuzzle());
    }
    
    /**
     * Description of constructorIntializesVariablesWithRightValues().
     * this test method makes sure that most of the class variables 
     * gets right values.
     */
    @Test
    public void constructorIntializesVariablesWithRightValues() {
        int testWithRows = 4;
        int testWithColums = 4;
        Puzzle p1 = p;
        for (int i = 0; i < 10; i++) {            
            int rows = p1.getNumberOfRows();
            int columns = p1.getNumberOfColumns();
            int empty = p1.getEmpty();
            int emptyRow = p1.getEmptyRow();
            int emptyCol = p1.getEmptyCol();
            assertEquals("Class variable ROW has wrong value:", testWithRows, rows);
            assertEquals("Class variable COL has wrong value:", testWithColums, columns);
            assertEquals("Class variable empty has wrong value:", rows * columns - 1, empty);
            assertEquals("Class variable emptyRow has wrong value:", rows - 1, emptyRow);
            assertEquals("Class variable emptyCol has wrong value:", columns - 1, emptyCol);
            testWithRows = (int)(Math.random()*10+2);
            testWithColums = (int)(Math.random()*10+2);
            p1 = new Puzzle(testWithRows, testWithColums);
        }
    }
    
    /**
     * Description of CreatedPuzzleIsInRigthOrder().
     * this test method makes sure that in byte[][]-array
     * numbers are in right order.
     */
    @Test
    public void CreatedPuzzleIsInRigthOrder() { 
        byte[][] puzzle = p.getPuzzle();
        for (int i = 0; i < 10; i++) {
            int rows = puzzle.length;
            int cols = puzzle[0].length;
            byte numberInCell = 0;
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
    
    /**
     * Description of getNumberInCell_methodReturnsRightValue().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void getNumberInCell_methodReturnsRightValue() {
        assertArrayEquals("Method returned wrong cordinate for number 12: ", new int[]{2,3}, p.getCordinates(12 - 1));
    }
    
    /**
     * Description of getCordinates_methodReturnsRightValues().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void getCordinates_methodReturnsRightValues() {
        assertEquals("Method returned wrong value from the cell[1][3]:", 7, p.getNumberInCell(1, 3));
    }
    
    /**
     * Description of setPuzzle_methodChangesPuzzleAndEmptyRowColVariables().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void setPuzzle_methodChangesPuzzleAndEmptyRowColVariables() {
        Puzzle p1 = testCase(test1); 
        assertEquals("emptyRow has wrong value! "+test1.toString(), p1.getEmptyRow(), 0);     
        assertEquals("emptyCol has wrong value! "+test1.toString(), p1.getEmptyCol(), 3);
        int index = 0;
        for (int row = 0; row < p1.getNumberOfRows(); row++) {
            for (int col = 0; col < p1.getNumberOfColumns(); col++) {
                assertEquals("Puzzle has wrong value in row "+row+" col "+col, test1[index] - 1, p1.getNumberInCell(row, col));
                index++;
            }  
        }   
    }
    
    /**
     * Description of up_methodSwapBetweenEmptyAndNumber_ReturnsTrue().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void up_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
        Puzzle p1 = testCase(test2);
        assertTrue("Returns false but should return true", p1.up());
        movementTest(p1, 1, 2, 6, 2, 2);
    }
    
    /**
     * Description of up_methodReturnsFalseWhenSwapIsUndoable().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void up_methodReturnsFalseWhenSwapIsUndoable() {
        Puzzle p1 = testCase(test1);
        assertFalse("Returns true but should return false", p1.up());
    }
    
    /**
     * Description of down_methodSwapBetweenEmptyAndNumber_ReturnsTrue().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void down_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
        Puzzle p1 = testCase(test2);
        assertTrue("Returns false but should return true", p1.down());
        movementTest(p1, 3, 2, 9, 2, 2);
    }
    
    /**
     * Description of down_methodReturnsFalseWhenSwapIsUndoable().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void down_methodReturnsFalseWhenSwapIsUndoable() {
        assertFalse("Returns true but should return false", p.down());
    }
    
    /**
     * Description of left_methodSwapBetweenEmptyAndNumber_ReturnsTrue().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void left_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
        Puzzle p1 = testCase(test2);
        assertTrue("Returns false but should return true", p1.left());
        movementTest(p1, 2, 1, 5, 2, 2);        
    }
    
    /**
     * Description of left_methodReturnsFalseWhenSwapIsUndoable().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void left_methodReturnsFalseWhenSwapIsUndoable() {
        Puzzle p1 = testCase(test3);
        assertFalse("Returns true but should return false", p1.left());
    }
    
    /**
     * Description of right_methodSwapBetweenEmptyAndNumber_ReturnsTrue().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void right_methodSwapBetweenEmptyAndNumber_ReturnsTrue() {
        Puzzle p1 = testCase(test2);
        assertTrue("Returns false but should return true", p1.right());
        movementTest(p1, 2, 3, 4, 2, 2);
    }
    
    /**
     * Description of right_methodReturnsFalseWhenSwapIsUndoable().
     * this test method makes sure that right value is returned.
     */
    @Test
    public void right_methodReturnsFalseWhenSwapIsUndoable() {
        Puzzle p1 = testCase(test1);
        assertFalse("Returns true but should return false", p1.right());
    }
    
    /**
     * Description of randomMovesForRandomSizePuzzles().
     * this test method creates 100 random size puzzle and with each of them makes 
     * 1000 random movements to be sure that no illegal moves are allowed.
     */
    @Test
    public void randomMovesForRandomSizePuzzles() {
        for (int i = 0; i < 100; i++) {
            int testWithRows = (int)(Math.random()*10+3);
            int testWithColums = (int)(Math.random()*10+3);
            Puzzle p1 = new Puzzle(testWithRows, testWithColums);
            for (int j = 0; j < 1000; j++) {
                int move = (int) (Math.random() * 4);
                switch (move) {
                    case 0:
                        assertEquals("Up movement gave wrong return value: ",p1.getEmptyRow() > 0, p1.up());
                        break;
                    case 1:
                        assertEquals("Down movement gave wrong return value: ",p1.getEmptyRow() < p1.getNumberOfRows() - 1, p1.down());
                        break;
                    case 2:
                        assertEquals("Right movement gave wrong return value: ",p1.getEmptyCol() > 0, p1.left());
                        break;
                    case 3:
                        assertEquals("Left movement gave wrong return value: ",p1.getEmptyCol() < p1.getNumberOfColumns() - 1, p1.right());
                        break;
                }   
            }   
        }
        Puzzle p1 = testCase(test1);
        assertFalse("Returns true but should return false.", p1.right());
    }
      
    /**
     * Description of isReady_returnsRightBoolean().
     * this test method checks boolean return values for different test puzzles.
     */
    @Test
    public void isReady_returnsRightBoolean() {
       byte[][] tests = {test1, test2, test3, test4, test5};
       boolean[] testResults = {false, false, false, true, false};
        for (int i = 0; i < tests.length; i++) {
            Puzzle p1 = testCase(tests[i]);
            assertEquals("Testing wrong the readiness of the puzzle: "+Arrays.toString(tests[i]), testResults[i], p1.isReady());
        }
    }
    
    /**
     * Description of shuffle_emptyPlaceChangesRandomPosition().
     * this test method checks the randomness of the result of the shuffle() method.
     * 10.000 shuffles are created and the positions of empty-place is recorded.
     * the distribution of the positions are compared to average.
     */
    @Test
    public void shuffle_emptyPlaceChangesRandomPosition() {
        int[] emptyPosition = new int[16];
        int amountOfTestShuffles = 10000; 
        double average = amountOfTestShuffles / 16;
        for (int i = 0; i < 10000; i++) {
            Puzzle p1 = testCase(test4);
            p1.shuffle();
            emptyPosition[p1.getEmptyRow() * 4 + p1.getEmptyCol()]++;
        }
        for (int i = 0; i < emptyPosition.length; i++) {
            assertEquals("Values are not random enough: ", average, emptyPosition[i], 100);           
        }
    }

    /**
     * Description of testCase(byte[] test).
     * this method initializes Puzzle class with the test permutation of the puzzle.
     */
    private Puzzle testCase(byte[] test) {
        Puzzle p1 = new Puzzle();
        p1.setPuzzle(test);
        return p1;
    }
    
    /**
     * Description of movementTest(Puzzle p1, int emptyRow, int emptyCol, int num, int numRow, int numCol).
     * this method checks that right swap happened.
     */
    private void movementTest(Puzzle p1, int emptyRow, int emptyCol, int num, int numRow, int numCol) {
        assertEquals("emptyRow has wrong value! "+test2.toString(), emptyRow, p1.getEmptyRow());
        assertEquals("emptyCol has wrong value! "+test2.toString(), emptyCol, p1.getEmptyCol());
        assertEquals("Puzzle has wrong value in row 2 col 3: ",num - 1, p1.getNumberInCell(numRow, numCol));  
    }
}
