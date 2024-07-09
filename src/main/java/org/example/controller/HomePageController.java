package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HomePageController {

    @FXML
    public AnchorPane homee;


    public void homecustomer(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) homee.getScene().getWindow();
        stage.setScene(scene);
        // stage.setTitle("Sign up page ");
        stage.centerOnScreen();


    }
}
