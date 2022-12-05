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
        switch (playerRequest) {
            case "human":
                return new HumanPlayer();
            default:
                return null;
        }
    }
}
