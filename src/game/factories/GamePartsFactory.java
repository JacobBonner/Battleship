package edu.colorado.objectgrind.game.factories;

import edu.colorado.objectgrind.weapons.arsenal.Arsenal;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.grid.Grid;

/**
 * This interface defines the functions for creating the components of a game of battleship.
 *
 * @see Fleet
 * @see Arsenal
 * @see Grid
 */
public interface GamePartsFactory {

    /**
     * Creates a Fleet object.
     *
     * @return <code>Fleet</code>
     */
    Fleet createFleet();

    /**
     * Creates an Arsenal object.
     *
     * @return <code>Arsenal</code>
     */
    Arsenal createArsenal();

    /**
     * Creates a Grid object.
     *
     * @return <code>Grid</code>
     */
    Grid createGrid();
}
