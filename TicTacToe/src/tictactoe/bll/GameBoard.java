/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author @todo
 */
public class GameBoard implements IGameModel {
    private boolean playerNext=false;
    int playFields[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int magicSquare[]={8,1,6,3,5,7,4,9,2}; //https://mathworld.wolfram.com/MagicSquare.html

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
        return playerNext?0:1;
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
        int index=((col+1)+(row*3))-1;
        System.out.println("col:"+col+"   row:"+row+"    index"+index);
        if(playFields[index]!=-1||isGameOver()) return false;
        playFields[index]=getNextPlayer();
        playerNext=!playerNext;
        return true;
    }

    public boolean isGameOver(){
        if(checkMagicSquare()){
            System.out.println("magicsquarechecktrue");
            return true;
        }
        int playCount=0;
        for(int i=0;i<9;i++){
            if(playFields[i]!=-1) playCount++;
        }
        if(playCount==9){
            System.out.println("playcountcheck");
            return true;
        }
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

}
