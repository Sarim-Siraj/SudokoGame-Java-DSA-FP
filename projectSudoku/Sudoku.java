package projectSudoku;


public class Sudoku {

    // Pre-defined Sudoku puzzle (use 0 for empty cells)
    private static int[][] board = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    // Print Sudoku Board
    public static void printBoard(int[][] board) {
        System.out.println("\n-------------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    // To check if the current move is valid or not.
    public static boolean isMoveValid(int x, int y, int move, int[][] board) {
        // To check if the number entered is valid or not.
        if (x < 0 || y < 0 || x > 8 || y > 8) return false;

        // To check the column constraint.
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == move) return false;
        }

        // To check the row constraint.
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == move) return false;
        }

        // To check the 3x3 subgrid.
        for (int i = (x / 3) * 3; i < (x / 3) * 3 + 3; i++) {
            for (int j = (y / 3) * 3; j < (y / 3) * 3 + 3; j++) {
                if (board[i][j] == move) return false;
            }
        }

        // After all checks, return true.
        return true;
    }

    // To get the next empty point in the board
    public static int[] getNext(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // If no empty cell is found, return null
    }

    // Solves the Sudoku using Backtracking
    public static boolean solve(int[][] board) {
        int[] next = getNext(board);

        if (next == null) {
            return true; // Solved
        }

        int x = next[0];
        int y = next[1];

        // Try numbers 1-9 for the current empty cell
        for (int i = 1; i <= 9; i++) {
            if (isMoveValid(x, y, i, board)) {
                board[x][y] = i;
                if (solve(board)) {
                    return true;
                }
                board[x][y] = 0; // Undo move
            }
        }

        return false; // If no valid number can be placed, return false
    }

    // Main method
    public static void main(String[] args) {
        // Step 1: Print the board before solving
        System.out.println("Sudoku Puzzle Before Solving:");
        printBoard(board);

        // Step 2: Solve the Sudoku puzzle
        if (solve(board)) {
            // Step 3: Print the solved Sudoku puzzle
            System.out.println("\nSudoku Puzzle After Solving:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for this Sudoku puzzle.");
        }
    }
}
