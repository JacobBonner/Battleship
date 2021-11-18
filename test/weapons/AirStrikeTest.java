package edu.colorado.objectgrind.weapons;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.game.factories.MediumGamePartsFactory;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.weapons.damage_weapons.AirStrike;
import edu.colorado.objectgrind.weapons.damage_weapons.HorizontalAirStrike;
import edu.colorado.objectgrind.weapons.damage_weapons.VerticalAirStrike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class defines the tests for the AirStrike class and two subclasses.
 *
 * @see AirStrike
 * @see HorizontalAirStrike
 * @see VerticalAirStrike
 * @see Player
 */
public class AirStrikeTest {

    /**
     * The Air Strikes that will be used throughout these tests.
     */
    private Weapon[] air_strikes;

    /**
     * The player that will be attacked by the air strikes.
     */
    private Player attacked_player;

    /**
     * Initializes the air strikes and player before each test.
     */
    @BeforeEach
    public void setUp() {
        air_strikes = new AirStrike[] { new HorizontalAirStrike(2), new VerticalAirStrike(2)};
        attacked_player = new Player( new MediumGamePartsFactory() );
    }

    /**
     * Checks that all of the air strikes have the correct number of uses.
     */
    @Test
    public void canGetNumUses() {

        for ( Weapon air_strike : air_strikes ) {
            assertEquals(1, air_strike.getNumUses());
        }
    }

    /**
     * Checks that we can decrease the uses of the air strikes.
     */
    @Test
    public void canDecreaseNumUses(){

        for ( Weapon air_strike : air_strikes ) {
            assertEquals(1, air_strike.getNumUses());
            air_strike.decreaseNumUses();
            assertEquals(0, air_strike.getNumUses());
        }
    }

    /**
     * Checks that each of the air strikes attacks the correct locations.
     * <p>
     *     Each attack should be a MISS because there are no ships placed on the player's grid.
     * </p>
     *
     * @throws Exception if the attack method is invalid
     */
    @Test
    public void canAttackLocation() throws Exception {

        Coordinate coordinate1 = new Coordinate(1, 0, 4); // choose coordinate
        Coordinate coordinate2 = new Coordinate(1, 4, 4); // choose coordinate
        Coordinate[] coords = new Coordinate[]{coordinate1, coordinate2};

        LocationStatus status;

        int row, col;

        for (int i = 0; i < 2; i++) {

            air_strikes[i].useWeapon(attacked_player, coords[i]); // use airstrike

            for (int j = 2; j < 7; j++) {

                if (i == 0) {
                    row = 0;
                    col = j;
                } else {
                    col = 4;
                    row = j;
                }

                Grid player_grid = attacked_player.getGrid(); // getGrid from the player
                status = player_grid.getLocationByIndex(1, row, col).getLocationStatus(); // check status
                assertEquals(LocationStatus.MISS, status);
            }
        }
    }
}

