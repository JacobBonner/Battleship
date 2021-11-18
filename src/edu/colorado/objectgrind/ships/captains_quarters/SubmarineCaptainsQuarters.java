package edu.colorado.objectgrind.ships.captains_quarters;

/**
 * This defines the captain's quarters behavior for the submarine.
 *
 * @see CaptainsQuarters
 * @see edu.colorado.objectgrind.ships.ship_types.Submarine
 */
public class SubmarineCaptainsQuarters extends CaptainsQuarters {

    /**
     * Class constructor that passes set values to the super class for each parameter in the super class' constructor.
     * <p>
     *     For a submarine, a location is a captain's quarters vertically if the location one row up is the same
     *     ship, and if both the locations two rows up and one row down are not the same ship. This is why the
     *     constructor passes {-1} for same_ship_rows and {-2, 1} for not_ship_rows to the super class's constructor.
     * </p>
     * <p>
     *     For a submarine, a location is a captain's quarters horizontally if the location one column to the left
     *     is the same ship, and if the location one column to the right is not the same ship. This is why the
     *     constructor passes {-1} for same_ship_cols and {1} for not_ship_cols to the super class's constructor.
     * </p>
     */
    public SubmarineCaptainsQuarters() {
        super( new int[] {-1}, new int[] {-2, 1}, new int[] {-1}, new int[] {1} );
    }
}
