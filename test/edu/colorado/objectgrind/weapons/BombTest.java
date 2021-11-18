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
 * This class defines the tests for the Bomb class.
 *
 * @see edu.colorado.objectgrind.weapons.damage_weapons.Bomb
 * @see Player
 * @see WeaponFactory
 */
public class BombTest {

    /**
     * The bomb that is used throughout these tests.
     */
    private Weapon my_bomb;

    /**
     * The player that will be attacked by the bomb.
     */
    private Player attacked_player;

    /**
     * Initializes the bomb and player before each test.
     */
    @BeforeEach
    public void setUp() {
        WeaponFactory factory = new MediumWeaponFactory();
        my_bomb = factory.createWeapon("Bomb");
        attacked_player = new Player(new MediumGamePartsFactory() );
    }

    /**
     * Checks that the bomb has the correct number of uses.
     */
    @Test
    public void canGetNumUses() {
        assertEquals(100,my_bomb.getNumUses());
    }

    /**
     * Checks that number of uses of the bomb can be decreased by one.
     */
    @Test
    public void canDecreaseNumUses(){
        my_bomb.decreaseNumUses();
        assertEquals(99, my_bomb.getNumUses());
    }

    /**
     * Checks that the bomb attacks the correct location.
     * <p>
     *     The attack should be a MISS because there are no ships on the player's grid.
     * </p>
     *
     * @throws Exception if the attack method is invalid
     */
    @Test
    public void canAttackLocation() throws Exception {
        Coordinate coordinate = new Coordinate(1,4,4); // choose coordinate
        my_bomb.useWeapon(attacked_player, coordinate); // use bomb
        Grid player_grid = attacked_player.getGrid(); // getGrid from the player
        LocationStatus status = player_grid.getLocationByIndex(1, 4,4).getLocationStatus(); // check status
        assertEquals(LocationStatus.MISS,status,"A bomb miss.");
    }
}
