import java.util.Scanner;

public class HumanPlayer implements Player {

    public HumanPlayer() {

    }

    /**
     * This method asks for coordinates from the user
     * and calls the relevant methods to play this turn.
     * @param board given current board
     * @param mark current player's mark
     */
    public void playTurn(Board board, Mark mark) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Please type-in a coordinate for %s: ", mark.toString());
        int num = in.nextInt();

        boolean isMarked = (board.putMark(mark, num / 10 - 1, num % 10 - 1));
        while (!isMarked) {
            System.out.print("ERROR! Please type-in a new coordinate: ");
            num = in.nextInt();
            isMarked = (board.putMark(mark, num / 10 - 1, num % 10 - 1));
        }
    }
}
