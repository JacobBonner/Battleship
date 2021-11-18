package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.LargeWeaponFactory;

public class LargeArsenal extends Arsenal {

    /*
        Bomb and Space Laser w/ 13x13 uses
        SonarPulse: 1 use ; available after 1 sunk
        AirStrike: 1 use, offset of 3, either horizontal or vertical ; available after 2 sunk
        NOT YET DECIDED: ScatterShot: 1 use, diamond pattern ; available after 3 sunk
     */

    // Constructor
    public LargeArsenal() {
        super( new LargeWeaponFactory() );
    }
}
