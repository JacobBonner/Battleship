package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.SingleLocationPattern;

/**
 * This class defines the bomb damage weapon, which attacks with a single location pattern.
 *
 * @see DamageWeapon
 * @see SingleLocationPattern
 */
public class Bomb extends DamageWeapon {

    /**
     * Class constructor specifying the number of uses that the weapon will have.
     * <p>
     *     Along with the parameter of this constructor, this constructor passes the following to the constructor of
     *     its super class (DamageWeapon): a valid layer of 1, an attack pattern of SingleLocationPattern,
     *     and a number of sinks needed of 0.
     * </p>
     *
     * @param num_uses the number of uses this weapon will have
     * @see SingleLocationPattern
     */
    public Bomb(int num_uses) {
        super(num_uses, new int[] {1} , new SingleLocationPattern(), 0);
    }

    /**
     * Prints the string "Bomb"
     */
    @Override
    public void printWeapon() {
        System.out.print("Bomb");
    }
}
