import static java.lang.Math.abs;

/**
 * Game board is responsible for controlling game status.
 */
public class GameBoard {

    private Player currentPlayer;

    private int[][] board;

    public GameBoard() {
        // Set current player
        currentPlayer = Player.A;
        // Setup new board
        board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    }

    /**
     * Changes game board and checks if someone wins. Player A will set tile to 1. Player B will set tile to -1;
     *
     * @param i row index of game board
     * @param j column index of game board
     * @return true if current player wins.
     */
    public boolean move(int i, int j) {
        if (currentPlayer == Player.A) {
            board[i][j] = 1;
        } else {
            board[i][j] = -1;
        }

        return isWin();
    }

    private boolean isWin() {
        // Checks rows and columns
        for (int i = 0; i < 3; i++) {
            int rowSum = 0;
            int columnSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += board[i][j];
                columnSum += board[j][i];
            }
            if (abs(rowSum) == 3 || abs(columnSum) == 3) {
                return true;
            }
        }

        // Checks diagonals
        int diagonalSum = 0;
        int reverseDiagonalSum = 0;
        for (int i = 0; i < 3; i++) {
            diagonalSum += board[i][i];
            reverseDiagonalSum += board[i][2 - i];
        }

        return abs(diagonalSum) == 3 || abs(reverseDiagonalSum) == 3;

    }

    public void switchPlayer() {
        if (currentPlayer == Player.A) {
            currentPlayer = Player.B;
        } else {
            currentPlayer = Player.A;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

enum Player {
    A,
    B
}
