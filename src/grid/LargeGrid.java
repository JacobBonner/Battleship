package edu.colorado.objectgrind.grid;

/**
 * This class defines the grid that will be used for a Large game of battleship.
 *
 * @see Grid
 */
public class LargeGrid extends Grid {

    /**
     * Class constructor specifying the number of layers for the Grid.
     * <p>
     *      This constructor passes to its super class 13 for the number of rows and columns.
     * </p>
     *
     * @param num_layers the number of layers that the grid will have
     */
    public LargeGrid(int num_layers) {
        super(13, num_layers);
    }
}
