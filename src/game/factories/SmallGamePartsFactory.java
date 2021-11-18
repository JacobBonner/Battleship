package edu.colorado.objectgrind.game.factories;

import edu.colorado.objectgrind.weapons.arsenal.Arsenal;
import edu.colorado.objectgrind.weapons.arsenal.SmallArsenal;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.ships.fleet.SmallFleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.SmallGrid;

/**
 * This class creates the components needed to play a Small game.
 *
 * @see GamePartsFactory
 * @see SmallFleet
 * @see SmallArsenal
 * @see SmallGrid
 */
public class SmallGamePartsFactory implements GamePartsFactory {

    /**
     * Creates a SmallFleet object.
     *
     * @return <code>SmallFleet</code>
     */
    public Fleet createFleet() {
        return new SmallFleet();
    }

    /**
     * Creates a SmallArsenal object.
     *
     * @return <code>SmallArsenal</code>
     */
    public Arsenal createArsenal() {
        return new SmallArsenal();
    }

    /**
     * Creates a SmallGrid object with two layers.
     *
     * @return <code>SmallGrid(2)</code>
     */
    public Grid createGrid() {
        return new SmallGrid(2);
    }
}
