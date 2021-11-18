package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.SmallWeaponFactory;

/**
 * This class defines the arsenal of weapons that a player will have in a Small game.
 *
 * @see Arsenal
 */
public class SmallArsenal extends Arsenal {

    /**
     * Class constructor that passes a <code>SmallWeaponFactory</code> to the super class.
     */
    public SmallArsenal() {
        super( new SmallWeaponFactory() );
    }
}
