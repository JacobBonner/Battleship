package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.ships.ship_types.Battleship;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the SurfaceCaptainsQuarters class.
 *
 * @see edu.colorado.objectgrind.ships.captains_quarters.SurfaceCaptainsQuarters
 * @see Battleship
 * @see Grid
 */
class SurfaceCaptainsQuartersTest {

    /**
     * The grid that the submarine will be placed on for these tests.
     */
    Grid my_grid;

    /**
     * The surface ship that is used for these tests.
     */
    Ship test_b_ship;

    /**
     * Initializes the grid and ship before each of the tests.
     */
    @BeforeEach
    void setUp() {
        my_grid = new MediumGrid(2);
        test_b_ship = new Battleship();
    }

    /**
     * Checks that the captain's quarters of the surface ship (battleship) can be found correctly.
     */
    @Test
    void isCaptainsQuarters() {

        //check above water
        boolean check1;
        my_grid.placeShip(test_b_ship,my_grid.stringCoordToIntCoord("12A"),my_grid.stringCoordToIntCoord("12D"));
        check1 = test_b_ship.checkCaptainsQuarter(my_grid,my_grid.stringCoordToIntCoord("12C"));
        assertTrue(check1);
    }

    /**
     * Checks that a location that is not the captain's quarters is correctly identified as not being so.
     */
    @Test
    void isNotCaptainsQuarters(){
        boolean check2;
        my_grid.placeShip(test_b_ship,my_grid.stringCoordToIntCoord("12A"),my_grid.stringCoordToIntCoord("12D"));
        check2 = test_b_ship.checkCaptainsQuarter(my_grid,my_grid.stringCoordToIntCoord("12D"));
        assertFalse(check2);
    }

}