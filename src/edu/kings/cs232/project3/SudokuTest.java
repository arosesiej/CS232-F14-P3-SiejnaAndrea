package edu.kings.cs232.project3;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Sudoku.
 * 
 * @author Andrea Siejna
 * 
 */


public class SudokuTest {
	
	private Sudoku puzzle;

    /**
     * Populates and creates partially-filled Sudoku board.
     * 
     * (9 answers needed)
     * 
     */
    @Before
    public void setup(){
        puzzle = new Sudoku();
        
        // CORRECT BOARD

        // { 8, 4, 6, 9, 3, 7, 1, 5, 2 },
        // { 3, 1, 9, 6, 2, 5, 8, 4, 7 },
        // { 7, 5, 2, 1, 8, 4, 9, 6, 3 },
        // { 2, 8, 5, 7, 1, 3, 6, 9, 4 },
        // { 4, 6, 3, 8, 5, 9, 2, 7, 1 },
        // { 9, 7, 1, 2, 4, 6, 3, 8, 5 },
        // { 1, 2, 7, 5, 9, 8, 4, 3, 6 },
        // { 6, 3, 8, 4, 7, 1, 5, 2, 9 },
        // { 5, 9, 4, 3, 6, 2, 7, 1, 8 }
        //
        
        Integer[][] sudoku = new Integer[][] {
                { 8, 4, 6, 9, 3, 7, 0, 5, 2 },
                { 3, 1, 0, 6, 2, 5, 8, 4, 7 },
                { 7, 5, 2, 1, 8, 0, 9, 6, 3 },
                { 2, 0, 5, 7, 1, 3, 6, 9, 4 },
                { 4, 6, 3, 8, 5, 9, 2, 7, 0 },
                { 9, 7, 1, 0, 4, 6, 3, 8, 5 },
                { 1, 2, 7, 5, 9, 0, 4, 3, 6 },
                { 0, 3, 8, 4, 7, 1, 5, 2, 9 },
                { 5, 9, 4, 3, 0, 2, 7, 1, 8 }
              } ;
        
        puzzle.filledBoard(sudoku);
        
    }
    
    

    
    /**
     * Test to see if isLegalMove returns correctly
     */
    public void testisLegalMoveMultipleCases(){
        
        // Create the board.       
        Integer[][] sudoku = new Integer[][] {
                { 8, 4, 6, 9, 3, 7, 4, 5, 2 },
                { 3, 1, 0, 6, 2, 5, 8, 4, 7 },
                { 7, 5, 2, 1, 8, 0, 9, 6, 3 },
                { 2, 0, 5, 7, 1, 3, 6, 9, 4 },
                { 4, 6, 3, 8, 5, 9, 2, 7, 8 },
                { 9, 7, 1, 0, 4, 6, 3, 8, 5 },
                { 1, 2, 7, 5, 9, 0, 4, 3, 6 },
                { 0, 3, 8, 4, 7, 1, 5, 2, 9 },
                { 5, 9, 4, 3, 10, 2, 7, 1, 8 }
              } ;
        
        puzzle.filledBoard(sudoku);
        
        // Duplicate number in row 
        assertFalse(puzzle.isLegalMove(0, 6));
        
        // Legal move
        assertTrue(puzzle.isLegalMove(1, 0));
        
        // Duplicate number in column
        assertFalse(puzzle.isLegalMove(8, 8));
        
        // Duplicate number in block
        assertFalse(puzzle.isLegalMove(4, 8));
        
        // Illegal value
        assertFalse(puzzle.isLegalMove(8, 4));
        
        // Legal move
        assertTrue(puzzle.isLegalMove(2, 0));

        
    }
    
    /**
     * Test to see if isLegalMove returns correctly
     * when given a cell that has an illegal value (not 1 - 9);
     */
    public void testisLegalMoveIllegalValue(){
        
        // Create the board.
        setup();
        
        boolean legalMove = puzzle.isLegalMove(0, 6);
        assertFalse(legalMove);

        
    }
    
