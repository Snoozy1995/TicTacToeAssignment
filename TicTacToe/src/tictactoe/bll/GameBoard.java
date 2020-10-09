/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author @todo
 */
public class GameBoard implements IGameModel {
    private boolean playerNext=false;
    private int playFields[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private int magicSquare[]={8,1,6,3,5,7,4,9,2}; //https://mathworld.wolfram.com/MagicSquare.html
    public boolean checkMagicSquare(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                for (int k = 0; k < 9; k++){
                    if (i != j && i != k && j != k) {
                        if (playFields[i] == 1 && playFields[j] == 1 && playFields[k] == 1 || playFields[i] == 0 && playFields[j] == 0 && playFields[k] == 0) {
                            if (magicSquare[i] + magicSquare[j] + magicSquare[k] == 15) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer(){
        return playerNext?1:0;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row){
        int index=((col)+(row*3));
        if(playFields[index]!=-1||isGameOver()) return false;
        playFields[index]=getNextPlayer();
        playerNext=!playerNext;
        return true;
    }

    public boolean isGameOver(){
        if(checkMagicSquare()) return true;
        int playCount=0;
        for(int i=0;i<9;i++){
            if(playFields[i]!=-1) playCount++;
        }
        if(playCount==9) return true;
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (i != j && i != k && j != k) {
                        if (playFields[i] == 1 && playFields[j] == 1 && playFields[k] == 1) {
                            if (magicSquare[i] + magicSquare[j] + magicSquare[k] == 15)
                                return 1;
                        }
                        if (playFields[i] == 0 && playFields[j] == 0 && playFields[k] == 0) {
                            if (magicSquare[i] + magicSquare[j] + magicSquare[k] == 15)
                                return 0;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        playerNext=false;
        for(int i=0;i<9;i++){ // Reset the board.
            playFields[i]=-1;
        }
    }

    /**
     * Retrieves a possible next play in a array integer following the format: column,row.
     * Returns {-1,-1} if nothing else is available otherwise it will get a column or row and return that.
     */
    public int[] getNextPlay() {
        List<Integer> available=new ArrayList<Integer>();
        //Retrieve available indexes
        int cr[]={-1,-1};
        for(int i=0;i<9;i++){
            if(tryPlayField(i)){ available.add(i); }
        }
        if(available.size()==0) return cr;
        Random random = new Random();
        int index = available.get(random.nextInt(available.size()));
        int col=index,row=0;
        //Index to column/row
        if(index>2&&index<6){
            row=1;
            col=index-3;
        }else if(index>5){
            row=2;
            col=index-6;
        }
        cr[0]=col;
        cr[1]=row;
        return cr;
    }

    /**
    * Attempts to check the playFields variable if the index is played already or not.
    * True if it is available to be played, otherwise false.
    */
    public boolean tryPlayField(int index){
        if(playFields[index]==-1) return true;
        return false;
    }
}
