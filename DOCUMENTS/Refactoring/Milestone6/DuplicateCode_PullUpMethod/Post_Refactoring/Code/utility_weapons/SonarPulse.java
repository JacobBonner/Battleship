package edu.colorado.objectgrind.weapons.utility_weapons;

import edu.colorado.objectgrind.weapons.attack_patterns.DiamondPattern;

public class SonarPulse extends UtilityWeapon {

    // Constructor
    public SonarPulse(int num_uses) {
        super(num_uses, new int[] {1}, new DiamondPattern(), 1);
    }

    @Override
    public void printWeapon() {
        System.out.print("Sonar Pulse");
    }
}
