package projectSudoku;

public class Sudoku {

    // Predefined Sudoku puzzle (0 represents an empty cell)
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

    // Print the Sudoku board
    public static void printBoard(int[][] board) {
        System.out.println("\n-------------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" _ ");  // Empty cells
                } else {
                    System.out.print(" " + board[i][j] + " ");  // Filled cells
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    // Check if the move is valid (row, column, and 3x3 grid)
    public static boolean isMoveValid(int x, int y, int move) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == move) return false;
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == move) return false;
        }

        // Check 3x3 grid
        int startRow = (x / 3) * 3;
        int startCol = (y / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == move) return false;
            }
        }

        return true;  // If all checks pass
    }

    // Find the next empty cell (returns row and column)
    public static int[] getNextEmptyCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};  // Return coordinates of the empty cell
                }
            }
        }
        return null;  // No empty cells
    }

    // Solve the Sudoku puzzle using backtracking
    public static boolean solve() {
        int[] next = getNextEmptyCell();
        if (next == null) return true;  // Puzzle solved

        int x = next[0];
        int y = next[1];

        // Try all numbers from 1 to 9
        for (int i = 1; i <= 9; i++) {
            if (isMoveValid(x, y, i)) {
                board[x][y] = i;  // Place the number
                if (solve()) {
                    return true;  // Puzzle solved
                }
                board[x][y] = 0;  // Backtrack if not solvable
            }
        }

        return false;  // No solution found
    }

    // Main method to run the Sudoku solver
    public static void main(String[] args) {
        System.out.println("Sudoku Puzzle Before Solving:");
        printBoard(board);

        // Solve the Sudoku puzzle
        if (solve()) {
            System.out.println("\nSudoku Puzzle After Solving:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists.");
        }
    }
}
