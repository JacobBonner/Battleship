package edu.colorado.objectgrind.weapons.attack_patterns;

/**
 * This class defines a vertical line pattern that a weapon can attack with.
 *
 * @see LinePattern
 */
public class VerticalLinePattern extends LinePattern {

    /**
     * Class constructor specifying the offset that will be used for the row offset in the super class.
     * <p>
     *     This LinePattern attacks in a vertical line, so the column offset is zero, i.e. don't change columns
     *     during attack.
     * </p>
     *
     * @param offset the offset value that will be used for row_offset in the super class
     */
    public VerticalLinePattern(int offset) {
        super(offset, 0);
    }
}
