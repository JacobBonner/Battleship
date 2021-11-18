package edu.colorado.objectgrind.ships.ship_types;

import edu.colorado.objectgrind.ships.captains_quarters.SurfaceCaptainsQuarters;
import edu.colorado.objectgrind.ships.placement.SurfaceShipPlacement;

/**
 * This class defines a Destroyer ship.
 *
 * @see Ship
 */
public class Destroyer extends Ship {

    /**
     * Class constructor that passes set values to the super class constructor for every attribute.
     * <p>
     *     Passes a health and length of 3, a captain quarter's health of 2, a placement behavior of SurfaceShipPlacement,
     *     and a captain's quarters of SurfaceCaptainsQuarters.
     * </p>
     */
    public Destroyer() {
        super(3, 3, 2, new SurfaceShipPlacement(), new SurfaceCaptainsQuarters());
    }

    /**
     *  Prints the word "Destroyer"
     */
    @Override
    public String printShip() {
        return "Destroyer";
    }
}
