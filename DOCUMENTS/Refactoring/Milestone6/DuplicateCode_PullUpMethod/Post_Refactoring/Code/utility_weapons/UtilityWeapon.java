package edu.colorado.objectgrind.weapons.utility_weapons;

import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

public abstract class UtilityWeapon extends Weapon {

    // Constructor
    public UtilityWeapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        super(uses, valid_layers, attack_pattern, sinks_needed);
    }

    // Attacks the given location and results in a FREE or OCCUPIED
    @Override
    public void attackLocation(Grid attacked_grid, Grid.Coordinate coord_of_attack) {

        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();
        int layer = coord_of_attack.getLayer();
        Location loc_of_attack = attacked_grid.getLocationByIndex(layer, row, col);

        // Set the status according to whether or not there is a ship at the current location
        if ( loc_of_attack.hasShip() ) loc_of_attack.setLocationStatus(Location.LocationStatus.OCCUPIED);
        else loc_of_attack.setLocationStatus(Location.LocationStatus.FREE);
    }
}
