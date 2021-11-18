package edu.colorado.objectgrind.commands;

/**
 * This interface defines a Command and its two methods execute() and undo().
 * <p>
 *     The Command objects are used throughout the game by a Player.
 * </p>
 */
public interface Command {

    /**
     * Executes a command.
     */
    void execute();

    /**
     * Un-executes a command, i.e. undo.
     */
    void undo();
}
