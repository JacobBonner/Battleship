package edu.colorado.objectgrind.grid;

/**
 * This class defines the grid that will be used for a Small game of battleship.
 *
 * @see Grid
 */
public class SmallGrid extends Grid {

    /**
     * Class constructor specifying the number of layers for the Grid.
     * <p>
     *      This constructor passes to its super class 7 for the number of rows and columns.
     * </p>
     *
     * @param num_layers the number of layers that the grid will have
     */
    public SmallGrid(int num_layers) {
        super(7, num_layers);
    }
}
