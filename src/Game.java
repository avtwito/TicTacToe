public class Game {

    private final Player playerX;
    private final Player playerO;
    private final Renderer renderer;

    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
    }

    public Winner run() {
        Board board = new Board();
        Player[] players = {playerX, playerO};
        Mark[] marks = {Mark.X, Mark.O};
        int counter = 0;
        while (!board.checkIfSomebodyWon()) {
            renderer.renderBoard(board);
            players[counter % 2].playTurn(board, marks[counter % 2]);


            counter++;
        }
        // If you reached here - somebody won
        renderer.renderBoard(board);
        return board.getWhoWin();
    }

}