    /**
     * Test to see if isLegalMove returns correctly
     * when given a cell that is legal.
     */
    public void testisLegalMoveLegalMove(){
        
        // Create the board.
        setup();
        
        boolean legalMove = puzzle.isLegalMove(1, 4);
        assertTrue(legalMove);

        
    }
    
    // ROW ITERATOR TESTS

    /**
     * 
     * Test to see if we correctly iterate through each row.
     * 
     */
    @Test
    public void testRowIteratorCorrect() {
        assertEquals("Incorrect row!", "846937052", createRowIterator(0));
        assertEquals("Incorrect row!", "310625847", createRowIterator(1));
        assertEquals("Incorrect row!", "752180963", createRowIterator(2));
        assertEquals("Incorrect row!", "205713694", createRowIterator(3));
        assertEquals("Incorrect row!", "463859270", createRowIterator(4));
        assertEquals("Incorrect row!", "971046385", createRowIterator(5));
        assertEquals("Incorrect row!", "127590436", createRowIterator(6));
        assertEquals("Incorrect row!", "038471529", createRowIterator(7));
        assertEquals("Incorrect row!", "594302718", createRowIterator(8));
        
    }

    /**
     * Test to see if given an illegal row that
     * a NoSuchElementException is thrown.
     */
    @Test(expected=NoSuchElementException.class)
    public void testRowNextThrowsNoSuchElementException() {
        
     // Create the row iterator w/ an illegal row
        Iterator<Integer> rowIterator = puzzle.rowIterator(9);  
        rowIterator.next();
        
    }
    
    /**
     * Test to see if given an illegal row that
     * hasNext() returns false.
     */
    @Test
    public void testRowHasNextFalse() {
        
     // Create the row iterator w/ an illegal row
        Iterator<Integer> rowIterator = puzzle.rowIterator(9);  
        assertFalse(rowIterator.hasNext());
        
    }
    
    /**
     * Helper method to test the row iterator.
     * 
     * @param givenRow
     *            What row we would like to use.
     * @return The String equivalent to that given row.
     */
    public String createRowIterator(int givenRow) {
        // Populate the board
        setup();

        // Create the row iterator
        Iterator<Integer> rowIterator = puzzle.rowIterator(givenRow);

        // Create the StringBuilder to hold the
        // equivalent of the row of Integers
        StringBuilder row = new StringBuilder("");

        // Loop through each column
        int i = 0;
        while (rowIterator.hasNext()) {
            row.insert(i, rowIterator.next());
            i++;
        }
        
        // Return the string equivalent of the row.
        return (row.toString());

    }
    
    
    
    // COLUMN ITERATOR TESTS
    
    /**
     * 
     * Test to see if we correctly iterate through each column.
     * 
     */
    @Test
    public void testColumnIteratorCorrect() {
        assertEquals("Incorrect column!", "837249105", createColumnIterator(0));
        assertEquals("Incorrect column!", "415067239", createColumnIterator(1));
        assertEquals("Incorrect column!", "602531784", createColumnIterator(2));
        assertEquals("Incorrect column!", "961780543", createColumnIterator(3));
        assertEquals("Incorrect column!", "328154970", createColumnIterator(4));
        assertEquals("Incorrect column!", "750396012", createColumnIterator(5));
        assertEquals("Incorrect column!", "089623457", createColumnIterator(6));
        assertEquals("Incorrect column!", "546978321", createColumnIterator(7));
        assertEquals("Incorrect column!", "273405698", createColumnIterator(8));
        
    }
    
    /**
     * Test to see if given an illegal column that
     * a NoSuchElementException is thrown.
     */
    @Test(expected=NoSuchElementException.class)
    public void testColumnNextThrowsNoSuchElementException() {
        
     // Create the col iterator w/ an illegal col
        Iterator<Integer> colIterator = puzzle.colIterator(9);  
        colIterator.next();
        
    }
    
    /**
     * Test to see if given an illegal column that
     * hasNext() returns false.
     */
    @Test
    public void testColHasNextFalse() {
        
     // Create the column iterator w/ an illegal column
        Iterator<Integer> colIterator = puzzle.colIterator(9);  
        assertFalse(colIterator.hasNext());
        
    }

