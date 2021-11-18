package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.ships.ship_types.Battleship;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the SurfaceShipPlacement class.
 *
 * @see edu.colorado.objectgrind.ships.placement.SurfaceShipPlacement
 * @see Battleship
 * @see Grid
 */
class SurfaceShipPlacementTest {

    /**
     * The ship that is used for these tests.
     */
    Ship test_ship;

    /**
     * The grid that the ship will be placed on for these tests.
     */
    Grid test_grid;

    /**
     * Initializes the grid and ship before each of the tests.
     */
    @BeforeEach
    void setUp() {
        test_ship = new Battleship();
        test_grid = new MediumGrid(2);
    }

    /**
     * Checks that the ship can be placed properly on layer 1, but not layer 0.
     */
    @Test
    void placeShip() {

        // Place the ship in the third row, and check each location afterwards
        test_ship.performPlacement(test_grid, test_grid.stringCoordToIntCoord("14A"),test_grid.stringCoordToIntCoord("14D"));
        boolean check_ship;
        for(int col = 0; col < 4; col++){
            check_ship = test_grid.getLocationByIndex(1,3,col).hasShip();
            assertTrue(check_ship);
        }

        // Try to place the ship on layer 0, but it should not be there
        test_ship.performPlacement(test_grid, test_grid.stringCoordToIntCoord("04A"),test_grid.stringCoordToIntCoord("04D"));
        for(int col = 0; col < 4; col++){
            check_ship = test_grid.getLocationByIndex(0,3,col).hasShip();
            assertFalse(check_ship);
        }

    }

    /**
     * Checks that an invalid placement of the ship is correctly identified.
     */
    @Test
    public void canDetectInvalidBodyPlacement() {
        Coordinate head = new Coordinate(1, 4,4);
        Coordinate tail = new Coordinate(0, 4,0);
        boolean valid = test_ship.performIsValidPlacement(head, tail);
        assertFalse(valid);
    }

    /**
     * Checks that the ship cannot be placed on layer 0.
     */
    @Test
    void isAtTheRightLayer(){
        boolean check_layer;
        check_layer = test_ship.performIsValidLayer(test_grid.stringCoordToIntCoord("04A"));
        assertFalse(check_layer);
    }

    /**
     * Checks that the ship can be placed where there is no other ship.
     */
    @Test
    void canPlaceShipHere() {
        boolean checkforship;
        checkforship = test_ship.performCanPlaceShip(test_grid, test_grid.stringCoordToIntCoord("04A"), test_grid.stringCoordToIntCoord("04D") );
        assertTrue(checkforship);
    }
}