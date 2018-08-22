package edu.kings.cs232.project3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * Class that represents a Sudoku board.
 * 
 * @author Andrea Siejna
 *
 */
public class Sudoku implements Iterable<Integer> {

    /**
     * Value that represents an empty square on the Sudoku board.
     */
    private static final int ILLEGAL;

    /**
     * An empty board of Sudoku as a 2D array.
     */
    Integer[][] sudokuArray;

    /**
     * Empty squares on the Sudoku board will be a value that isn't applicable
     * on a real board.
     * 
     * An arbitrary value of 0 was chosen.
     */
    static {
        ILLEGAL = 0;
    }

    /**
     * Constructor for an empty Sudoku board.
     * 
     */
    public Sudoku() {
        sudokuArray = new Integer[9][9];
        defaultBoard();

    }

    /**
     * Fills in the Sudoku board with pre-set values.
     * 
     * @param newSudoku
     *            New sudoku board.
     */
    public void filledBoard(Integer[][] newSudoku) {
        sudokuArray = newSudoku;
    }

    /**
     * Fills in the Sudoku board with illegal values.
     */
    public void defaultBoard() {

        for (int i = 0; i < sudokuArray.length; i++) {
            for (int j = 0; j < sudokuArray[i].length; j++) {
                sudokuArray[i][j] = ILLEGAL;
            }
        }

    }

    /**
     * Prints the Sudoku board.
     */
    public void printBoard() {

        Iterator<Integer> board = iterator();

        int row = 0;
        while (board.hasNext()) {

            System.out.print(" " + board.next() + " ");
            row++;

            if (row == 9) {
                System.out.println();
                row = 0;
            }

        }

    }

    /**
     * Sets a square with the given value.
     * 
     * @param row
     *            The desired row destination.
     * @param col
     *            The desired column destination.
     * @param value
     *            The value to set the square to.
     * @throws NoSuchElementException
     *             Exception thrown if not within the boundaries (0 - 8).
     */
    public void setSquare(int row, int col, int value) throws NoSuchElementException {

        if (row < 0 || row > 8 || col < 0 || col > 8) {
            throw new NoSuchElementException();

        } else {

            if (value < 1 || value > 9) {

                System.out.println("Illegal value.");

            } else {

                sudokuArray[row][col] = value;

            }
        }

    }

    /**
     * Gets the cell at the given row and column.
     * 
     * @param row
     *            The desired row destination.
     * @param col
     *            The desired column destination.
     * @return value The value of the cell.
     * @throws NoSuchElementException
     *             Exception thrown if not within the boundaries (0 - 8).
     */
    public int getSquare(int row, int col) throws NoSuchElementException {

        int value = 0;

        if (row < 0 || row > 8 || col < 0 || col > 8) {
            throw new NoSuchElementException();

        } else {

            sudokuArray[row][col] = value;

        }

        return value;

    }

    /**
     * If every cell is a legal move, then the puzzle itself is legal.
     * 
     * @return If the puzzle is legal or not.
     */
    public boolean isLegalPuzzle() {
        boolean isLegalPuzzle = true;

        // Loop through each cell
        for (int i = 0; i < sudokuArray.length; i++) {

            for (int j = 0; j < sudokuArray[j].length; j++) {

                // If this is a legal move or not
                if (isLegalMove(i, j) == false) {
                    isLegalPuzzle = false;
                }

            }
        }

        return isLegalPuzzle;
    }

    /**
     * Checks to see if the cell is a legal move in the row.
     * 
     * @param row
     *            The row desired.
     * @param col
     *            The column desired.
     * @return If the cell is legal within the row.
     */
    public boolean isLegalRowMove(int row, int col) {
        boolean isLegalRowMove = true;

        // Grab the cell we want
        int cell = sudokuArray[row][col];

        // First check and see if the cell is even a legal number (1 - 9)
        if (cell >= 1 && cell <= 9) {

            // Grab the row iterator from the given row we're in.
            Iterator<Integer> rowIterator = rowIterator(row);

            // !!!!!!!!!! Go through each value of this row
            int numberSame = 0;
            while (rowIterator.hasNext()) {

                // The current value we're at in the row
                int currentRowValue = rowIterator.next();

                // If it's the same
                if (currentRowValue == cell) {
                    numberSame++;
                }

            }

            // If there's more than one that are the same
            // if there's one (accounting for the cell we're talking about)
            // or less than we're good.
            if (numberSame > 1) {
                isLegalRowMove = false;
            }

        } else {
            isLegalRowMove = false;

        }

        return isLegalRowMove;

    }

