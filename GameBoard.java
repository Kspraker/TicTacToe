package cpsc2150.extendedTicTacToe;

/**
 * @Invariants gameBoard != NULL
 *             numToWin >= MIN_ALL && numToWin <= MAX_WIN [and smallest of numCols and numRows]
 *             numCols >= MIN_ALL && numCols <= MAX_COL
 *             numRows >= MIN_ALL && numRows <= MAX_ROW
 * @Correspondeces: Rows = numRows
 *                  Columns = numCols()
 *                  NumberToWin = numToWin;
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{
    private char[][] gameBoard;
    private int numToWin;
    private int numCols;
    private int numRows;


    /**
     * @param rows is the number of rows
     * @param cols is the number of columns
     * @param win is the number of sequential tokens to win
     * @pre
     * MIN_ALL <= rows <= MAX_ROWS
     * MIN_ALL <= cols <= MAX_COLS
     * MIN_ALL <= win <= [smallest of row and col] && win <= MAX_WIN
     *
     * @post
     * [gameBoard will be filled to the size specified]
     */
    // Fill the array with spaces
    public GameBoard(int rows, int cols, int win)
    {
        numRows = rows;
        numCols = cols;
        numToWin = win;

        gameBoard = new char[numRows][numCols];
        for(int i = 0; i < gameBoard.length; i++)
        {
            for(int j = 0; j < gameBoard[i].length; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        gameBoard[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        int row = pos.getRow();
        int col = pos.getColumn();
        char marker = gameBoard[row][col];
        return marker;
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumCols()
    {
        return numCols;
    }

    public int getNumToWin()
    {
        return numToWin;
    }
}
