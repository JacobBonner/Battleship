package edu.colorado.objectgrind;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.ships.ship_types.Battleship;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines the tests for the Location class.
 *
 * @see Location
 * @see LocationStatus
 * @see Ship
 */
public class LocationTest {

    /**
     * The Location that is used throughout the tests.
     */
    Location my_location;

    /**
     * Initializes the location before each test.
     */
    @BeforeEach
    public void setUp() {
        my_location = new Location();
    }

    /**
     * Checks that the status of a location can be successfully changed.
     */
    @Test
    public void canChangeLocationStatus() {

        // Initial status should be HIDDEN
        LocationStatus location_status = my_location.getLocationStatus();
        assertEquals(LocationStatus.HIDDEN, location_status);

        // Change the location status to every possible status, and check that it was successful
        for(LocationStatus status: LocationStatus.values()) {
            my_location.setLocationStatus(status);
            location_status = my_location.getLocationStatus();
            assertEquals(status, location_status);
        }
    }

    /**
     * Checks that a location can successfully have a ship given to it.
     */
    @Test
    public void canPlaceShipAtLocation() {
        Ship my_ship = new Battleship();
        my_location.setShip(my_ship);
        Ship get_ship = my_location.getShip();
        assertEquals(my_ship, get_ship);
        assertTrue(my_location.hasShip());
    }

    /**
     * Checks that the ship at a location can be removed from it.
     */
    @Test
    public void canRemoveShip() {
        Ship my_ship = new Battleship();
        my_location.setShip(my_ship);
        my_location.removeShip();
        assertNull(my_location.getShip());
        assertFalse(my_location.hasShip());
    }
}
