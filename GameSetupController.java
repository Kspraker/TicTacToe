package cpsc2150.extendedTicTacToe;

/**
 * Created by kplis on 4/5/2018.
 */
public class GameSetupController {
    private GameSetupScreen view;
    private int max_size = 20;
    private final int MEM_CUTOFF = 64;


    public GameSetupController(GameSetupScreen v)
    {
        view = v;
    }

    public void processButtonClick(int rows, int cols, int players, int numWin )
    {
        IGameBoard checkThis = new GameBoard(rows, cols, numWin);
        String errorMsg = "";
        if(rows < checkThis.MIN_ALL|| rows > max_size)
        {
            errorMsg += "Rows must be between " +  checkThis.MIN_ALL + " and " + max_size;
        }

        if(cols < checkThis.MIN_ALL || cols > max_size)
        {
            errorMsg += "Columns must be between " +  checkThis.MIN_ALL + " and " + max_size;
        }

        if (numWin > rows)
        {
            errorMsg += "Can't have more to win than the number of rows";
        }
        if (numWin > rows)
         {
            errorMsg += "Can't have more to win than the number of Columns";
         }

        if(numWin < checkThis.MIN_ALL)
        {
            errorMsg += "Number to win must be at least " + checkThis.MIN_ALL;
        }

        if(!errorMsg.equals(""))
        {
            view.displayError(errorMsg);

        }
        else
        {
            view.closeScreen();
            IGameBoard model;
            if(rows * cols <= MEM_CUTOFF) {
                model = new GameBoard(rows, cols, numWin);
            }
            else
            {
                model = new GameBoardMem(rows, cols, numWin);
            }
            TicTacToeView tview = new TicTacToeView(rows, cols);
            TicTacToeController tcontroller = new TicTacToeController(model, tview, players);

            tview.registerObserver(tcontroller);
        }
    }
}
