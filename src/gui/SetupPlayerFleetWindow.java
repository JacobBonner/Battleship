package edu.colorado.objectgrind.gui;

import edu.colorado.objectgrind.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * This class defines the fleet placement window for player.
 *
 * @see ControlWindow
 * @see GameSetting
 */
public class SetupPlayerFleetWindow extends JFrame{
    /**
     * GUI panel stores the textfield and button.
     */
    JPanel panel;

    /**
     * GUI textfield array that get the head and tail coordinates of each ships.
     */
    final JTextField[] text;

    /**
     * GUI label labels each textfield or button.
     */
    JLabel label;

    /**
     * GUI button for submit the fleet.
     */
    final JButton button;

    /**
     * Player used throughout the setup fleet window.
     *
     * @see Player
     */
    final Player player;

    /**
     * The boolean array for checking all the ships are placed.
     */
    final boolean[] place_ships;

    /**
     * Class constructor that initialize the game and setup the frame. The user will type in the coordinates for each
     * ships then if the fleet is placed for player 1, it will goes to the fleet placement window for player 2 then
     * repeating the same process. Finally the user will start play the game which will go to next class, Control Window.
     *
     * @param battleship_game The game setting that applies throughout the game
     * @param player_index The player id for each player
     * @param player_name The player name
     */
    public SetupPlayerFleetWindow(GameSetting battleship_game, int player_index, String player_name){
        // Initialize game
        player = battleship_game.getPlayer(player_index);
        place_ships = new boolean[player.getSizeOfFleet()];

        // Setup frame
        Arrays.fill(place_ships, false);
        text = new JTextField[player.getSizeOfFleet()];
        setTitle("Placed Fleet for " + player_name);
        setLayout(new GridLayout(player.getSizeOfFleet()+1, 1));

        // Setup Fleet for player
        System.out.println("Enter the Coordinate as layer(0: Underwater, 1: Surface) + coordinate (Ex: 12A)");
        System.out.println("Place Fleet for " + player_name);
        for (int i = 0; i < player.getSizeOfFleet(); i++) {
            panel = new JPanel();
            label = new JLabel("Head,Tail");
            text[i] = new JTextField(10);
            int ship_index = i;
            text[i].addActionListener(e -> {
                try {
                    String[] input = text[ship_index].getText().split(",");
                    // If the user can place ship the ship will be placed in the grid, else the user has to reenter the head and tail coordinate
                    if(battleship_game.canPlaceShips(player_index, ship_index, input[0], input[1])){
                        battleship_game.placeShips(player_index, ship_index, input[0], input[1]);
                        place_ships[ship_index] = true;
                    }else{
                        System.out.println("Error: " + player.getShipAtIndex(ship_index).printShip() + " cannot be placed on the grid.");
                        text[ship_index].setText("");
                    }
                }catch(Exception except){
                    System.out.println("Error: " + except.getMessage());
                    text[ship_index].setText("");
                }
            });
            // Add panel to the frame
            panel.add(label);
            panel.add(text[i]);
            add(new JLabel(player.getShipAtIndex(i).printShip()));
            add(panel);
        }

        // Press Submit button when all the ships are placed, then it will move to next frame that the
        button = new JButton("Submit");
        button.addActionListener(e -> {
            if(fleetPlaced()) {
                // If the current player is player 1, then go to next setup fleet window for player 2. Otherwise, Start Play the Game
                if(player_index == 0){
                    SetupPlayerFleetWindow frame = new SetupPlayerFleetWindow(battleship_game, 1, "Player 2");
                    frame.setVisible(true);
                }else{
                    new ControlWindow(battleship_game);
                }
                dispose();
            }else{
                System.out.println("Error: The fleet is not placed.");
            }
        });
        add(button);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    /**
     * Get the submit button.
     *
     * @return <code>JButton</code> submit button
     */
    public JButton getButton(){
        return this.button;
    }

    /**
     * Get the textfield of the ship.
     *
     * @param i the index of each textfield
     * @return <code>JTextField</code> the textfield of the ship
     */
    public JTextField getTextField(int i){
        return this.text[i];
    }

    /**
     * Get the fleet placed status.
     *
     * @return <code>boolean</code> fleet placed <code>false</code> otherwise
     */
    public boolean fleetPlaced(){
        for(boolean ships: this.place_ships) if(!ships) return false;
        return true;
    }
}
