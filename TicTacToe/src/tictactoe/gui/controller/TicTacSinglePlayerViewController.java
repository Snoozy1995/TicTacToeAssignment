/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Stegger
 */
public class TicTacSinglePlayerViewController implements Initializable
{

    @FXML
    private Label lblPlayer;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            if (game.play(c, r))
            {
                Button btn = (Button) event.getSource();
                btn.setText("X");
                setPlayer();
                if (game.isGameOver()){
                    displayWinner(game.getWinner());
                }else{ //code starts
                    int cr[]=game.getNextPlay();
                    if (game.play(cr[0],cr[1]))
                    {
                        Button btncpu = (Button) gridPane.lookup("#btn"+((cr[0])+(cr[1]*3)+1));
                        btncpu.setText("O");

                        if (game.isGameOver()){
                            displayWinner(game.getWinner());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleNewGame(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/GameSelectView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();


            final Node current_source = (Node) event.getSource();
            final Stage current_stage = (Stage) current_source.getScene().getWindow();
            current_stage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();
    }

    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }

    private void displayWinner(int winner) {
        String message = "";
        switch (winner) {
            case -1:
                message = "It's a draw :-(";
                break;
            case 0:
                message = "You win!!! :-)";
                break;
            default:
                message = "It's a loss, computer win. :-(";
                break;
        }
        lblPlayer.setText(message);
    }

}
