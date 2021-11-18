package edu.colorado.objectgrind.ships;

import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.ships.ship_types.Submarine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the SubmarinePlacement class.
 *
 * @see edu.colorado.objectgrind.ships.placement.SubmarinePlacement
 * @see Submarine
 * @see Grid
 */
class SubmarinePlacementTest {

    /**
     * The submarine that is used for these tests.
     */
    Ship sub_new;

    /**
     * The grid that the submarine will be placed on for these tests.
     */
    Grid test_grid;

    /**
     * Initializes the grid and submarine before each of the tests.
     */
    @BeforeEach
    void setUp() {
        test_grid = new MediumGrid(2);
        sub_new = new Submarine();
    }

    /**
     * Checks that the submarine can be placed horizontally on the grid.
     */
    @Test
    void placeShipHorizontally() {
        sub_new.performPlacement(test_grid, test_grid.stringCoordToIntCoord("14A"),test_grid.stringCoordToIntCoord("14D"));
        boolean check_ship;
        for(int col = 0; col < 4; col++){
            check_ship = test_grid.getLocationByIndex(1,3,col).hasShip();
            assertTrue(check_ship);
        }

        // check for periscope thingy
        boolean check_notch;
        check_notch = test_grid.getLocationByIndex(1,3,2).hasShip();
        assertTrue(check_notch);
    }

    /**
     * Checks that the submarine can be placed vertically on the grid.
     */
    @Test
    public void placeShipVertically() {

        boolean check_ship, check_notch;

        // Place the submarine in the first column, and check that it is at each location afterwards
        sub_new.performPlacement(test_grid, test_grid.stringCoordToIntCoord("04A"),test_grid.stringCoordToIntCoord("07A"));
        for(int row = 3; row < 7; row++){
            check_ship = test_grid.getLocationByIndex(0,row,0).hasShip();
            assertTrue(check_ship);
        }

        // Assert that the topside of the submarine is where it should be
        check_notch = test_grid.getLocationByIndex(0,5,1).hasShip();
        assertTrue(check_notch);
    }

    /**
     * Checks that a topside of a submarine out of bounds horizontally can be detected while placing the submarine.
     */
    @Test
    public void canDetectHorizontalTopSideOutOfBounds() {

        // Try to place a Submarine in the top row
        boolean can_place = sub_new.performCanPlaceShip(test_grid, test_grid.stringCoordToIntCoord("11A"),test_grid.stringCoordToIntCoord("11D"));

        // Should be false, and submarine not placed
        assertFalse( can_place );
        assertFalse( sub_new.isShipPlaced() );
    }

    /**
     * Checks that a topside of a submarine out of bounds horizontally can be detected while placing the submarine.
     */
    @Test
    public void canDetectVerticalTopSideOutOfBounds() {

        // Try to place a Submarine in furthest right column
        boolean can_place = sub_new.performCanPlaceShip(test_grid, test_grid.stringCoordToIntCoord("11J"),test_grid.stringCoordToIntCoord("14J"));

        // Should be false, and submarine not placed
        assertFalse( can_place );
        assertFalse( sub_new.isShipPlaced() );
    }

    /**
     * Checks that the submarine can be placed where there is no other ship.
     */
    @Test
    void canPlaceShipHere() {
        boolean check_for_ship;
        check_for_ship = sub_new.performCanPlaceShip(test_grid, test_grid.stringCoordToIntCoord("14A"), test_grid.stringCoordToIntCoord("14D") );
        assertTrue(check_for_ship);
    }

}