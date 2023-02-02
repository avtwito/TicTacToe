import java.util.HashMap;
import java.util.Map;

// TODO: This class assumes X is the clever player, will be fixed later

public class CleverPlayer implements Player {
    private static final Map<Winner, Integer> winnerMap = new HashMap<Winner, Integer>() {{
        put(Winner.X_WIN, 1);
        put(Winner.O_WIN, -1);
        put(Winner.DRAW, 0);
    }};

    @Override
    public void playTurn(Board board, Mark mark) {
        // plays first move
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        Board boardCopy = new Board(board);
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                // Is the spot available?
                // TODO: there's a tiny bug here, it doesn't enter the IF even when mark is blank.
                if (boardCopy.getMark(row, col).equals(Mark.BLANK)) {
                    boardCopy.putMark(mark, row, col);
                    int score = minimax(boardCopy, 0, false, mark);
                    boardCopy.putMark(Mark.BLANK, row, col);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        board.putMark(mark, bestMove[0], bestMove[1]);
    }

    private int minimax(Board board, int depth, boolean isMaximizing, Mark mark) {
        boolean result = board.checkIfSomebodyWon();
        if (result) {
            int score = winnerMap.get(board.getWhoWin());
            return score;
        }
        // maximizing means opponent's POV
        int bestScore;
        if (isMaximizing) { // if it's maximizing, no need to reverse the mark
            bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    // Is the spot available?
                    if (board.getMark(row, col) == Mark.BLANK) {
                        board.putMark(mark, row, col);
                        int score = minimax(board, depth + 1, false, mark);
                        board.putMark(Mark.BLANK, row, col);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        }
        // not maximizing means ai's turn
        else { // it is a maximizing state, need to reverse the mark
            bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    // Is the spot available?
                    if (board.getMark(row, col) == Mark.BLANK) {
                        board.putMark(oppositeMark(mark), row, col);
                        int score = minimax(board, depth + 1, true, oppositeMark(mark));
                        board.putMark(Mark.BLANK, row, col);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;

    }

    private Mark oppositeMark (Mark mark) {
        switch (mark) {
            case O -> {
                return Mark.X;
            }
            case X -> {
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

}
