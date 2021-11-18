package edu.colorado.objectgrind.gui;

import javax.swing.*;

/**
 * This class defines the game control window that users place the weapon index and coordinate.
 *
 * @see GameSetting
 */
public class ControlWindow extends JFrame{
    /**
     * GUI panel that stores the textfield and button.
     */
    final JPanel panel;

    /**
     * GUI textfield that get the weapon index.
     */
    final JTextField text_weapon;

    /**
     * GUI textfield that get the coordinate that user want to attack.
     */
    final JTextField text_coordinate;

    /**
     * GUI button that user submit the weapon index and coordinate to attack the opponent player.
     */
    final JButton button;

    /**
     * The coordinate that the user want to attack on the opponent grid.
     */
    String coordinate;

    /**
     * The weapon that the user want to use for attack.
     */
    String weapon;

    /**
     * The player index.
     */
    int player_index;

    /**
     * Array of player name.
     */
    final String[] player_name;

    /**
     * Array of the opponent player index.
     */
    final int[] opponent;

    /**
     * The control window and player grids, own grid and opponent grid, will display in the screen. The user type in
     * the weapon index and coordinate for attack. The player will take turns. The game end after the opponent player
     * surrender.
     *
     * @param battleship_game The game setting throughout the game.
     */
    public ControlWindow(GameSetting battleship_game){
        panel = new JPanel();
        text_weapon = new JTextField(5);
        text_coordinate = new JTextField(5);
        button = new JButton("Enter");
        setTitle("Control");
        player_name = new String[]{"Player 1", "Player 2"};
        opponent = new int[]{1,0};
        player_index = 0;

        System.out.println(player_name[player_index] + " attacks.");

        // Select weapon index
        battleship_game.printArsenal(player_index);
        text_weapon.addActionListener(e -> {
            weapon = text_weapon.getText();
            System.out.println("Weapon index: " + weapon);
        });


        text_coordinate.addActionListener(e -> {
            coordinate = text_coordinate.getText();
            System.out.println("Coordinate: " + coordinate);
        });

        battleship_game.displayFrame(0);
        button.addActionListener(e -> {
            try {
                battleship_game.setPlayerAttackOnGrid(player_index, opponent[player_index], weapon, coordinate);
                if (battleship_game.getPlayer(opponent[player_index]).surrender()) {
                    System.out.println("\n");
                    System.out.println(player_name[opponent[player_index]] + " surrender");
                    System.out.println("Thanks for playing Battleship Game!");
                    dispose();
                    return;
                }else{
                    battleship_game.removeFrame(player_index);
                    if (player_index == 0) { player_index = 1; }
                    else { player_index = 0; }
                    battleship_game.displayFrame(player_index);
                }
            }catch (Exception exception){
                System.out.println("Error: " + exception.getMessage());
                System.out.println("Error: The weapon index/coordinate is either not in valid format or out of bound.");
            }
            battleship_game.printStatus(player_index, player_name[player_index]);
            text_weapon.setText("");
            text_coordinate.setText("");
        });

        // Add components to the frame
        panel.add(new JLabel("Enter Weapon index"));
        panel.add(text_weapon);
        panel.add(new JLabel("Enter Location"));
        panel.add(text_coordinate);
        panel.add(button);
        add(panel);
        pack();
        setVisible(true);
    }

    /**
     * Get the enter button.
     *
     * @return <code>JButton</code> the enter button
     */
    public JButton getButton(){
        return this.button;
    }

    /**
     * Get the weapon textfield.
     *
     * @return <code>JTextField</code> the weapon textfield.
     */
    public JTextField getTextWeaponIndex(){
        return this.text_weapon;
    }

    /**
     * Get the coordinate textfield.
     *
     * @return <code>JTextField</code> the coordinate textfield.
     */
    public JTextField getTextCoordinate(){
        return this.text_coordinate;
    }
}
