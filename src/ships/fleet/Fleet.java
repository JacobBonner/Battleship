package edu.colorado.objectgrind.ships.fleet;

import edu.colorado.objectgrind.ships.ShipFactory;
import edu.colorado.objectgrind.ships.ship_types.Ship;

/**
 * This class defines the fleet of ships that a player will have for a game of battleship.
 *
 * @see Ship
 * @see ShipFactory
 */
public abstract class Fleet {

    /**
     * The ships that are in this fleet.
     * @see Ship
     */
    private final Ship[] ships;

    /**
     * Class constructor specifying the names of the ships that will be in this fleet.
     *
     * @param ship_names the names of the ships that will be in this fleet
     */
    public Fleet(String[] ship_names) {

        // Set the ship factory
        ShipFactory ship_factory = new ShipFactory();

        // Initialize the ship array
        int num_ships = ship_names.length;
        this.ships = new Ship[ num_ships ];

        // For each ship name, create the ship with the factory
        for( int i=0; i<num_ships; i++ ) {
            this.ships[i] = ship_factory.createShip(ship_names[i]);
        }
    }

    /**
     * Returns the ship in this fleet at the given index.
     *
     * @param index the index of the ship that will be returned
     * @return the <code>Ship</code> at the given index
     * @see #ships
     */
    public Ship getShipByIndex(int index) {
        return this.ships[index];
    }

    /**
     * Gets the size of the fleet, i.e. total number of ships.
     *
     * @return the number of ships in this fleet
     * @see #ships
     */
    public int getSize() {
        return this.ships.length;
    }

    /**
     * Gets the number of ships in this fleet that have sunk.
     *
     * @return a count of all ships in this fleet that have sunk
     * @see #ships
     * @see Ship#hasSunk()
     */
    public int getNumShipsSunk() {
        int num = 0;
        for (Ship ship : this.ships) {
            if (ship.hasSunk()) num++;
        }
        return num;
    }

    /**
     * Determines if all of the ships have been sunk.
     *
     * @return <code>true</code> if all ships in this fleet have sunk; <code>false</code> otherwise
     * @see Ship#hasSunk()
     */
    public boolean allShipsSunk() {

        // If any of the ships has not sunk, then return false
        for (Ship ship : this.ships) {
            if (!ship.hasSunk()) return false;
        }
        return true;
    }

    /**
     * Determines if all of the ships in this fleet have been placed.
     *
     * @return <code>true</code> if all of the ships in this fleet have been placed; <code>false</code> otherwise
     * @see Ship#isShipPlaced()
     */
    public boolean allShipsPlaced() {

        // Initialize to true
        boolean all_ships_placed = true;

        // if any ship has not been placed, then set to false
        for ( Ship ship : this.ships ) {
            if ( !ship.isShipPlaced() ) {
                all_ships_placed = false;
                break;
            }
        }

        return all_ships_placed;
    }
}
