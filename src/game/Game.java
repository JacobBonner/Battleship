package edu.colorado.objectgrind.game;

import edu.colorado.objectgrind.game.factories.GamePartsFactory;

/**
 * This class defines the highest level of the battleship game.
 * <p>
 *     It contains two players who can each take a turn against each other.
 * </p>
 *
 * @see GamePartsFactory
 * @see Player
 */
public class Game {

    /**
     * Player 1 of the game.
     */
    private final Player player_1;

    /**
     * Player 2 of the game.
     */
    private final Player player_2;

    /**
     * Class constructor specifying the GamePartsFactory that will be used to create the parts for this game.
     *
     * @param factory_parts the factory that is needed to create the components of the Game
     * @see GamePartsFactory
     * @see Player
     */
    public Game(GamePartsFactory factory_parts) {

        // Create the two players, passing the game parts factory
        this.player_1 = new Player(factory_parts);
        this.player_2 = new Player(factory_parts);
    }

    /**
     * Returns player 1 of this game.
     *
     * @return <code>Player</code> object for player 1
     */
    public Player getPlayer_1() {
        return player_1;
    }

    /**
     * Returns player 2 of this game.
     *
     * @return <code>Player</code> object for player 2
     */
    public Player getPlayer_2() {
        return player_2;
    }

    /**
     *  Player 1 takes a turn attacking player 2.
     *
     * @param idx_of_weapon index of the weapon that the player will use
     * @param coordinate coordinate where the player will attack, in string format
     * @throws Exception if the Method used in takeTurn is invalid
     * @see Player#takeTurn(Player, int, String)
     */
    public void player1TakeTurn(int idx_of_weapon, String coordinate) throws Exception {
        this.player_1.takeTurn( this.player_2, idx_of_weapon, coordinate);
    }

    /**
     *  Player 2 takes a turn attacking player 1.
     *
     * @param idx_of_weapon index of the weapon that the player will use
     * @param coordinate coordinate where the player will attack, in string format
     * @throws Exception if the Method used in takeTurn is invalid
     * @see Player#takeTurn(Player, int, String)
     */
    public void player2TakeTurn(int idx_of_weapon, String coordinate) throws Exception {
        this.player_2.takeTurn( this.player_1, idx_of_weapon, coordinate);
    }
}
