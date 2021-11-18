package edu.colorado.objectgrind.ships.ship_types;

import edu.colorado.objectgrind.ships.captains_quarters.SurfaceCaptainsQuarters;
import edu.colorado.objectgrind.ships.placement.SurfaceShipPlacement;

/**
 * This class defines a Minesweeper ship.
 *
 * @see Ship
 */
public class Minesweeper extends Ship {

    /**
     * Class constructor that passes set values to the super class constructor for every attribute.
     * <p>
     *     Passes a health and length of 4, a captain quarter's health of 1, a placement behavior of SurfaceShipPlacement,
     *     and a captain's quarters of SurfaceCaptainsQuarters.
     * </p>
     */
    public Minesweeper() {
        super(2, 2, 1, new SurfaceShipPlacement(), new SurfaceCaptainsQuarters());
    }

    /**
     *  Prints the word "Minesweeper"
     */
    @Override
    public String printShip() {
        return "Minesweeper";
    }
}
