package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.SmallWeaponFactory;

public class SmallArsenal extends Arsenal {

    /*
        Bomb and SpaceLaser w/ 7x7 uses
        SonarPulse: 1 use ; available after 1 sunk
    */

    // Constructor
    public SmallArsenal() {
        super( new SmallWeaponFactory() );
    }
}
