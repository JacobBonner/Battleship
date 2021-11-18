package edu.colorado.objectgrind.game.factories;

import edu.colorado.objectgrind.enums.Size;
import edu.colorado.objectgrind.game.Game;

/**
 * This class creates a Game with a GamePartsFactory corresponding to the given Size, i.e. Small, Medium, or Large Game.
 *
 * @see Game
 */
public class GameFactory {

    /**
     * Creates a Game object with a GamePartsFactory corresponding to the given size.
     *
     * @param size The size of the game that is to be created.
     * @return A <code>Game</code> with a corresponding <code>GamePartsFactory</code> depending on the size given.
     * @see Game
     * @see GamePartsFactory
     * @see SmallGamePartsFactory
     * @see MediumGamePartsFactory
     * @see LargeGamePartsFactory
     */
    public Game createGame(Size size) {

        GamePartsFactory factory_parts = null;

        switch (size) {
            case SMALL:
                factory_parts = new SmallGamePartsFactory();
                break;

            case MEDIUM:
                factory_parts = new MediumGamePartsFactory();
                break;

            case LARGE:
                factory_parts = new LargeGamePartsFactory();
                break;
        }

        return new Game(factory_parts);
    }
}
