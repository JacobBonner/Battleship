package edu.colorado.objectgrind.weapons.attack_patterns;

/**
 * This class defines a horizontal line pattern that a weapon can attack with.
 *
 * @see LinePattern
 */
public class HorizontalLinePattern extends LinePattern {

    /**
     * Class constructor specifying the offset that will be used for the column offset in the super class.
     * <p>
     *     This LinePattern attacks in a horizontal line, so the row offset is zero, i.e. don't change rows during attack.
     * </p>
     *
     * @param offset the offset value that will be used for col_offset in the super class
     */
    public HorizontalLinePattern(int offset) {
        super(0, offset);
    }
}
