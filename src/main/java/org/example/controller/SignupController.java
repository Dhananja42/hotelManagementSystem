package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.UserDto;
import org.example.model.SignUpModel;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignupController {

    @FXML
    public AnchorPane signupA;
    @FXML
    private Button signupbutton;

    @FXML
    private TextField signupcnum;

    @FXML
    private TextField signupemail;

    @FXML
    private TextField signupname;

    @FXML
    private PasswordField signuppassword1;

    @FXML
    private PasswordField signuppassword2;



    public void signup(ActionEvent actionEvent) throws SQLException {
        System.out.println("testtt");
        String userName = signupname.getText();
        String eMail = signupemail.getText();
        String conNum = signupcnum.getText();
        String password = signuppassword1.getText();
        String password1 = signuppassword2.getText();
        System.out.println(userName +" "+eMail+" "+conNum );

       if(userName.isEmpty() || eMail.isEmpty() || password1.isEmpty() || password.isEmpty()){

            //new Alert(Alert.AlertType.ERROR,"please fill all blanks");
           JOptionPane.showMessageDialog(null,"Fill empties");
           }

       else   if (Objects.equals(password, password1)){
                    System.out.println("test2");
                    UserDto dto = new UserDto(userName,eMail,conNum,password);
                     boolean ifSave = new SignUpModel().saveuser(dto);

                 if (ifSave){
                      System.out.println("test3");
                      //new Alert(Alert.AlertType.CONFIRMATION,"Account create Success");
                 }else {
                     System.out.println("test4");
                     System.out.println("Error ");
                      }


           }else {
            System.out.println("test01");
           // new Alert(Alert.AlertType.ERROR,"ERROR");
        }

    }

    public void SClear(ActionEvent actionEvent) {
        signupname.setText("");
        signupemail.setText("");
        signupcnum.setText("");
        signuppassword1.setText("");
        signuppassword2.setText("");

    }

    public void signbacktologin(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage)signupA.getScene().getWindow();
        stage.setScene(scene);
        // stage.setTitle("Sign up page ");
        stage.centerOnScreen();

    }
}



