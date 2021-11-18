package edu.colorado.objectgrind.weapons.utility_weapons;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

/**
 * This class defines a weapon that is used to reveal something about a location,
 * <p>
 *     An attack with a utility weapon results in either FREE or OCCUPIED.
 * </p>
 *
 * @see Weapon
 * @see WeaponAttackPattern
 */
public abstract class UtilityWeapon extends Weapon {

    /**
     * Class constructor specifying values for all of this weapon's attributes.
     * <p>
     *     This constructor takes all of the parameters and calls the constructor of its super class - Weapon.
     * </p>
     *
     * @param uses the number of uses this weapon will have
     * @param valid_layers the layers that will be valid for using this weapon
     * @param attack_pattern the attack pattern that this weapon will have and use
     * @param sinks_needed the number of ships a player must sink in order to use this weapon
     */
    public UtilityWeapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        super(uses, valid_layers, attack_pattern, sinks_needed);
    }

    /**
     * Attacks the given location and results in a FREE or OCCUPIED.
     *
     * @param attacked_grid the grid that will be attacked by this weapon
     * @param coord_of_attack the coordinate that this weapon will be attacking
     * @see LocationStatus
     */
    @Override
    public void attackLocation(Grid attacked_grid, Coordinate coord_of_attack) {

        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();
        int layer = coord_of_attack.getLayer();
        Location loc_of_attack = attacked_grid.getLocationByIndex(layer, row, col);

        // Set the status according to whether or not there is a ship at the current location
        if ( loc_of_attack.hasShip() ) loc_of_attack.setLocationStatus(LocationStatus.OCCUPIED);
        else loc_of_attack.setLocationStatus(LocationStatus.FREE);
    }
}
