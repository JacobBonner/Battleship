package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

/**
 * This class defines the air strike damage weapon, which attacks with a line pattern.
 *
 * @see DamageWeapon
 * @see WeaponAttackPattern
 */
public abstract class AirStrike extends DamageWeapon {

    /**
     * Class constructor specifying the attack pattern that the weapon will have.
     * <p>
     *     Along with the parameter of this constructor, it passes the following to the constructor of its super class
     *     (DamageWeapon): a number of uses of 1, a valid layer of 1, and a number of sinks needed of 1.
     * </p>
     *
     * @param attack_pattern the attack pattern that this weapon will use
     */
    public AirStrike(WeaponAttackPattern attack_pattern) {
        super(1, new int[] {1}, attack_pattern, 1);
    }
}
