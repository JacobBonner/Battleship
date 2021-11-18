package edu.colorado.objectgrind.weapons.damage_weapons;

import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.grid.Location;
import edu.colorado.objectgrind.ships.ship_types.Ship;
import edu.colorado.objectgrind.weapons.Weapon;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

public abstract class DamageWeapon extends Weapon {

    // Constructor
    public DamageWeapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        super(uses, valid_layers, attack_pattern, sinks_needed);
    }

    // Attacks the given location and results in a HIT or MISS
    @Override
    public void attackLocation(Grid attacked_grid, Grid.Coordinate coord){

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
                    for (int j=0; j<attacked_grid.getGrid()[coord.getLayer()].length; j++) {

                        Location row_location = attacked_grid.getLocationByIndex(1, coord.getRow(), j);
                        Location col_location = attacked_grid.getLocationByIndex(1, j,coord.getColumn());

                        if ( row_location.getShip() == attacked_ship ) row_location.setLocationStatus(Location.LocationStatus.HIT);
                        else if ( col_location.getShip() == attacked_ship ) col_location.setLocationStatus(Location.LocationStatus.HIT);
                    }
                }
                else {

                    // Inform the user that it is a MISS
                    System.out.println("MISS");

                    // Update the location status to MISS
                    attacked_location.setLocationStatus(Location.LocationStatus.MISS);
                }
            }
            else { // if it is not the captain's quarters

                // Indicate that the attack was a HIT
                System.out.println("HIT");

                // Hit the ship, i.e. decrease its health by one
                attacked_ship.hit();

                // Update the location status to HIT
                attacked_location.setLocationStatus(Location.LocationStatus.HIT);
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
            attacked_location.setLocationStatus(Location.LocationStatus.MISS);
        }
    }

    @Override
    public void useWeapon(Player attacked_player, Grid.Coordinate coord_of_attack) throws Exception {

        // Use the weapon
        super.useWeapon(attacked_player, coord_of_attack);

        // If all the ships of the attacked player are sunk, then they surrender, and print it to the user
        if( attacked_player.surrender() ) System.out.print("Your opponent has SURRENDERED");
    }
}
