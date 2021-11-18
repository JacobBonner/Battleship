package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

import java.lang.reflect.Method;

/**
 * This class defines a weapon that does "damage" to a location, and either results in a HIT or a MISS.
 *
 * @see Weapon
 * @see WeaponAttackPattern
 */
public abstract class DamageWeapon extends Weapon {

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
    public DamageWeapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        super(uses, valid_layers, attack_pattern, sinks_needed);
    }

    /**
     * Attacks the given coordinate on the grid and results in a HIT or MISS.
     *
     * @param attacked_grid the grid that will be attacked by this weapon
     * @param coord the coordinate that this weapon will be used at
     * @see Ship#checkCaptainsQuarter(Grid, Coordinate)
     * @see Ship#isCapQuartersDestroyed()
     * @see Ship#sink()
     * @see LocationStatus
     */
    @Override
    public void attackLocation(Grid attacked_grid, Coordinate coord){

        // Save the attacked location
        Location attacked_location = attacked_grid.getLocationByIndex(coord.getLayer(), coord.getRow(), coord.getColumn());
        
        // Get the layer of the attacked coordinate
        int layer = coord.getLayer();
        System.out.println("Attacking layer " + layer);

        // If there is a ship at the location of attack
        if( attacked_location.hasShip() ) {

            // Get the ship that is at this location
            Ship attacked_ship = attacked_location.getShip();

            // If the location is the captain's quarters
            if ( attacked_ship.checkCaptainsQuarter(attacked_grid,coord) ) {

                // Decrease the health of the captain's quarters
                attacked_ship.hitCapQuarters();

                // If the captain's quarters health is zero
                if( attacked_ship.isCapQuartersDestroyed() ) {

                    // Indicate that the attack was a HIT
                    System.out.println("HIT the Captain's Quarters");

                    // Sink the ship
                    attacked_ship.sink();

                    // Update every location the ship is at to HIT, by scanning the row and col of the attacked location
                    for (int i=0; i<attacked_grid.getGrid()[layer].length; i++) {

                        for(int j=0; j<attacked_grid.getGrid()[layer].length; j++) {

                            // If the current location has the same ship as the one that was just hit, set status to HIT
                            Location new_location = attacked_grid.getLocationByIndex(layer, i, j);
                            if ( new_location.getShip() == attacked_ship ) new_location.setLocationStatus(LocationStatus.HIT);
                        }
                    }
                }
                else {

                    // Inform the user that it is a MISS
                    System.out.println("MISS");

                    // Update the location status to MISS
                    attacked_location.setLocationStatus(LocationStatus.MISS);
                }
            }
            else { // if it is not the captain's quarters

                // Indicate that the attack was a HIT
                System.out.println("HIT");

                // Hit the ship, i.e. decrease its health by one
                attacked_ship.hit();

                // Update the location status to HIT
                attacked_location.setLocationStatus(LocationStatus.HIT);
            }

            // If the ship has sunk
            if ( attacked_ship.hasSunk() ) {

                // Inform the attacker that they sunk a ship, and give the type
                System.out.println("You SUNK your opponent's ");
                attacked_ship.printShip();
            }
        }
        else { // there is no ship at the location of attack

            // Inform the user that it is a MISS
            System.out.println("MISS");

            // Update the location status to MISS
            attacked_location.setLocationStatus(LocationStatus.MISS);
        }
    }

    /**
     * Uses this weapon over its entire attack pattern on all of its valid layers.
     * <p>
     *     This function calls the useWeapon function from this class' super class - Weapon - and then
     *     afterwards checks if the player that was attacked has surrendered.
     * </p>
     *
     * @param attacked_player the player who is being attacked by this weapon
     * @param coord_of_attack the coordinate where this weapon is being used
     * @throws Exception if the attack method is invalid
     * @see #printWeapon()
     * @see #decreaseNumUses()
     * @see #valid_layers
     * @see #attack_pattern
     * @see Method
     * @see WeaponAttackPattern#attackWithPattern(Weapon, Method, Grid, Coordinate)
     */
    @Override
    public void useWeapon(Player attacked_player, Coordinate coord_of_attack) throws Exception {

        // Use the weapon
        super.useWeapon(attacked_player, coord_of_attack);

        // If all the ships of the attacked player are sunk, then they surrender, and print it to the user
        if( attacked_player.surrender() ) System.out.print("Your opponent has SURRENDERED");
    }
}
