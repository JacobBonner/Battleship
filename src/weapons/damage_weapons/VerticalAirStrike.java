package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.VerticalLinePattern;

/**
 * This class defines the vertical air strike damage weapon, which attacks with a vertical line pattern.
 *
 * @see DamageWeapon
 * @see VerticalLinePattern
 */
public class VerticalAirStrike extends AirStrike {

    /**
     * Class constructor specifying the offset for this class' attack pattern.
     * <p>
     *     This constructor passes a VerticalLinePattern with the given offset to the constructor of its super
     *     class (AirStrike).
     * </p>
     *
     * @param offset the offset that this weapon will use in its attack pattern
     * @see VerticalLinePattern
     */
    public VerticalAirStrike(int offset) {
        super(new VerticalLinePattern(offset));
    }

    /**
     * Prints the string "Vertical Air Strike"
     */
    @Override
    public void printWeapon() {
        System.out.print("Vertical Air Strike");
    }
}
