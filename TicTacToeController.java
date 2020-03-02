package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */

/**
 * Kaleb Spraker
 * 12-4-19
 * CPSC 2151 - 001, HW6
 * This program uses a 2D array and a map to keep track of a tic tac toe game, with user-defined rows and
 * columns, as well as a user-defined number of tokens to win, between at least 2 players and at most 10 players.
 * This program also now uses a gui for entering initial dimensions and placing markers.
 */

public class TicTacToeController{
    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;


    public static final int MAX_PLAYERS = 10;
    private static int numPlayers;
    private static final Character[] totalPlayerList = {'X', 'O', 'K', 'A', 'L', 'E', 'B', 'S', 'Y', 'W'};
    private static int currentPlayer;
    private static boolean won;
    private static boolean draw;




    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @param np The number of players for the game
     * @post the controller will respond to actions on the view using the model.
     */
    TicTacToeController(IGameBoard model, TicTacToeView view, int np){
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        currentPlayer = 0;
        draw = false;
        won = false;
    }

    /**
     *
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        BoardPosition thisPlayer = new BoardPosition(row, col);
        // Check if there has been a win or draw
        if(draw || won)
        {
            newGame();
        }
        // If not, check the position
        else {
            // Check if the coordinate is a space, if it is, place the marker
            if (curGame.whatsAtPos(thisPlayer) == ' ') {
                curGame.placeMarker(thisPlayer, totalPlayerList[currentPlayer]);
                screen.setMarker(row, col, totalPlayerList[currentPlayer]);
                // check if that results in a win, if it does set win to true and display to user
                if (curGame.checkForWinner(thisPlayer)) {
                    won = true;
                    screen.setMessage(totalPlayerList[currentPlayer] + " Won! Click a button to start a new game.");
                    // If not, check for a draw, then set draw to true if true, and display
                } else if (curGame.checkForDraw()) {
                    draw = true;
                    screen.setMessage("Draw! Click a button to start a new game.");
                }
                // If neither a win or draw, increment the player counter
                else {
                    // If the player is the last player, set it back to the first
                    if (currentPlayer == numPlayers - 1) {
                        currentPlayer = 0;
                    } else {
                        currentPlayer++;
                    }
                    // Alert the next player of their turn
                    screen.setMessage("It is " + totalPlayerList[currentPlayer] + "\'s turn.");
                }
                //If the spot is not a space, display that the sport is not available
            } else {
                screen.setMessage("Sorry, that position is not available. It is " + totalPlayerList[currentPlayer] + "\'s turn.");
            }
        }
    }

    private void newGame()
    {
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
