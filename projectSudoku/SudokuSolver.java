package projectSudoku;
public class SudokuSolver {

    // Check if the block at (x, y) is empty (0 indicates empty)
    public static boolean isBlockValid(int x, int y, int board[][]) {
        return board[x][y] == 0;
    }

    // Check if the Sudoku board is completely filled
    public static boolean isComplete(int board[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) return false;  // Found an empty cell
            }
        }
        return true;  // No empty cells left
    }

    // Check if placing 'move' in (x, y) is a valid move
    public static boolean isMoveValid(int x, int y, int move, int board[][]) {
        // Check if coordinates are within valid range
        if (x < 0 || y < 0 || x >= 9 || y >= 9) return false;

        // Check column constraint
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == move) return false;
        }

        // Check row constraint
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == move) return false;
        }

        // Check sub-grid constraint (3x3 box)
        int startRow = (x / 3) * 3;
        int startCol = (y / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == move) return false;
            }
        }

        return true;  // All checks passed
    }

    // Find the next empty spot (0) on the board, return null if none found
    public static Point getNext(int x, int y, int board[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) return new Point(i, j);
            }
        }
        return null;  // No empty spot found
    }

    // Solve the Sudoku puzzle using backtracking
    public static boolean solve(int x, int y, int move, int board[][]) {
        Point next = getNext(x, y, board);  // Find the next empty cell

        // If there is an empty cell
        if (next != null) {
            // Try placing numbers 1-9 in the empty cell
            for (int i = 1; i <= 9; i++) {
                if (isMoveValid(next.x, next.y, i, board)) {
                    board[next.x][next.y] = i;  // Place the number
                    if (solve(next.x, next.y, i, board)) {
                        return true;  // Puzzle solved
                    }
                    board[next.x][next.y] = 0;  // Backtrack if not solvable
                }
            }
        } else {
            return true;  // No empty spots left, puzzle is solved
        }
        return false;  // No solution found
    }

    // Solve the entire board
    public static int[][] solve(int[][] board) {
        int[][] solvedBoard = new int[9][9];  // Temporary array for solving
        solvedBoard[0][0] = -1;  // Used to identify if solution is found

        // Try solving the puzzle by calling the solve method
        if (solve(0, 0, 1, board)) {
            return board;  // Return solved board
        }

        // Return an invalid board if no solution
        return solvedBoard;
    }
}
