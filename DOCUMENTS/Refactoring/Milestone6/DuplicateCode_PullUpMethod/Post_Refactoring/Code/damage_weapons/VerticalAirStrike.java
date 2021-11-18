package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.VerticalLinePattern;

public class VerticalAirStrike extends AirStrike {

    // Constructor
    public VerticalAirStrike(int offset) {
        super(new VerticalLinePattern(offset));
    }

    @Override
    public void printWeapon() {
        System.out.print("Vertical Air Strike");
    }
}
