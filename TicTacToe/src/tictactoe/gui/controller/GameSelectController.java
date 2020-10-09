package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameSelectController {
    @FXML
    private Button btnNewGame;

    @FXML
    private void selectSinglePlayer(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/TicTacView.fxml"));
            fxmlLoader.setController(new TicTacSinglePlayerViewController());
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

    @FXML
    private void selectMultiPlayer(ActionEvent event)
    {
        try {
            //fx:controller="tictactoe.gui.controller.TicTacViewController"
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/TicTacView.fxml"));
            fxmlLoader.setController(new TicTacViewController());
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
