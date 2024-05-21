package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public AnchorPane root;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane loginA;

    @FXML
    void login(ActionEvent event) {
        String userName = username.getText();
        String passWord = password.getText();

        System.out.println(userName );
        System.out.println(passWord);
    }

    @FXML
    void loginSignupbutton(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
       // stage.setTitle("Sign up page ");
        stage.centerOnScreen();


    }


}

