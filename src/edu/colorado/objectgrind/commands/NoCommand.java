package edu.colorado.objectgrind.commands;

/**
 * This class defines a command that does nothing.
 * <p>
 *     It is used in place of a null object when handling commands in other code.
 * </p>
 *
 * @see Command
 */
public class NoCommand implements Command {

    /**
     * Does nothing.
     */
    public void execute() {
        System.out.println("This command does nothing");
    }

    /**
     * Does nothing.
     */
    public void undo() {
        System.out.println("This command does nothing");
    }
}

