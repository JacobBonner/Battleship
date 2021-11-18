package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.SingleLocationPattern;

public class Bomb extends DamageWeapon {

    // Constructor
    public Bomb(int num_uses) {
        super(num_uses, new int[] {1} , new SingleLocationPattern(), 0);
    }

    @Override
    public void printWeapon() {
        System.out.print("Bomb");
    }
}
