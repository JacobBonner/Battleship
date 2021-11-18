package edu.colorado.objectgrind;

import edu.colorado.objectgrind.enums.Direction;
import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.ships.fleet.MediumFleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.grid.MediumGrid;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the Grid class.
 *
 * @see Grid
 * @see Fleet
 */
public class GridTest {

    /**
     * The grid that is used throughout the tests.
     */
    Grid my_grid;

    /**
     * The fleet that will be placed on the grid.
     */
    Fleet my_fleet;

    /**
     * Initializes the grid and fleet before each test.
     */
    @BeforeEach
    public void setUp() {
        my_grid = new MediumGrid(2);
        my_fleet = new MediumFleet();
    }

    /**
     * Checks that the grid created in setUp has the correct dimensions and size.
     */
    @Test
    public void canCreateGridOfGivenSize() {

        // Create the grid
        Location[][][] test_grid = new Location[2][10][10];

        // Initialize each location in the grid
        for (int dims = 0; dims < 2; dims++) {
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    test_grid[dims][row][column] = new Location();
                }
            }
        }

        assertEquals(test_grid.length, my_grid.getGrid().length);
        assertEquals(test_grid[0].length, my_grid.getGrid()[0].length);
        assertEquals(test_grid[0][0].length, my_grid.getGrid()[0][0].length);
    }

    /**
     * Checks that an invalid string coordinate format can be successfully detected.
     */
    @Test
    public void canDetectInvalidCoordinateFormat() {
        boolean is_valid_format = my_grid.isValidCoordinateFormat("Hello1234");
        assertFalse(is_valid_format);
    }

    /**
     * Checks that a string coordinate can be successfully converted to a Coordinate object with integers.
     */
    @Test
    public void canConvertStringToCoord() {
        Coordinate coord_converted = my_grid.stringCoordToIntCoord("11A");
        Coordinate coord_created = new Coordinate(1,0,0);
        assertEquals(coord_created.getRow(), coord_converted.getRow());
        assertEquals(coord_created.getColumn(), coord_converted.getColumn());
    }

    /**
     * Checks that an invalid layer can be successfully detected while indexing the grid.
     */
    @Test
    public void canDetectInvalidLayer() {
        // Layer index too high
        Coordinate coord_1 = new Coordinate(3,4,4);
        assertFalse(my_grid.isCoordinateInBounds(coord_1));

        // Layer index too low
        Coordinate coord_2 = new Coordinate(-1,4,4);
        assertFalse(my_grid.isCoordinateInBounds(coord_2));
    }

    /**
     * Checks that an invalid row can be successfully detected while indexing the grid.
     */
    @Test
    public void canDetectInvalidRow() {
        // Row index too high
        Coordinate coord_1 = new Coordinate(1,11,4);
        assertFalse(my_grid.isCoordinateInBounds(coord_1));

        // Row index too low
        Coordinate coord_2 = new Coordinate(1,-1,4);
        assertFalse(my_grid.isCoordinateInBounds(coord_2));
    }

    /**
     * Checks that an invalid column can be successfully detected while indexing the grid.
     */
    @Test
    public void canDetectInvalidColumn() {
        // Column index too high
        Coordinate coord_1 = new Coordinate(1,4,11);
        assertFalse(my_grid.isCoordinateInBounds(coord_1));

        // Column index too low
        Coordinate coord_2 = new Coordinate(1,4,-1);
        assertFalse(my_grid.isCoordinateInBounds(coord_2));
    }


    /**
     * Checks that an invalid ship placement can be successfully detected on this grid.
     */
    @Test
    public void canDetectInvalidShipPlacement() {

        // Get the Grid's Battleship
        Ship to_place = my_fleet.getShipByIndex(0);
        Coordinate head = my_grid.stringCoordToIntCoord("11A");
        Coordinate tail = my_grid.stringCoordToIntCoord("14D");

        // Try to place the ship diagonally
        boolean is_valid = to_place.performIsValidPlacement(head, tail);
        assertFalse(is_valid);
        assertFalse(my_grid.shipCanBePlaced(to_place, "11A", "14D"));

        // Try to put the tail closer to the head than the length of the ship
        head = my_grid.stringCoordToIntCoord("11A");
        tail = my_grid.stringCoordToIntCoord("11B");
        is_valid = to_place.performIsValidPlacement(head, tail);
        assertFalse(is_valid);
        assertFalse(my_grid.shipCanBePlaced(to_place, "11A", "11B"));

        // Try to put the tail above the head
        head = my_grid.stringCoordToIntCoord("16A");
        tail = my_grid.stringCoordToIntCoord("12A");
        is_valid = to_place.performIsValidPlacement(head, tail);
        assertFalse(is_valid);
        assertFalse(my_grid.shipCanBePlaced(to_place, "16A", "12A"));

        // Try to put the tail to the left of the head
        head = my_grid.stringCoordToIntCoord("11H");
        tail = my_grid.stringCoordToIntCoord("11B");
        is_valid = to_place.performIsValidPlacement(head, tail);
        assertFalse(is_valid);
        assertFalse(my_grid.shipCanBePlaced(to_place, "11H", "11B"));
    }

    /**
     * Checks that two ships are prevented from being placed at any of the same locations.
     */
    @Test
    public void canPreventTwoShipsAtSameLocation() {

        // Try to place a ship where there is already a ship
        Coordinate head = my_grid.stringCoordToIntCoord("15D");
        Coordinate tail = my_grid.stringCoordToIntCoord("15G");
        my_grid.placeShip(my_fleet.getShipByIndex(0), head, tail);

        Coordinate new_tail = my_grid.stringCoordToIntCoord("15F");
        boolean can_place = my_fleet.getShipByIndex(1).performCanPlaceShip(my_grid, head, new_tail);
        assertFalse(can_place);
        assertFalse(my_grid.shipCanBePlaced(my_fleet.getShipByIndex(1), "15D", "15F"));
    }

    /**
     * Checks that a ship can successfully be placed on the grid.
     */
    @Test
    public void canPlaceShip() {

        // Place the Grid's Battleship vertically
        Ship to_place = my_fleet.getShipByIndex(0);
        my_grid.placeShip(to_place, new Coordinate(1, 0, 0), new Coordinate(1, 3, 0));
        assertEquals(to_place, my_grid.getLocationByIndex(1,0,0).getShip());
        assertEquals(to_place, my_grid.getLocationByIndex(1,1,0).getShip());
        assertEquals(to_place, my_grid.getLocationByIndex(1, 2,0).getShip());
        assertEquals(to_place, my_grid.getLocationByIndex(1, 3,0).getShip());

        // Place the Grid's Destroyer horizontally
        to_place = my_fleet.getShipByIndex(1);
        my_grid.placeShip(to_place, new Coordinate(1, 6, 3), new Coordinate(1, 6, 5));
        assertEquals(to_place, my_grid.getLocationByIndex(1, 6,3).getShip());
        assertEquals(to_place, my_grid.getLocationByIndex(1, 6,4).getShip());
        assertEquals(to_place, my_grid.getLocationByIndex(1, 6,5).getShip());
    }

    /**
     * Checks that a ship can be successfully removed from the grid.
     */
    @Test
    public void canRemoveShip() {

        // Place the Grid's Battleship vertically
        Ship to_place = my_fleet.getShipByIndex(0);
        my_grid.placeShip(to_place, new Coordinate(1, 0, 0), new Coordinate(1, 3, 0));

        // Remove the ship, then all locations that it was at should not have a ship
        my_grid.removeShip(to_place);
        assertNull(my_grid.getLocationByIndex(1,0,0).getShip());
        assertNull(my_grid.getLocationByIndex(1,1,0).getShip());
        assertNull(my_grid.getLocationByIndex(1, 2,0).getShip());
        assertNull(my_grid.getLocationByIndex(1, 3,0).getShip());
        assertFalse(my_grid.getLocationByIndex(1,0,0).hasShip());
        assertFalse(my_grid.getLocationByIndex(1,1,0).hasShip());
        assertFalse(my_grid.getLocationByIndex(1, 2,0).hasShip());
        assertFalse(my_grid.getLocationByIndex(1, 3,0).hasShip());
    }

    /**
     * Checks that a fleet is successfully determined able to move in every direction.
     */
    @Test
    public void canDetermineIfFleetCanMove() {

        // Set up the ships such that the fleet CAN move in EVERY direction
        my_grid.placeShip(my_fleet.getShipByIndex(0), new Coordinate(1, 2, 5), new Coordinate(1, 2, 8));
        my_grid.placeShip(my_fleet.getShipByIndex(1), new Coordinate(1, 6, 7), new Coordinate(1, 8, 7));
        my_grid.placeShip(my_fleet.getShipByIndex(2), new Coordinate(1, 1, 2), new Coordinate(1, 2, 2));
        my_grid.placeShip(my_fleet.getShipByIndex(3), new Coordinate(1, 5, 2), new Coordinate(1, 5, 5));

        // Check that the fleet can move in every direction
        for ( Direction dir : Direction.values() ) {
            boolean can_move = my_grid.canMoveFleet(dir);
            assertTrue(can_move);
        }
    }

    /**
     * Checks that a fleet is successfully determined able to NOT move in any direction.
     */
    @Test
    public void canDetermineIfFleetCannotMove() {

        // Set up the ships such that the fleet CANNOT move in ANY direction
        my_grid.placeShip(my_fleet.getShipByIndex(0), new Coordinate(1, 1, 6), new Coordinate(1, 1, 9));
        my_grid.placeShip(my_fleet.getShipByIndex(1), new Coordinate(1, 7, 7), new Coordinate(1, 9, 7));
        my_grid.placeShip(my_fleet.getShipByIndex(2), new Coordinate(1, 0, 1), new Coordinate(1, 1, 1));
        my_grid.placeShip(my_fleet.getShipByIndex(3), new Coordinate(0, 6, 0), new Coordinate(0, 6, 3));

        // Check that the fleet can move in no direction
        for ( Direction dir : Direction.values() ) {
            boolean can_move = my_grid.canMoveFleet(dir);
            assertFalse(can_move);
        }
    }

    /**
     * Checks that if a ship was HIT before moving, then the HIT moves with it.
     */
    @Test
    public void canMoveHitStatusWithShip () {

        // Place the ships
        my_grid.placeShip(my_fleet.getShipByIndex(0), new Coordinate(1, 2, 5), new Coordinate(1, 2, 8));
        my_grid.placeShip(my_fleet.getShipByIndex(1), new Coordinate(1, 6, 7), new Coordinate(1, 8, 7));
        my_grid.placeShip(my_fleet.getShipByIndex(2), new Coordinate(1, 1, 2), new Coordinate(1, 2, 2));
        my_grid.placeShip(my_fleet.getShipByIndex(3), new Coordinate(1, 5, 2), new Coordinate(1, 5, 5));

        // Set one location of a ship to HIT
        my_grid.getLocationByIndex(1,2,5).setLocationStatus(LocationStatus.HIT);

        // Move the fleet north
        my_grid.moveFleet(Direction.NORTH);

        assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 1, 5).getShip());
        assertEquals(LocationStatus.HIT, my_grid.getLocationByIndex(1, 1, 5).getLocationStatus());
        assertEquals(LocationStatus.HIDDEN, my_grid.getLocationByIndex(1, 2, 5).getLocationStatus());

        // Move the fleet East
        my_grid.moveFleet(Direction.EAST);
        assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 1, 6).getShip());
        assertEquals(LocationStatus.HIT, my_grid.getLocationByIndex(1, 1, 6).getLocationStatus());
        assertEquals(LocationStatus.HIDDEN, my_grid.getLocationByIndex(1, 1, 5).getLocationStatus());
    }

    /**
     * Checks that a fleet can be successfully moved in every direction on the grid.
     */
    @Test
    public void canMoveFleetEveryDirection() {

        // Set the offsets for where we want to move the fleet
        int row_offset;
        int col_offset;

        for ( Direction direction : Direction.values() ) {

            // Remove all ships
            for (int i=0; i<4; i++) {
                my_grid.removeShip(my_fleet.getShipByIndex(i));
            }

            // Place the ships
            my_grid.placeShip(my_fleet.getShipByIndex(0), new Coordinate(1, 2, 5), new Coordinate(1, 2, 8));
            my_grid.placeShip(my_fleet.getShipByIndex(1), new Coordinate(1, 6, 7), new Coordinate(1, 8, 7));
            my_grid.placeShip(my_fleet.getShipByIndex(2), new Coordinate(1, 1, 2), new Coordinate(1, 2, 2));
            my_grid.placeShip(my_fleet.getShipByIndex(3), new Coordinate(1, 5, 2), new Coordinate(1, 5, 5));

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
            my_grid.moveFleet(direction);

            // Check the location of the Battleship
            assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 2 + row_offset, 5 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 2 + row_offset, 6 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 2 + row_offset, 7 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(0), my_grid.getLocationByIndex(1, 2 + row_offset, 8 + col_offset).getShip());

            // Check the location of the Destroyer
            assertEquals(my_fleet.getShipByIndex(1), my_grid.getLocationByIndex(1, 6 + row_offset, 7 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(1), my_grid.getLocationByIndex(1, 7 + row_offset, 7 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(1), my_grid.getLocationByIndex(1, 8 + row_offset, 7 + col_offset).getShip());

            // Check the location of the Minesweeper
            assertEquals(my_fleet.getShipByIndex(2), my_grid.getLocationByIndex(1, 1 + row_offset, 2 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(2), my_grid.getLocationByIndex(1, 2 + row_offset, 2 + col_offset).getShip());

            // Check the location of the Submarine
            assertEquals(my_fleet.getShipByIndex(3), my_grid.getLocationByIndex(1, 4 + row_offset, 4 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(3), my_grid.getLocationByIndex(1, 5 + row_offset, 2 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(3), my_grid.getLocationByIndex(1, 5 + row_offset, 3 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(3), my_grid.getLocationByIndex(1, 5 + row_offset, 4 + col_offset).getShip());
            assertEquals(my_fleet.getShipByIndex(3), my_grid.getLocationByIndex(1, 5 + row_offset, 5 + col_offset).getShip());
        }
    }
}
