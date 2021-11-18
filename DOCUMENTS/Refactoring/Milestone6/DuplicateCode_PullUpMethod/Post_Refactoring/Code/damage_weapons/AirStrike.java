package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

public abstract class AirStrike extends DamageWeapon {

    // Constructor
    public AirStrike(WeaponAttackPattern attack_pattern) {
        super(1, new int[] {1}, attack_pattern, 1);
    }
}
