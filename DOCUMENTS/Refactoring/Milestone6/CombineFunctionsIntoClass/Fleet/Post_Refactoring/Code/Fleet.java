package edu.colorado.objectgrind.ships.fleet;

import edu.colorado.objectgrind.factories.ShipFactory;
import edu.colorado.objectgrind.ships.ship_types.Ship;

public abstract class Fleet {

    protected Ship[] ships;
    protected ShipFactory ship_factory;

    // Constructor
    public Fleet(String[] ship_names) {
        this.ship_factory = new ShipFactory();

        // Initialize the ship array
        int num_ships = ship_names.length;
        this.ships = new Ship[ num_ships ];

        // For each ship name, create the ship
        for( int i=0; i<num_ships; i++ ) {
            this.ships[i] = this.ship_factory.createShip(ship_names[i]);
        }
    }

    // Gets a ship by index
    public Ship getShipByIndex(int index) {
        return this.ships[index];
    }

    // Gets the size of the fleet, i.e. total number of ships
    public int getSize() {
        return this.ships.length;
    }

    // Gets the number of ships that have sunk
    public int getNumShipsSunk() {
        int num = 0;
        for (Ship ship : this.ships) {
            if (ship.hasSunk()) num++;
        }
        return num;
    }

    // Determines if all of the ships have been sunk
    public boolean allShipsSunk() {

        // If any of the ships has not sunk, then return false
        for (Ship ship : this.ships) {
            if (!ship.hasSunk()) return false;
        }
        return true;
    }

    // Determines if all of the ships have been placed
    public boolean allShipsPlaced() {

        boolean all_ships_placed = true;

        for ( Ship ship : this.ships ) {
            if ( !ship.isShipPlaced() ) {
                all_ships_placed = false;
                break;
            }
        }

        return all_ships_placed;
    }
}
