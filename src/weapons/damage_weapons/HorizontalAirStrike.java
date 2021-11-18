package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.HorizontalLinePattern;

/**
 * This class defines the horizontal air strike damage weapon, which attacks with a horizontal line pattern.
 *
 * @see DamageWeapon
 * @see HorizontalLinePattern
 */
public class HorizontalAirStrike extends AirStrike {

    /**
     * Class constructor specifying the offset for this class' attack pattern.
     * <p>
     *     This constructor passes a HorizontalLinePattern with the given offset to the constructor of its super
     *      class (AirStrike).
     * </p>
     *
     * @param offset the offset that this weapon will use in its attack pattern
     * @see HorizontalLinePattern
     */
    public HorizontalAirStrike(int offset) {
        super(new HorizontalLinePattern(offset));
    }

    /**
     * Prints the string "Horizontal Air Strike"
     */
    @Override
    public void printWeapon() {
        System.out.print("Horizontal Air Strike");
    }
}
