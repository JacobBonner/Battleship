package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.SingleLocationPattern;

public class SpaceLaser extends DamageWeapon {

    // Constructor
    public SpaceLaser(int num_uses) {
        super(num_uses, new int[] {0,1}, new SingleLocationPattern(), 1);
    }

    @Override
    public void printWeapon() {
        System.out.print("Space Laser");
    }
}

