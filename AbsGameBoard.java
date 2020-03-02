package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard{

    /**
     *
     * @return a string containing everything in the gameBoard array
     * @post
     * [toString is a string representation of the GameBoard]
     */
    @Override
    public String toString()
    {
        String board = "   ";
        for(int t = 0; t < getNumCols(); t++)
        {
            board += String.format("%2d|", t);
        }
        board += "\n";

        // Loop through the array, filling the string as it goes
        for(int i = 0; i < getNumRows(); i++)
        {
            for(int j = 0; j < getNumCols(); j++)
            {
                if(j == 0)
                {
                    board += String.format("%2d|", i);
                }
                BoardPosition pos = new BoardPosition(i, j);

                board += String.format("%-2c|", whatsAtPos(pos));
            }
            board+= "\n";
        }

        return board;
    }
}
