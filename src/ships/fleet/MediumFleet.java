package edu.colorado.objectgrind.ships.fleet;

/**
 * This class defines the fleet that a player will have for a Medium game of battleship.
 *
 * @see Fleet
 */
public class MediumFleet extends Fleet {

    /**
     * Class constructor that passes the array of ship names to the super class that will be in this fleet.
     */
    public MediumFleet() {
        super( new String[]{ "Battleship", "Destroyer", "Minesweeper", "Submarine" } );
    }
}
