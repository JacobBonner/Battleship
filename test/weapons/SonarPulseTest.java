package edu.colorado.objectgrind.weapons;

import edu.colorado.objectgrind.enums.LocationStatus;
import edu.colorado.objectgrind.game.factories.MediumGamePartsFactory;
import edu.colorado.objectgrind.weapons.factories.MediumWeaponFactory;
import edu.colorado.objectgrind.weapons.factories.WeaponFactory;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class defines the tests for the SonarPulse class.
 *
 * @see edu.colorado.objectgrind.weapons.utility_weapons.SonarPulse
 * @see Player
 * @see WeaponFactory
 */
public class SonarPulseTest {

    /**
     * The sonar pulse that is used throughout these tests.
     */
    private Weapon my_sonar_pulse;

    /**
     * The player who will be attacked by the sonar pulse.
     */
    private Player attacked_player;

    /**
     * Initializes the sonar pulse and player before each test.
     */
    @BeforeEach
    public void setUp() {
        WeaponFactory factory = new MediumWeaponFactory();
        my_sonar_pulse = factory.createWeapon("SonarPulse");
        attacked_player = new Player(new MediumGamePartsFactory() );
    }

    /**
     * Checks that the sonar pulse has the correct number of uses.
     */
    @Test
    public void canGetNumUses() {
        assertEquals(2,my_sonar_pulse.getNumUses());
    }

    /**
     * Checks that number of uses of the sonar pulse can be decreased.
     */
    @Test
    public void canDecreaseNumUses() {
        my_sonar_pulse.decreaseNumUses();
        my_sonar_pulse.decreaseNumUses();
        assertEquals(0, my_sonar_pulse.getNumUses());
    }

    /**
     * Checks that the sonar pulse attacks the correct locations.
     * <p>
     *     The attacks should all result in FREE because there are no ships on the player's grid.
     * </p>
     *
     * @throws Exception if the attack method is invalid
     */
    @Test
    public void checkLocationStatus() throws Exception {
        int row = 4;
        int col = 4;
        int layer = 1;

        int offset;
        Coordinate coordinate = new Coordinate(layer,row,col); // choose coordinate
        my_sonar_pulse.useWeapon(attacked_player, coordinate); // use sonarPulse
        Grid player_grid = attacked_player.getGrid(); // getGrid from the player
        LocationStatus status;
        for(int i=row-2; i < row+3; i++){ // check the location status in sonar pulse
            if(i == row-2 | i == row + 2) offset = 0;
            else if(i == row-1 | i == row+1) offset = 1;
            else offset = 2;
            for(int j=col-offset; j < col+offset+1; j++){
                status = player_grid.getLocationByIndex(1, i,j).getLocationStatus(); // check status
                assertEquals(LocationStatus.FREE,status,"The location is free.");
            }
        }
    }
}
