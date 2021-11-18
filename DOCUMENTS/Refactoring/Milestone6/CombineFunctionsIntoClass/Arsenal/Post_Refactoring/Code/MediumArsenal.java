package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.MediumWeaponFactory;

public class MediumArsenal extends Arsenal {

    /*
        Bomb and Space Laser w/ 10x10 uses
        SonarPulse: 2 uses ; available after one ship sunk
        AirStrike: 1 use, offset of 2, either horizontal or vertical ; avilable after 2 sunk
     */

    // Constructor
    public MediumArsenal() {
        super( new MediumWeaponFactory() );
    }
}