    /**
     * Checks to see if the cell is a legal move in the column.
     * 
     * @param row
     *            The row desired.
     * @param col
     *            The column desired.
     * @return If the cell is legal within the column.
     */
    public boolean isLegalColMove(int row, int col) {
        boolean isLegalColMove = true;

        // Grab the cell we want
        int cell = sudokuArray[row][col];

        // First check and see if the cell is even a legal number (1 - 9)
        if (cell >= 1 && cell <= 9) {

            // Grab the column iterator from the given column we're in.
            Iterator<Integer> colIterator = colIterator(col);

            // !!!!!!!!!! Go through each value of this column
            int numberSame = 0;
            while (colIterator.hasNext()) {

                // The current value we're at in the column
                int currentColValue = colIterator.next();

                // If it's the same
                if (currentColValue == cell) {
                    numberSame++;
                }

            }

            // If there's more than one that are the same
            // if there's one (accounting for the cell we're talking about)
            // or less than we're good.
            if (numberSame > 1) {
                isLegalColMove = false;
            }

        } else {
            isLegalColMove = false;

        }

        return isLegalColMove;

    }

    /**
     * Checks to see if the cell is a legal move in the block.
     * 
     * @param row
     *            The row desired.
     * @param col
     *            The column desired.
     * @return If the cell is legal within the block.
     */
    public boolean isLegalBlockMove(int row, int col) {
        boolean isLegalBlockMove = true;

        // Grab the cell we want
        int cell = sudokuArray[row][col];

        // First check and see if the cell is even a legal number (1 - 9)
        if (cell >= 1 && cell <= 9) {

            // Grab the block iterator from the given block we're in.
            int block = (((row / 3) * 3) + (col / 3));
            Iterator<Integer> blockIterator = blockIterator(block);

            // !!!!!!!!!! Go through each value of this block
            int numberSame = 0;
            while (blockIterator.hasNext()) {

                // The current value we're at in the block
                int currentBlockValue = blockIterator.next();

                // If it's the same
                if (currentBlockValue == cell) {
                    numberSame++;
                }

            }

            // If there's more than one that are the same
            // if there's one (accounting for the cell we're talking about)
            // or less than we're good.
            if (numberSame > 1) {
                isLegalBlockMove = false;
            }

        } else {
            isLegalBlockMove = false;

        }

        return isLegalBlockMove;

    }

    /**
     * Checks to see if the cell is a legal move.
     * 
     * @param row
     *            The given row.
     * @param col
     *            The given col.
     * @return Whether the cell is legal or not.
     */
    public boolean isLegalMove(int row, int col) {

        boolean isLegalMove = (isLegalRowMove(row, col)) && (isLegalColMove(row, col)) && (isLegalBlockMove(row, col));

        return isLegalMove;
    }

    /**
     * Find a solution starting from the given row and column working in
     * row-major order.
     * 
     * @param r
     *            The given row
     * @param c
     *            The given column
     * @return if the Sudoku is solved.
     */
    public boolean solveSudoku(int r, int c) {

        if (r == sudokuArray.length) {

            boolean allCorrect = true;

            for (int i = 0; i < sudokuArray.length; i++) {

                for (int j = 0; j < sudokuArray[i].length; j++) {

                    if (isLegalMove(r, c) == false) {
                        allCorrect = false;
                    }
                }
            }

            if (allCorrect) {
                return true;
            } else {
                return false;
            }

        } else if (sudokuArray[r][c] != 0) {

            if ((c + 1) != sudokuArray.length) {
                return solveSudoku(r, c + 1);
            } else {
                return solveSudoku(r + 1, 0);
            }

        } else {

            for (int value = 1; value <= 9; value++) {
                setSquare(r, c, value);

                if ((c + 1) != sudokuArray.length) {

                    if (solveSudoku(r, c + 1) == true) {
                        return true;
                    }

                } else {

                    if (solveSudoku(r + 1, 0) == true) {
                        return true;
                    }

                }

            }
            setSquare(r, c, 0);
            return false;
        }
    }

    /**
     * Returns an iterator over the entire Sudoku board.
     * 
     * @return An iterator that visits each cell on the board.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Board();
    }

    /**
     * Returns an iterator over an entire row of the Sudoku board.
     * 
     * @param currentRow
     *            The desired row.
     * 
     * @return An iterator that visits each cell of the row.
     */
    public Iterator<Integer> rowIterator(int currentRow) {
        return new Row(currentRow);
    }

    /**
     * Returns an iterator over an entire column of the Sudoku board.
     * 
     * @param currentCol
     *            The desired column.
     * 
     * @return An iterator that visits each cell of the column.
     */
    public Iterator<Integer> colIterator(int currentCol) {
        return new Column(currentCol);
    }

