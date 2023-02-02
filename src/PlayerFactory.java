public class PlayerFactory {
    public PlayerFactory() {

    }

    /**
     * This method gets a string from the command line
     * with types of players to build,
     * and returns the players accordingly
     * @param playerRequest "clever"/"whatever"/"human"
     * @return Player CleverPlayer/WhateverPlayer/HumanPlayer
     */
    public Player buildPlayer(String playerRequest) {
        return switch (playerRequest) {
            case "human" -> new HumanPlayer();
            case "whatever" -> new WhateverPlayer();
            case "clever" -> new CleverPlayer();
            default -> null;
        };
    }
}
