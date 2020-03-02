package cpsc2150.extendedTicTacToe;

/**
 * This object will hold all the information relating to the tic tac toe board.
 * @Defines: Rows: z - the number of rows
 *           Columns: z - the number of columns
 *           NumberToWin: z - the number of tokens sequentially to win
 *
 *
 *
 * @Initialization Ensures: [A game board will be created according to user data as well as establishing a win number]
 *
 * @Constraints: MIN_ALL <= Rows <= MAX_ROW
 *               MIN_ALL <= Columns <= MAX_COL
 *               [MIN_ALL <= NumberToWin <= smallest of Row and Column]
 */
public interface IGameBoard {
    int MAX_ROW = 100;
    int MAX_COL = 100;
    int MAX_WIN = 25;
    int MIN_ALL = 3;
    int MIN_PLAYERS = 2;
    int MAX_PLAYERS = 10;

    /**
     *
     * @return the number of rows
     * @post
     * getNumRows() = numRows
     */
    int getNumRows();

    /**
     *
     * @return the number of columns
     * @post
     * getNumCols() = numCols
     */
    int getNumCols();

    /**
     *
     * @return the number needed to win
     * @post
     * getNumToWin() = numToWin
     */
    int getNumToWin();

    /**
     *
     * @param pos coordinate of the space in the array
     * @return whether the space is available or not
     * @pre
     * pos.rowPos >= 0 and pos.colPos >= 0
     * @post
     * [true if space is not occupied and is in bounds]
     */
    default boolean checkSpace(BoardPosition pos)
    {
        boolean available = false;
        // Checks if the row position is within the bounds
        if(pos.getRow() >= 0 && pos.getRow() < getNumRows())
        {
            // If the row position is within the bounds, checks if the column position is within
            if(pos.getColumn() >= 0 && pos.getColumn() < getNumCols())
            {
                // If both positions are within the bounds, checks if there is a character there
                if(whatsAtPos(pos) == ' ')
                {
                    available = true;
                }
            }
        }
        return available;
    }

    /**
     *
     * @param marker coordinate of where the marker is to be placed
     * @param player the character, X or O, of the player
     * @pre
     * this.checkSpace(marker) = true
     * marker.rowPos >= 0 and marker.colPos >= 0
     * @post
     * [gameBoard at point] == player
     */
    void placeMarker(BoardPosition marker, char player);


