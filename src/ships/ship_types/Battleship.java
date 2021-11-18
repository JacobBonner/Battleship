package edu.colorado.objectgrind.ships.ship_types;

import edu.colorado.objectgrind.ships.captains_quarters.SurfaceCaptainsQuarters;
import edu.colorado.objectgrind.ships.placement.SurfaceShipPlacement;

/**
 * This class defines a Battleship ship.
 *
 * @see Ship
 */
public class Battleship extends Ship {

    /**
     * Class constructor that passes set values to the super class constructor for every attribute.
     * <p>
     *     Passes a health and length of 4, a captain quarter's health of 2, a placement behavior of SurfaceShipPlacement,
     *     and a captain's quarters of SurfaceCaptainsQuarters.
     * </p>
     */
    public Battleship() {
        super(4, 4, 2, new SurfaceShipPlacement(), new SurfaceCaptainsQuarters());
    }

    /**
     *  Prints the word "Battleship"
     */
    @Override
    public String printShip() {
        return "Battleship";
    }
}