    /**
     * Returns an iterator over an entire block of the Sudoku board.
     * 
     * @param givenBlock
     *            The block we want to iterate over.
     * @return An iterator of that block.
     */
    public Iterator<Integer> blockIterator(int givenBlock) {
        return new Block(givenBlock);
    }

    // INNER CLASS ROW ITERATOR
    public class Row implements Iterator<Integer> {

        /**
         * Cursor that indicates where we are in the row
         */
        private int rowCursor;

        /**
         * Cursor that indicates where we are in the column
         */
        private int colCursor = 0;

        /**
         * Element to be removed
         */
        private int removeLocation = sudokuArray[rowCursor][colCursor];
        
        /**
         * Constructor for the Row class.
         * 
         * @param row
         *            The desired row.
         */
        public Row(int row) {
            rowCursor = row;
        }

        /**
         * If there's a next element
         * 
         * @return Whether or not there's an element next.
         */
        @Override
        public boolean hasNext() {
            boolean hasNext = true;

            if (colCursor < 0 || colCursor > 8 || rowCursor < 0 || rowCursor > 8) {
                hasNext = false;
            }

            return hasNext;
        }

        /**
         * Returns the element at the current cursor.
         * 
         * @return The value of the cell at the current cursor.
         */
        @Override
        public Integer next() {
            Integer current = null;

            if (!hasNext()) {

                throw new NoSuchElementException();

            } else {

                current = sudokuArray[rowCursor][colCursor];
                removeLocation = current;
                colCursor++;
                

            }

            return current;

        }

        /**
         * Removes the last returned cell from the collection.
         */
        @Override
        public void remove() {

            if (!hasNext()) {

                throw new IllegalStateException();

            } else {
                removeLocation = ILLEGAL;
                
            }

        }

    }

    // INNER CLASS COLUMN ITERATOR
    public class Column implements Iterator<Integer> {

        /**
         * Cursor that indicates where we are in the row
         */
        private int rowCursor = 0;

        /**
         * Cursor that indicates where we are in the column
         */
        private int colCursor;

        /**
         * Element to be removed
         */
        private int removeLocation = sudokuArray[rowCursor][colCursor];
        
        /**
         * Constructor for the Column class.
         * 
         * @param column
         *            The desired column.
         */
        public Column(int column) {
            colCursor = column;
        }

        /**
         * If there's a next element
         * 
         * @return Whether or not there's an element next.
         */
        @Override
        public boolean hasNext() {
            boolean hasNext = true;

            if (colCursor < 0 || colCursor > 8 || rowCursor < 0 || rowCursor > 8) {
                hasNext = false;
            }

            return hasNext;
        }

        /**
         * Returns the element at the current cursor.
         * 
         * @return The value of the cell at the current cursor.
         */
        @Override
        public Integer next() {
            Integer current = null;

            if (!hasNext()) {

                throw new NoSuchElementException();

            } else {

                current = sudokuArray[rowCursor][colCursor];
                removeLocation = current;
                rowCursor++;
                

            }

            return current;

        }

        /**
         * Removes the last returned cell from the collection.
         */
        @Override
        public void remove() {

            if (!hasNext()) {

                throw new IllegalStateException();

            } else {
               removeLocation = ILLEGAL;
                
            }

        }

    }

    // INNER CLASS BLOCK ITERATOR
    public class Block implements Iterator<Integer> {

        /**
         * Cursor that indicates where we are in the block
         */
        private int blockCursor;
        
        /**
         * Cursor that indicates where we are in the block list.
         */
        private int listCursor;
        
        /**
         * Cursor that indicates where we are in the row.
         */
        private int rowCursor;
        
        /**
         * Cursor that indicates where we are in the column.
         */
        private int colCursor;
        
        /**
         * Element to be removed
         */
        private int removeLocation = sudokuArray[rowCursor][colCursor];

        /**
         * Constructor for the Block class.
         * 
         * @param block
         *            The desired block.
         */
        public Block(int block) {
            blockCursor = block;
        }

        /**
         * If there's a next element
         * 
         * @return Whether or not there's an element next.
         */
        @Override
        public boolean hasNext() {
            boolean hasNext = true;

            if (blockCursor < 0 || blockCursor > 8 || listCursor < 0 || listCursor > 8) {
                hasNext = false;
            }

            return hasNext;
        }

        /**
         * Returns the element at the current cursor.
         * 
         * @return The value of the cell at the current cursor.
         */
        @Override
        public Integer next() {
            Integer current = null;

            if (!hasNext()) {

                throw new NoSuchElementException();

            } else {

                ArrayList<Integer> blockArrayList = getBlockList(blockCursor);

                current = blockArrayList.get(listCursor);
                listCursor++;

            }

            return current;

        }

