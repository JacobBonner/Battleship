package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.HorizontalLinePattern;

public class HorizontalAirStrike extends AirStrike {

    // Constructor
    public HorizontalAirStrike(int offset) {
        super(new HorizontalLinePattern(offset));
    }

    @Override
    public void printWeapon() {
        System.out.print("Horizontal Air Strike");
    }
}
