/**
 * Class commits a series of TicTacToe matches between two given players
 * with a given renderer, as between the matches the players switch roles.
 * At the end of every match, the current score will be printed
 */
public class Tournament {

    private final int rounds;
    private final Renderer renderer;
    private final Player[] players;

    public Tournament(int rounds, Renderer renderer,
                      Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = new Player[]{player1, player2};
    }

    public int[] playTournament() {
        Game match;
        int[] winArray = {0, 0, 0};
        Winner winner;
        for (int i = 0; i < rounds; i++) {
            match = new Game(players[i % 2], players[(i + 1) % 2], renderer);
            winner = match.run();
            switch (winner) {
                case X_WIN -> winArray[i % 2]++;
                case O_WIN -> winArray[(i + 1) % 2]++;
                default -> winArray[2]++;
            }
        }
        return winArray;
    }


    public static void main(String[] args) {
        if (!(isArgsValid(args))) {
            System.out.println("please follow pattern:");
            System.out.println("java Tournament [round count] [render target: console/none] [player 1: human/clever/whatever] [player 2: human/clever/whatever]");
            System.out.println("for example: java Tournament 10000 none clever whatever");
            return;
        }
        int rounds = Integer.parseInt(args[0]);
        String rendererType = args[1];
        String player1Type = args[2];
        String player2Type = args[3];
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();

        Renderer renderer = rendererFactory.buildFactory(rendererType);
        Player player1 = playerFactory.buildPlayer(player1Type);
        Player player2 = playerFactory.buildPlayer(player2Type);
        if (renderer == null || player1 == null || player2 == null) {
            System.out.println("Not supported yet");
            return;
        }

        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        int[] score = tournament.playTournament();
        System.out.printf("X wins: %d. O wins: %d. Draws: %d%n", score[0], score[1], score[2]);
    }

    private static boolean isArgsValid(String[] args) {
        // TODO: optimize shitty code
        // or add tests for god's sake
        if (Integer.parseInt(args[0]) < 1)
            return false;
        if (!(args[1].equals("console") || args[1].equals("none")))
            return false;
        if (!(args[2].equals("human") || args[2].equals("whatever") || args[2].equals("clever")))
            return false;
        if (!(args[3].equals("human") || args[3].equals("whatever") || args[3].equals("clever")))
            return false;
        return true;
    }
}
