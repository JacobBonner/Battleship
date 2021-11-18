package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.ships.ship_types.Submarine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the SubmarineCaptainsQuarters class.
 *
 * @see edu.colorado.objectgrind.ships.captains_quarters.SubmarineCaptainsQuarters
 * @see Submarine
 * @see Grid
 */
class SubmarineCaptainsQuartersTest {

    /**
     * The grid that the submarine will be placed on for these tests.
     */
    Grid my_grid;

    /**
     * The submarine that is used for these tests.
     */
    Ship test_sub;

    /**
     * Initializes the grid and submarine before each of the tests.
     */
    @BeforeEach
    void setUp() {
        my_grid = new MediumGrid(2);
        test_sub = new Submarine();
    }

    /**
     * Checks that the captain's quarters of the submarine can be found correctly.
     */
    @Test
    void isCaptainsQuarters() {

        // check underwater
        boolean check;
        my_grid.placeShip(test_sub,my_grid.stringCoordToIntCoord("02A"),my_grid.stringCoordToIntCoord("02D"));
        check = test_sub.checkCaptainsQuarter(my_grid,my_grid.stringCoordToIntCoord("02D"));
        assertTrue(check);

        //check above water
        boolean check1;
        my_grid.placeShip(test_sub,my_grid.stringCoordToIntCoord("12A"),my_grid.stringCoordToIntCoord("12D"));
        check1 = test_sub.checkCaptainsQuarter(my_grid,my_grid.stringCoordToIntCoord("12D"));
        assertTrue(check1);

    }

    /**
     * Checks that a location that is not the captain's quarters is correctly identified as not being so.
     */
    @Test
    void isNotCaptainsQuarters(){
        boolean check2;
        my_grid.placeShip(test_sub,my_grid.stringCoordToIntCoord("02A"),my_grid.stringCoordToIntCoord("02D"));
        check2 = test_sub.checkCaptainsQuarter(my_grid,my_grid.stringCoordToIntCoord("02C"));
        assertFalse(check2);
    }

}