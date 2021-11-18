package edu.colorado.objectgrind.grid;

/**
 * This class defines a 3D coordinate: (row, column, layer)
 */
public class Coordinate {

    /**
     * The row value for this coordinate.
     */
    private final int row;

    /**
     * The column value for this coordinate.
     */
    private final int col;

    /**
     * The layer value for this coordinate.
     */
    private final int layer;

    /**
     * Class constructor specifying the row, column, and layer values that this coordinate will receive.
     *
     * @param new_layer the layer for this coordinate
     * @param new_row the row for this coordinate
     * @param new_col the column for this coordinate
     */
    public Coordinate(int new_layer, int new_row, int new_col) {
        this.row = new_row;
        this.col = new_col;
        this.layer = new_layer;
    }

    /**
     * Returns the row of this coordinate.
     *
     * @return the row of this coordinate
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this coordinate.
     *
     * @return the col of this coordinate
     */
    public int getColumn() {
        return this.col;
    }

    /**
     * Returns the layer of this coordinate.
     *
     * @return the layer of this coordinate
     */
    public int getLayer() {
        return this.layer;
    }
}
