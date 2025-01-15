package projectSudoku;

public class SudokuSolver {

    // This class contains methods to solve the Sudoku puzzle.
    // We don't need to change the board in this file.

    // Check if the move is valid (row, column, and 3x3 grid)
    public static boolean isMoveValid(int x, int y, int move, int[][] board) {
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
    public static int[] getNextEmptyCell(int[][] board) {
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
    public static boolean solve(int[][] board) {
        int[] next = getNextEmptyCell(board);
        if (next == null) return true;  // Puzzle solved

        int x = next[0];
        int y = next[1];

        // Try all numbers from 1 to 9
        for (int i = 1; i <= 9; i++) {
            if (isMoveValid(x, y, i, board)) {
                board[x][y] = i;  // Place the number
                if (solve(board)) {
                    return true;  // Puzzle solved
                }
                board[x][y] = 0;  // Backtrack if not solvable
            }
        }

        return false;  // No solution found
    }
}

