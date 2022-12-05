public class Tournament {

    public static void main(String[] args) {
        HumanPlayer playerX = new HumanPlayer();
        HumanPlayer playerY = new HumanPlayer();
        ConsoleRenderer renderer = new ConsoleRenderer();
        Game game = new Game(playerX, playerY, renderer);
        System.out.println(game.run().toString());
    }
}
