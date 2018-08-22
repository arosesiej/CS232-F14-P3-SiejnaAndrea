package edu.kings.cs232.project3;

import java.util.Scanner;

/**
 * Main class for users to interact with the Sudoku board.
 * 
 * @author Andrea Siejna
 *
 */
public class Main {

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(String[] args) {

        Sudoku puzzle = new Sudoku();

        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println("1 Enter the value of a cell");
        System.out.println("2 Print the current solution");
        System.out.println("3 Check this solution");
        System.out.println("4 Solve the puzzle");
        System.out.println("0 Quit");
        String answer = input.next();

        while (answer != "0") {

            switch (answer) {

            // Enter the value of a cell
            case ("1"):

                System.out.println("---Entering a cell value---");
                System.out.println("Enter the row (0 - 8):");
                int row = input.nextInt();
                System.out.println("Enter the col (0 - 8):");
                int col = input.nextInt();
                System.out.println("Enter the value (1 - 9):");
                int value = input.nextInt();

                puzzle.setSquare(row, col, value);
                answer = "";

            break;

            // Print the current solution
            case ("2"):

                puzzle.printBoard();
                answer = "";

            break;

            // Check this solution
            case ("3"):

                boolean anyBlank = false;
                boolean correctSoFar = false;
                int incorrectCount = 0;

                // Go through each cell
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                        // If the current cell is blank
                        if (puzzle.getSquare(i, j) == 0) {
                            anyBlank = true;
                        }
                        // Otherwise cell is not blank
                        else {

                            // If the cell is a legal move
                            if (puzzle.isLegalMove(i, j)) {

                                // If not a legal move
                            } else {
                                incorrectCount++;
                            }

                        }

                        // If any are incorrect then we are not correct so far
                        if (incorrectCount > 0) {
                            correctSoFar = false;
                        }

                    }
                }

                // If there are blanks and no incorrect moves
                if (anyBlank && correctSoFar) {
                    System.out.println("So far so good!");

                    // If there aren't any blanks and no incorrect moves
                } else if (anyBlank == false && correctSoFar) {
                    System.out.println("Correct! You're so smart!");

                    // This is not a legal solution
                } else {
                    System.out.println("Incorrect! Keep trying.");
                }

                answer = "";
            break;

            // Solve the puzzle
            case ("4"):

                // If the puzzle can be solved.
                if (puzzle.solveSudoku(0, 0)) {
                    System.out.println("Puzzle has been solved.");

                    // If the puzzle cannot be solved.
                } else {
                    System.out.println("You made a mistake dummy!");

                }

                answer = "";
            break;

            // Invalid input
            default:

                System.out.println("What would you like to do?");
                System.out.println("1 Enter the value of a cell");
                System.out.println("2 Print the current solution");
                System.out.println("3 Check this solution");
                System.out.println("4 Solve the puzzle");
                System.out.println("0 Quit");
                answer = input.next();
            break;
            }

        }

    }

}
