package edu.colorado.objectgrind.game.factories;

import edu.colorado.objectgrind.weapons.arsenal.Arsenal;
import edu.colorado.objectgrind.weapons.arsenal.LargeArsenal;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.ships.fleet.LargeFleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.LargeGrid;

/**
 * This class creates the components needed to play a Large game.
 *
 * @see GamePartsFactory
 * @see LargeFleet
 * @see LargeArsenal
 * @see LargeGrid
 */
public class LargeGamePartsFactory implements GamePartsFactory {

    /**
     * Creates a LargeFleet object.
     *
     * @return <code>LargeFleet</code>
     */
    public Fleet createFleet() {
        return new LargeFleet();
    }

    /**
     * Creates a LargeArsenal object.
     *
     * @return <code>LargeArsenal</code>
     */
    public Arsenal createArsenal() {
        return new LargeArsenal();
    }

    /**
     * Creates a LargeGrid object with two layers.
     *
     * @return <code>LargeGrid(2)</code>
     */
    public Grid createGrid() {
        return new LargeGrid(2);
    }
}
