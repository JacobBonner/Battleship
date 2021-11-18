package edu.colorado.objectgrind.weapons.arsenal;

import edu.colorado.objectgrind.weapons.factories.MediumWeaponFactory;

/**
 * This class defines the arsenal of weapons that a player will have in a Medium game.
 *
 * @see Arsenal
 */
public class MediumArsenal extends Arsenal {

    /**
     * Class constructor that passes a <code>MediumWeaponFactory</code> to the super class.
     */
    public MediumArsenal() {
        super( new MediumWeaponFactory() );
    }
}
