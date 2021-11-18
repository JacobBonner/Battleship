package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.LargeWeaponFactory;

/**
 * This class defines the arsenal of weapons that a player will have in a Large game.
 *
 * @see Arsenal
 */
public class LargeArsenal extends Arsenal {

    /**
     * Class constructor that passes a <code>LargeWeaponFactory</code> to the super class.
     */
    public LargeArsenal() {
        super( new LargeWeaponFactory() );
    }
}
