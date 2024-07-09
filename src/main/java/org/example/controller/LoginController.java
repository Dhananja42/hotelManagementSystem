package org.example.controller;

import com.sun.net.httpserver.Authenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.loginDto;
import org.example.model.SignUpModel;
import org.example.model.loginModel;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

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
    public AnchorPane home;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        String userName = username.getText();
        String passWord = password.getText();



        if (userName == null || password == null) {
            JOptionPane.showMessageDialog(null, "Fill Empties");
        } else {
            loginDto ld = new loginDto(userName, passWord);
            boolean ifSave = new loginModel().log(ld);
            if (ifSave){
                System.out.println("test3");
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                // stage.setTitle("Sign up page ");
                stage.centerOnScreen();


                //new Alert(Alert.AlertType.CONFIRMATION,"Account create Success");
            }else {
                System.out.println("test4");
                System.out.println("Error ");
            }


        }

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

