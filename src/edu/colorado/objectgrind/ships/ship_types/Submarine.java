package edu.colorado.objectgrind.ships.ship_types;

import edu.colorado.objectgrind.ships.captains_quarters.SubmarineCaptainsQuarters;
import edu.colorado.objectgrind.ships.placement.SubmarinePlacement;

/**
 * This class defines a Submarine ship.
 *
 * @see Ship
 */
public class Submarine extends Ship {

    /**
     * Class constructor that passes set values to the super class constructor for every attribute.
     * <p>
     *     Passes a health of 5, a length of 4, a captain quarter's health of 1, a placement behavior of SubmarinePlacement,
     *     and a captain's quarters of SubmarineCaptainsQuarters.
     * </p>
     */
    public Submarine() {
        super(5, 4, 1, new SubmarinePlacement(), new SubmarineCaptainsQuarters());
    }

    /**
     *  Prints the word "Submarine"
     */
    @Override
    public String printShip() {
        return "Submarine";
    }
}
