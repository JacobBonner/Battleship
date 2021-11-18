package edu.colorado.objectgrind.factories;

import edu.colorado.objectgrind.game.*;
import edu.colorado.objectgrind.game.factories.GameFactory;
import edu.colorado.objectgrind.grid.LargeGrid;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.grid.SmallGrid;
import edu.colorado.objectgrind.enums.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the GameFactory class.
 *
 * @see GameFactory
 * @see Game
 */
public class GameFactoryTest {

    /**
     * A factory that will create a game.
     */
    GameFactory factory;

    /**
     * The game that this test class uses.
     */
    Game game;

    /**
     * Creates a new factory before every other test.
     */
    @BeforeEach
    public void setUp() {
        factory = new GameFactory();
    }

    /**
     * Checks that a SmallGrid can be created.
     */
    @Test
    public void canCreateGameWithSmallGrid() {
        game = factory.createGame(Size.SMALL);

        // Check the size of the grid for both players
        assertTrue( game.getPlayer_1().getGrid() instanceof SmallGrid );
        assertTrue( game.getPlayer_2().getGrid() instanceof SmallGrid );
    }

    /**
     * Checks that a MediumGrid can be created.
     */
    @Test
    public void canCreateGameWithMediumGrid() {
        game = factory.createGame(Size.MEDIUM);

        // Check the size of the grid for both players
        assertTrue( game.getPlayer_1().getGrid() instanceof MediumGrid );
        assertTrue( game.getPlayer_2().getGrid() instanceof MediumGrid );
    }

    /**
     * Checks that a LargeGrid can be created.
     */
    @Test
    public void canCreateGameWithLargeGrid() {
        game = factory.createGame(Size.LARGE);

        // Check the size of the grid for both players
        assertTrue( game.getPlayer_1().getGrid() instanceof LargeGrid );
        assertTrue( game.getPlayer_2().getGrid() instanceof LargeGrid );
    }
}