    /**
     * Helper method to test the column iterator.
     * 
     * @param givenColumn
     *            What column we would like to use.
     * @return The String equivalent to that given column.
     */
    public String createColumnIterator(int givenColumn) {
        // Populate the board
        setup();

        // Create the col iterator
        Iterator<Integer> columnIterator = puzzle.colIterator(givenColumn);

        // Create the StringBuilder to hold the
        // equivalent of the col of Integers
        StringBuilder col = new StringBuilder("");

        // Loop through each col
        int i = 0;
        while (columnIterator.hasNext()) {
            col.insert(i, columnIterator.next());
            i++;
        }
        
        // Return the string equivalent of the col.
        return (col.toString());

    }
    
    // BLOCK ITERATOR TESTS
    
    /**
     * 
     * Test to see if we correctly iterate through each block.
     * 
     */
    @Test
    public void testBlockIteratorCorrect() {
        assertEquals("Incorrect block!", "846310752", createBlockIterator(0));
        assertEquals("Incorrect block!", "937625180", createBlockIterator(1));
        assertEquals("Incorrect block!", "052847963", createBlockIterator(2));
        assertEquals("Incorrect block!", "205463971", createBlockIterator(3));
        assertEquals("Incorrect block!", "713859046", createBlockIterator(4));
        assertEquals("Incorrect block!", "694270385", createBlockIterator(5));
        assertEquals("Incorrect block!", "127038594", createBlockIterator(6));
        assertEquals("Incorrect block!", "590471302", createBlockIterator(7));
        assertEquals("Incorrect block!", "436529718", createBlockIterator(8));
        
    }

    /**
     * Test to see if given an illegal block that
     * a NoSuchElementException is thrown.
     */
    @Test(expected=NoSuchElementException.class)
    public void testBlockNextThrowsNoSuchElementException() {
        
     // Create the block iterator w/ an illegal block
        Iterator<Integer> blockIterator = puzzle.blockIterator(9);  
        blockIterator.next();
        
    }
    
    /**
     * Test to see if given an illegal block that
     * hasNext() returns false.
     */
    @Test
    public void testBlockHasNextFalse() {
        
     // Create the block iterator w/ an illegal block
        Iterator<Integer> blockIterator = puzzle.blockIterator(9);  
        assertFalse(blockIterator.hasNext());
        
    }

    /**
     * Helper method to test the block iterator.
     * 
     * @param givenBlock
     *            What block we would like to use.
     * @return The String equivalent to that given block.
     */
    public String createBlockIterator(int givenBlock) { 

        // Populate the board
        setup();

        // Create the block iterator
        Iterator<Integer> blockIterator = puzzle.blockIterator(givenBlock);

        // Create the StringBuilder to hold the
        // equivalent of the row of Integers
        StringBuilder block = new StringBuilder("");

        // Loop through each column
        int i = 0;
        while (blockIterator.hasNext()) {
            block.insert(i, blockIterator.next());
            i++;
        }
        
        // Return the string equivalent of the row.
        return (block.toString());

    }

    
    // BOARD ITERATOR TESTS
    
    /**
     * 
     * Test to see if we correctly iterate through the board.
     * 
     */
    @Test
    public void testBoardIteratorCorrect() {
        assertEquals("Incorrect board!", "846937052310625847752180963205713694463859270971046385127590436038471529594302718", createBoardIterator());
        
    }

    /**
     * Helper method to test the board iterator.
     * 
     * @return The String equivalent to the board.
     */
    public String createBoardIterator() {
        // Populate the board
        setup();

        // Create the row iterator
        Iterator<Integer> boardIterator = puzzle.iterator();

        // Create the StringBuilder to hold the
        // equivalent of the board
        StringBuilder board = new StringBuilder("");

        // Loop through each value
        int i = 0;
        while (boardIterator.hasNext()) {
            board.insert(i, boardIterator.next());
            i++;
        }
        
        // Return the string equivalent of the row.
        return (board.toString());

    }
}
