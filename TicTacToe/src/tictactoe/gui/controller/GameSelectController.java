package tictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameSelectController {
    @FXML
    private Button btnNewGame;

    @FXML
    private void selectSinglePlayer(ActionEvent event)
    {

    }

    @FXML
    private void selectMultiPlayer(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/TicTacView.fxml"));
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
}
