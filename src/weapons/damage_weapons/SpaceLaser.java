package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.SingleLocationPattern;

/**
 * This class defines the space laser damage weapon, which attacks with a single location pattern.
 *
 * @see DamageWeapon
 * @see SingleLocationPattern
 */
public class SpaceLaser extends DamageWeapon {

    /**
     * Class constructor specifying the number of uses that the weapon will have.
     * <p>
     *     Along with the parameter of this constructor, this constructor passes the following to the constructor of
     *     its super class (DamageWeapon): valid layers of 0 and 1, an attack pattern of SingleLocationPattern,
     *     and a number of sinks needed of 1.
     * </p>
     *
     * @param num_uses the number of uses this weapon will have
     * @see SingleLocationPattern
     */
    public SpaceLaser(int num_uses) {
        super(num_uses, new int[] {0,1}, new SingleLocationPattern(), 1);
    }

    /**
     * Prints the string "Space Laser"
     */
    @Override
    public void printWeapon() {
        System.out.print("Space Laser");
    }
}

