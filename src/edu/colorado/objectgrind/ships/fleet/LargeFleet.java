package edu.colorado.objectgrind.ships.fleet;

/**
 * This class defines the fleet that a player will have for a Large game of battleship.
 *
 * @see Fleet
 */
public class LargeFleet extends Fleet {

    /**
     * Class constructor that passes the array of ship names to the super class that will be in this fleet.
     */
    public LargeFleet() {
        super( new String[] { "Minesweeper", "Minesweeper", "Destroyer", "Destroyer", "Battleship", "Submarine", "Submarine" } );
    }
}