        /**
         * Helper method to obtain the block from the board.
         * 
         * @param blockCursor
         *            The current block we want.
         * @return blockList A list of the values from the block in top-down,
         *         left-right order.
         */
        public ArrayList<Integer> getBlockList(int blockCursor) {
            // Store values in a block that we will return
            ArrayList<Integer> blockList = new ArrayList<Integer>();

            // rowCursor and colCursor
            rowCursor = 0;
            colCursor = 0;

            // Loop through each block
            for (int i = 0; i <= blockCursor; i++) {

                // Seeing what column we're at

                // If we're not one of the first three blocks vertically
                if ((i + 3) % 3 == 0) {
                    colCursor = 0;

                    // If we're not one of the first three blocks horizontally
                    if (i != 0)
                        rowCursor += 3;
                }

                // If we're one of the blocks in the 2nd column vertically
                if ((i + 2) % 3 == 0) {
                    colCursor = 3;

                }

                // If we're one of the blocks in the 3rd column vertically
                if ((i + 1) % 3 == 0) {
                    colCursor = 6;

                }
            }

            // Loop through each row and column
            for (int j = rowCursor; j < rowCursor + 3; j++) {

                for (int k = colCursor; k < colCursor + 3; k++) {

                    // Add the value to the list
                    blockList.add((sudokuArray[j][k]));
                    
                }
            }

            // Return the list
            return blockList;

        }

        /**
         * Removes the last returned cell from the collection.
         */
        @Override
        public void remove() {

            if (!hasNext()) {

                throw new IllegalStateException();

            } else {

                // rowCursor and colCursor
                rowCursor = 0;
                colCursor = 0;

                // Loop through each block
                for (int i = 0; i <= blockCursor; i++) {

                    // Seeing what column we're at

                    // If we're not one of the first three blocks vertically
                    if ((i + 3) % 3 == 0) {
                        colCursor = 0;

                        // If we're not one of the first three blocks
                        // horizontally
                        if (i != 0)
                            rowCursor += 3;
                    }

                    // If we're one of the blocks in the 2nd column vertically
                    if ((i + 2) % 3 == 0) {
                        colCursor = 3;

                    }

                    // If we're one of the blocks in the 3rd column vertically
                    if ((i + 1) % 3 == 0) {
                        colCursor = 6;

                    }
                }

                // Loop through each row and column
                for (int j = rowCursor; j < rowCursor + 3; j++) {

                    for (int k = colCursor; k < colCursor + 3; k++) {

                        // Make it illegal
                        sudokuArray[j][k] = ILLEGAL;
                    }
                }

                next();
            }

        }

    }

    // INNER CLASS BOARD ITERATOR
    public class Board implements Iterator<Integer> {

        /**
         * Cursor that indicates where we are in the board.
         */
        private int boardCursor;
        
        /**
         * Cursor that indicates where we are in the row.
         */
        private int rowCursor;
        
        /**
         * Cursor that indicates where we are in the column.
         */
        private int colCursor;

        /**
         * Element to be removed
         */
        private int removeLocation = sudokuArray[rowCursor][colCursor];
        
        /**
         * Constructor for the Board class.
         * 
         */
        public Board() {
            boardCursor = 0;
        }

        /**
         * If there's a next element
         * 
         * @return Whether or not there's an element next.
         */
        @Override
        public boolean hasNext() {
            boolean hasNext = true;

            if (boardCursor > 80) {
                hasNext = false;
            }

            return hasNext;
        }

        /**
         * Returns the element at the current cursor.
         * 
         * @return The value of the cell at the current cursor.
         */
        @Override
        public Integer next() {
            Integer current = null;

            if (!hasNext()) {

                throw new NoSuchElementException();

            } else {

                ArrayList<Integer> boardList = new ArrayList<Integer>();

                // Loop through each row and column and put it into a list
                for (rowCursor = 0; rowCursor < sudokuArray.length; rowCursor++) {

                    for (colCursor = 0; colCursor < sudokuArray[rowCursor].length; colCursor++) {

                        int add = sudokuArray[rowCursor][colCursor];
                        
                        boardList.add(add);
                    }
                }

                current = boardList.get(boardCursor);
                boardCursor++;

            }

            return current;

        }

        /**
         * Removes the last returned cell from the collection.
         */
        @Override
        public void remove() {

            if (!hasNext()) {

                throw new IllegalStateException();

            } else {

                // Loop through each row and column
                for (rowCursor = 0; rowCursor < sudokuArray.length; rowCursor++) {

                    for (colCursor = 0; colCursor < sudokuArray[rowCursor].length; colCursor++) {

                        sudokuArray[rowCursor][colCursor] = ILLEGAL;

                    }
                }
            }

        }

    }

}
