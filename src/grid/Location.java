package edu.colorado.objectgrind.grid;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the individual locations/spaces that will make up a grid.
 *
 * @see LocationStatus
 * @see Ship
 */
public class Location {

    /**
     * Indicates whether or not there is a ship at this location.
     */
    private boolean has_ship;

    /**
     * The status of this location.
     * @see LocationStatus
     */
    private LocationStatus status;

    /**
     * The ship that is at this location.
     */
    private Ship ship;

    /**
     * Class constructor that initializes this location to have no ship, and the status to be HIDDEN.
     */
    public Location() {
        this.has_ship = false;
        this.status = LocationStatus.HIDDEN;
        this.ship = null;
    }

    /**
     * Returns whether or not this location has a ship.
     *
     * @return the current value of has_ship
     * @see #has_ship
     */
    public boolean hasShip() {
        return this.has_ship;
    }

    /**
     * Returns the status of this location.
     *
     * @return the current value of status
     * @see #status
     */
    public LocationStatus getLocationStatus() {
        return this.status;
    }

    /**
     * Sets the status of this location.
     *
     * @param status the status to give to this location
     * @see #status
     */
    public void setLocationStatus(LocationStatus status) {
        this.status = status;
    }

    /**
     * Returns the ship of this location.
     *
     * @return the current value of ship
     * @see #ship
     */
    public Ship getShip() {
        return this.ship;
    }

    /**
     * Sets the ship of this location.
     *
     * @param ship the ship to put at this location
     * @see #ship
     * @see #has_ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
        this.has_ship = true;
    }

    /**
     * Removes the ship from this location.
     * @see #ship
     * @see #has_ship
     */
    public void removeShip() {
        this.ship = null;
        this.has_ship = false;
    }
}
