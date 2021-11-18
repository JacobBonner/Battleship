package edu.colorado.objectgrind.weapons.utility_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.DiamondPattern;

/**
 * This class defines the sonar pulse utility weapon, which attacks with a diamond pattern.
 *
 * @see UtilityWeapon
 * @see DiamondPattern
 */
public class SonarPulse extends UtilityWeapon {

    /**
     * Class constructor specifying the number of uses that the weapon will have.
     * <p>
     *     This constructor passes the following to the constructor of its super class (UtilityWeapon):
     *     a valid layer of 1, an attack pattern of DiamondPattern, and a number of sinks needed of 1.
     * </p>
     *
     * @param num_uses the number of uses this weapon will have
     * @see DiamondPattern
     */
    public SonarPulse(int num_uses) {
        super(num_uses, new int[] {1}, new DiamondPattern(), 1);
    }

    /**
     * Prints the string "Sonar Pulse".
     */
    @Override
    public void printWeapon() {
        System.out.print("Sonar Pulse");
    }
}