    /**
     *
     * @param lastPos coordinate of the last marker placed
     * @return whether that marker resulted in a win
     * @pre
     * lastPos.rowPos >= 0 and lastPos.colPos >= 0
     * @post
     * [checkForWinner = true if lastPos results in a row horizontally, vertically or diagonally, else checkForWinner = false]
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        boolean winner = false;
        char checkPlayer = whatsAtPos(lastPos);

        // Check the horizontal first
        if(checkHorizontalWin(lastPos, checkPlayer))
        {
            winner = true;
        }
        else if (checkVerticalWin(lastPos, checkPlayer))
        {
            winner = true;
        }
        else if(checkDiagonalWin(lastPos, checkPlayer))
        {
            winner = true;
        }

        return winner;
    }

    /**
     *
     * @return whether the players have tied
     * @pre
     * checkForWinner = false
     * @post
     * [true if there is a draw]
     */
    // Checks for a space, if there is one return false
    default boolean checkForDraw()
    {
        for(int i = 0; i < getNumRows(); i++)
        {
            for(int j = 0; j < getNumCols(); j++)
            {
                BoardPosition pos = new BoardPosition(i, j);
                if(whatsAtPos(pos) == ' ')
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *
     * @param lastPos coordinate of the last marker placed
     * @param player the character, X or O, of the player
     * @return whether the last marker resulted a win for this player via horizontal
     * @pre
     * lastPos.rowPos >= 0 and lastPos.colPos >= 0
     * @post
     * [checkHorizontalWin = true if lastPos results in a row = getNumWin(), else checkHorizontalWin = false]
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int numToWin = getNumToWin();
        int numMarkers = 0;
        int row = lastPos.getRow();
        int startCol = lastPos.getColumn();
        int col = lastPos.getColumn();
        boolean winner = false;

        // Loop while there exists a player at the current position
        while(col < getNumCols() && isPlayerAtPos(lastPos, player))
        {
            col++;
            // Update position
            lastPos = new BoardPosition(row, col);
            numMarkers++;
        }

        if(numMarkers >= numToWin)
        {
            winner = true;
        }

        else
        {
            // Reset last pos to original position
            col = startCol - 1;
            lastPos = new BoardPosition(row, col);

            while(col >= 0 && isPlayerAtPos(lastPos, player))
            {
                col--;
                lastPos = new BoardPosition(row, col);
                numMarkers++;
            }

            if(numMarkers >= numToWin)
            {
                winner = true;
            }
        }

        return winner;
    }

    /**
     *
     * @param lastPos coordinate of the last marker placed
     * @param player the character, X or O, of the player
     * @return whether the last marker resulted in a win for this player via vertical
     * @pre
     * lastPos.rowPos >= 0 and lastPos.colPos >= 0 and checkHorizontalWin == false
     * @post
     * [checkVerticalWin = true if lastPos results in a column = getNumWin(), else checkVerticalWin = false]
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int numToWin = getNumToWin();
        int numMarkers = 0;
        int row = lastPos.getRow();
        int startRow = lastPos.getRow();
        int col = lastPos.getColumn();
        boolean winner = false;

        // Loop while there exists a player at the current position
        while(row < getNumRows() && isPlayerAtPos(lastPos, player))
        {
            row++;
            // Update position
            lastPos = new BoardPosition(row, col);
            numMarkers++;
        }

        if(numMarkers >= numToWin)
        {
            winner = true;
        }

        else
        {
            // Reset last pos to original position
            row = startRow - 1;
            lastPos = new BoardPosition(row, col);

            while(row >= 0 && isPlayerAtPos(lastPos, player))
            {
                row--;
                lastPos = new BoardPosition(row, col);
                numMarkers++;
            }

            if(numMarkers >= numToWin)
            {
                winner = true;
            }
        }

        return winner;

    }

    /**
     *
     * @param lastPos coordinate of the last marker placed
     * @param player the character, of the player
     * @return whether the last marker placed resulted in a win  for this player via diagonal
     * @pre
     * lastPos.rowPos >= 0 and lastPos.colPos >= 0 and checkVertical Win == false
     * @post
     * [checkDiagonalWin = true if lastPos results in a diagonal line = getNumWin(), else checkDiagonalWin = false]
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int numMarkers = 0;
        int numToWin = getNumToWin();
        int row = lastPos.getRow();
        int startRow = lastPos.getRow();
        int startCol = lastPos.getColumn();
        int col = lastPos.getColumn();
        boolean winner = false;

        // While there exists a matching marker up and left, increment numMarker
        while(row >= 0 && col >= 0 && isPlayerAtPos(lastPos, player))
        {
            row--;
            col--;
            lastPos = new BoardPosition(row, col);

            numMarkers++;
        }

        if(numMarkers >= numToWin)
        {
            winner = true;
        }

        else
        {
            // Set row and col to the space below and right the starting position
            row = startRow + 1;
            col = startCol + 1;
            lastPos = new BoardPosition(row,col);

            // While there exists a matching marker below and right, increment numMarkers
            while(row < getNumRows() && col < getNumCols() && isPlayerAtPos(lastPos, player))
            {
                row++;
                col++;
                lastPos = new BoardPosition(row, col);
                numMarkers++;
            }

            if(numMarkers >= numToWin)
            {
                winner = true;
            }
            else
            {
                numMarkers = 0;
                row = startRow;
                col = startCol;
                lastPos = new BoardPosition(row, col);

                // Loops while there exists a matching marker up and to the right
                while(row >= 0 && col < getNumCols() && isPlayerAtPos(lastPos, player))
                {
                    row--;
                    col++;
                    numMarkers++;
                    lastPos = new BoardPosition(row, col);
                }

                if(numMarkers >= numToWin)
                {
                    winner = true;
                }
                else
                {
                    // Increment row down and to the left
                    row = startRow + 1;
                    col = startCol - 1;
                    lastPos = new BoardPosition(row, col);

                    // Loops while there exists a matching marker down and to the left
                    while(row < getNumRows() && col >= 0 && isPlayerAtPos(lastPos, player))
                    {
                        row++;
                        col--;
                        numMarkers++;
                        lastPos = new BoardPosition(row, col);
                    }

                    if(numMarkers >= numToWin)
                    {
                        winner = true;
                    }
                }
            }
        }
        return winner;
    }

    /**
     *
     * @param pos the coordinates of the position
     * @return the character located in the position
     * @pre
     * pos.rowPos >= 0 and pos.colPos >= 0
     * @post
     * char = ' ' or char = 'X' or char = 'O'
     */
    char whatsAtPos(BoardPosition pos);

    /**
     *
     * @param pos the coordinates of the position
     * @param player the character, X or O, of the player
     * @return if that player is at the position
     * @pre
     * pos.rowPos >= 0 and pos.colPos >= 0
     * @post
     * [gameBoard at position] = player or [gameBoard at position] != player
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        char whatsHere = whatsAtPos(pos);

        if(player == whatsHere)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
