package edu.colorado.objectgrind.grid;

/**
 * This class defines the grid that will be used for a Medium game of battleship.
 *
 * @see Grid
 */
public class MediumGrid extends Grid {

    /**
     * Class constructor specifying the number of layers for the Grid.
     * <p>
     *      This constructor passes to its super class 10 for the number of rows and columns.
     * </p>
     *
     * @param num_layers the number of layers that the grid will have
     */
    public MediumGrid(int num_layers) {
        super(10, num_layers);
    }
}
