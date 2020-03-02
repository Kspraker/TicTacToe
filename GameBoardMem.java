package cpsc2150.extendedTicTacToe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 * @Invariants gameBoard != NULL
 *             numToWin >= MIN_ALL && numToWin <= MAX_WIN [and smallest of numCols and numRows]
 *             numCols >= MIN_ALL && numCols <= MAX_COL
 *             numRows >= MIN_ALL && numRows <= MAX_ROW
 * @Correspondeces: Rows = numRows
 *                  Columns = numCols()
 *                  NumberToWin = numToWin;
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private Map<Character, List<BoardPosition>> gameMap;
    private int numToWin;
    private int numCols;
    private int numRows;

    public GameBoardMem(int row, int cols, int win)
    {
        numRows = row;
        numCols = cols;
        numToWin = win;
        gameMap = new HashMap<Character, List<BoardPosition>>();
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

    public void placeMarker(BoardPosition marker, char player)
    {
        // If the player exists in the map, get the list and add the position to the list
        if(gameMap.containsKey(player))
        {
            List<BoardPosition> tempList;
            tempList = gameMap.get(player);
            tempList.add(marker);
            gameMap.put(player, tempList);
        }
        // If the player does not exist in the map, create a new array list and put the key and value in the map
        else
        {
            List<BoardPosition> newList = new ArrayList<BoardPosition>();
            newList.add(marker);
            gameMap.put(player, newList);
        }
    }

    public char whatsAtPos(BoardPosition pos)
    {
        char marker = ' ';
        for (Map.Entry<Character, List<BoardPosition>> map: gameMap.entrySet())
        {
            // map holds our current key value pair
            if(map.getValue().contains(pos))
            {
                marker = map.getKey();
            }
        }
        return marker;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        boolean find;
        if(gameMap.containsKey(player))
        {
            find = gameMap.get(player).contains(pos);
        }
        else{
            find = false;
        }
        return find;
    }
}
