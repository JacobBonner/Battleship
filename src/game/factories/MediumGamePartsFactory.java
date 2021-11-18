package edu.colorado.objectgrind.game.factories;

import edu.colorado.objectgrind.weapons.arsenal.Arsenal;
import edu.colorado.objectgrind.weapons.arsenal.MediumArsenal;
import edu.colorado.objectgrind.ships.fleet.Fleet;
import edu.colorado.objectgrind.ships.fleet.MediumFleet;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.MediumGrid;

/**
 * This class creates the components needed to play a Medium game.
 *
 * @see GamePartsFactory
 * @see MediumFleet
 * @see MediumArsenal
 * @see MediumGrid
 */
public class MediumGamePartsFactory implements GamePartsFactory {

    /**
     * Creates a MediumFleet object.
     *
     * @return <code>MediumFleet</code>
     */
    public Fleet createFleet() {
        return new MediumFleet();
    }

    /**
     * Creates a MediumArsenal object.
     *
     * @return <code>MediumArsenal</code>
     */
    public Arsenal createArsenal() {
        return new MediumArsenal();
    }

    /**
     * Creates a MediumGrid object with two layers.
     *
     * @return <code>MediumGrid(2)</code>
     */
    public Grid createGrid() {
        return new MediumGrid(2);
    }
}
