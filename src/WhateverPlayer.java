import java.util.Random;

public class WhateverPlayer implements Player {
    @Override
    public void playTurn(Board board, Mark mark) {
        Random cordCreator = new Random();
        int row = cordCreator.nextInt(Board.SIZE);
        int col = cordCreator.nextInt(Board.SIZE);
        while (board.getMark(row, col) != Mark.BLANK) {
            row = cordCreator.nextInt(Board.SIZE);
            col = cordCreator.nextInt(Board.SIZE);
        }
        board.putMark(mark, row, col);
    }
}
