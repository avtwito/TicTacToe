
enum Mark {
    BLANK {
        @Override
        public String toString() {
            return "BLANK";
        }
    },
    X {
        @Override
        public String toString() {
            return "X";
        }
    },
    O {
        @Override
        public String toString() {
            return "O";
        }
    }
}

enum Winner {
    X_WIN {
        @Override
        public String toString() {
            return "X WINS!";
        }
    },
    O_WIN {
        @Override
        public String toString() {
            return "O WINS!";
        }
    },
    DRAW {
        @Override
        public String toString() {
            return "IT'S A DRAW!";
        }
    }
}

public class Board {

    public static final int SIZE = 5;
    public static final int WIN_STREAK = 4;


    private static Mark[][] board;
    private int emptySquares = SIZE * SIZE;
    private boolean didSomebodyWin = false;
    private Winner whoWin;

    //==================Public Methods==================

    /**
     * Constructor
     */
    public Board() {
        board = new Mark[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    /**
     * This method allows users to put a mark in (row, col) coordinate on the board
     * @param mark the mark to put in (row, col)
     * @param row row coordinate
     * @param col col coordinate
     * @return true if update request has completed successfully, false otherwise
     */
    public boolean putMark(Mark mark, int row, int col){
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
            return false;
        if (board[row][col] == Mark.X || board[row][col] == Mark.O)
            return false;
        board[row][col] = mark;
        emptySquares--;
        if (checkWinner(mark, row, col)) {
            didSomebodyWin = true;
            whoWin = getEnumWinner(mark);
        }
        else if (emptySquares == 0) {
            didSomebodyWin = true;
            whoWin = Winner.DRAW;
        }
        return true;
    }

    public boolean checkWinner(Mark mark, int row, int col) {


        //==================== horizontal ====================

        // count left
        int countWins = countMarkInDirection(row, col, 0, 1, mark);

        // count right
        countWins += countMarkInDirection(row, col, 0, -1, mark);

        if (countWins > WIN_STREAK)
            return true;


        //==================== orthogonal ====================

        // count up
        countWins = countMarkInDirection(row, col, -1, 0, mark);

        // count down
        countWins += countMarkInDirection(row, col, 1, 0, mark);

        if (countWins > WIN_STREAK)
            return true;


        // ========== diagonal left to right (down) ==========

        // count left up
        countWins = countMarkInDirection(row, col, -1, -1, mark);

        // count right down
        countWins += countMarkInDirection(row, col, 1, 1, mark);

        if (countWins > WIN_STREAK)
            return true;


        // =========== diagonal right to left (up) ===========


        // count right up
        countWins = countMarkInDirection(row, col, -1, 1, mark);

        // count left down
        countWins += countMarkInDirection(row, col, 1, -1, mark);

        return countWins > WIN_STREAK;
    }

    public boolean checkIfSomebodyWon() {
        return didSomebodyWin;
    }

    public Winner getWhoWin() {
        return whoWin;
    }

    /**
     * A method to read mark at a specific coordinate
     * @param row row coordinate
     * @param col col coordinate
     * @return enum Mark (X, O or BLANK)
     * or null in case coordinate is illegal
     */
    public Mark getMark(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
            return null;
        return board[row][col];
    }


    //==================Private Methods==================
    private Winner getEnumWinner(Mark winner) {
        if (winner == Mark.X)
            return Winner.X_WIN;
        if (winner == Mark.O)
            return Winner.O_WIN;
        return Winner.DRAW;
    }

    private int countMarkInDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
        int count = 0;
        while(row < SIZE && row >= 0 && col < SIZE && col >= 0 && board[row][col] == mark) {
            count++;
            row += rowDelta;
            col += colDelta;
        }
        return count;
    }
}
