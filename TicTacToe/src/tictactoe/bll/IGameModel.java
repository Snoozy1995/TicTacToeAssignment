/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public interface IGameModel
{

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer();

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    public boolean play(int col, int row);

    /**
     * Attempts to retrieve an available play for the computer in singleplayer.
     * Retrieves a possible next play in a array integer following the format: column,row.
     * Returns {-1,-1} if nothing else is available otherwise it will get a column or row and return that.
     */
    public int[] getNextPlay();


    /**
     *
     * Attempts to check the playFields variable if the index is played already or not.
     *
     * @return true if available or false if not.
     */
    public boolean tryPlayField(int index);

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver();

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    public int getWinner();

    /**
     * Resets the game to a new game state.
     */
    public void newGame();
}
