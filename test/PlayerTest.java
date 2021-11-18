package edu.colorado.objectgrind;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.game.factories.GamePartsFactory;
import edu.colorado.objectgrind.game.factories.MediumGamePartsFactory;
import edu.colorado.objectgrind.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the Player class.
 *
 * @see Player
 * @see GamePartsFactory
 */
public class PlayerTest {

    /**
     * The player used throughout the tests.
     */
    Player player1;

    /**
     * Initialize the player before each test.
     */
    @BeforeEach
    public void setUp() {
        GamePartsFactory factory = new MediumGamePartsFactory();
        player1 = new Player(factory);
    }

    /**
     * Checks that a player can place and remove a ship from their grid.
     * <p>
     *     The player places a ship on their grid and removes it by calling execute and undo from a PlaceShipCommand,
     *     respectively.
     * </p>
     */
    @Test
    public void canPlaceAndRemoveShipWithCommand() {

        // Place the ship with
        player1.playerPlaceShip(0, "11A", "11D");
        assertTrue(player1.getShipAtIndex(0).isShipPlaced());
        assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1,0,0).getShip());
        assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1,0,1).getShip());
        assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1,0,2).getShip());
        assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1,0,3).getShip());

        // Remove the ship
        player1.playerUndo();
        assertFalse(player1.getShipAtIndex(0).isShipPlaced());
        assertNull(player1.getGrid().getLocationByIndex(1,0,0).getShip());
        assertNull(player1.getGrid().getLocationByIndex(1,0,1).getShip());
        assertNull(player1.getGrid().getLocationByIndex(1,0,2).getShip());
        assertNull(player1.getGrid().getLocationByIndex(1,0,3).getShip());
    }

    /**
     * Checks that a player can successfully determine when all of their ships have sunk.
     */
    @Test
    public void canDetectAllShipsSunk() {
        for ( int i=0; i<player1.getSizeOfFleet(); i++ ) {
            player1.getShipAtIndex(i).sink();
        }

        assertTrue(player1.surrender());
    }

    /**
     * Checks that the player's fleet can successfully be moved in every direction, and back.
     * <p>
     *     The player moves their fleet on their grid and moves it back by calling execute and undo from a
     *     MoveFleetCommand, respectively.
     * </p>
     */
    @Test
    public void canMoveFleetWithCommand() {

        // Place all of the ships
        player1.playerPlaceShip(0, "13F", "13I");
        player1.playerPlaceShip(1, "17H", "19H");
        player1.playerPlaceShip(2, "12C", "13C");
        player1.playerPlaceShip(3, "16C", "16F");

        // Set the offsets for where we want to move the fleet
        int row_offset;
        int col_offset;

        for ( Direction direction : Direction.values() ) {

            row_offset = 0;
            col_offset = 0;

            // Set the offsets based on the direction of movement
            switch (direction) {

                case NORTH:
                    // Move the fleet up one row
                    row_offset = -1;
                    break;

                case EAST:
                    // Move the fleet right one column
                    col_offset = 1;
                    break;

                case SOUTH:
                    // Move the fleet down one row
                    row_offset = 1;
                    break;

                case WEST:
                    // Move the fleet left one column
                    col_offset = -1;
                    break;
            }

            // Move the fleet in the given direction
            player1.playerMoveFleet(direction);

            // Check the battleship
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2+row_offset, 5+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2+row_offset, 6+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2+row_offset, 7+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2+row_offset, 8+col_offset).getShip());

            // Check the destroyer
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 6+row_offset, 7+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 7+row_offset, 7+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 8+row_offset, 7+col_offset).getShip());

            // Check the minesweeper
            assertEquals(player1.getShipAtIndex(2), player1.getGrid().getLocationByIndex(1, 1+row_offset, 2+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(2), player1.getGrid().getLocationByIndex(1, 2+row_offset, 2+col_offset).getShip());

            // Check the submarine
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 4+row_offset, 4+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5+row_offset, 2+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5+row_offset, 3+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5+row_offset, 4+col_offset).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5+row_offset, 5+col_offset).getShip());

            // undo the movement
            player1.playerUndo();

            // Check the battleship
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2, 5).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2, 6).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2, 7).getShip());
            assertEquals(player1.getShipAtIndex(0), player1.getGrid().getLocationByIndex(1, 2, 8).getShip());

            // Check the destroyer
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 6, 7).getShip());
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 7, 7).getShip());
            assertEquals(player1.getShipAtIndex(1), player1.getGrid().getLocationByIndex(1, 8, 7).getShip());

            // Check the minesweeper
            assertEquals(player1.getShipAtIndex(2), player1.getGrid().getLocationByIndex(1, 1, 2).getShip());
            assertEquals(player1.getShipAtIndex(2), player1.getGrid().getLocationByIndex(1, 2, 2).getShip());

            // Check the submarine
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 4, 4).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5, 2).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5, 3).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5, 4).getShip());
            assertEquals(player1.getShipAtIndex(3), player1.getGrid().getLocationByIndex(1, 5, 5).getShip());
        }
    }
}
