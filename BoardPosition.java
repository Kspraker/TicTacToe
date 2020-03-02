package cpsc2150.extendedTicTacToe;

/**
 * @Invariant rowPos >= 0
 * @Invariant colPos >= 0
 */

public class BoardPosition {
    private int rowPos;
    private int colPos;

    /**
     *
     * @param row position in the row of the array
     * @param col position in the column of the array
     * @pre
     * row >= 0 && col >= 0 && row < MAX_ROW && col < MAX_COL
     * @post
     * rowPos = row && colPos = col
     */
    public BoardPosition(int row, int col)
    {
        rowPos = row;
        colPos = col;
    }

    /**
     *
     * @return the position of the row stored in the object
     * @post
     * getRow() = rowPos
     */
    public int getRow()
    {
        return rowPos;
    }

    /**
     *
     * @return the position of the column stored in the object
     * @post
     * getColumn() = colPos
     */
    public int getColumn()
    {
        return colPos;
    }

    /**
     * @param compare is the object to be compared
     * @return If the positions are equal or not
     * @pre
     * object 1 == BoardPosition passed in && object 2 == existing BoardPosition Object
     * @post
     * return true if position1 = position2
     */
    @Override
    public boolean equals(Object compare)
    {
        boolean decision = false;
        BoardPosition newObject = (BoardPosition)compare;
        if((this.rowPos == newObject.rowPos) && (this.colPos == newObject.colPos))
        {
            decision = true;
        }

        return decision;
    }

    /**
     *
     * @return a string of the coordinate
     * @post
     * string == row, col
     */
    @Override
    public String toString()
    {
        String coordinates = "";
        coordinates = String.valueOf(rowPos) + ", " + String.valueOf(colPos);
        return coordinates;
    }
}
