package edu.colorado.objectgrind.ships.fleet;

/**
 * This class defines the fleet that a player will have for a Small game of battleship.
 *
 * @see Fleet
 */
public class SmallFleet extends Fleet {

    /**
     * Class constructor that passes the array of ship names to the super class that will be in this fleet.
     */
    public SmallFleet() {
        super( new String[]{ "Minesweeper", "Minesweeper", "Destroyer" } );
    }
}
