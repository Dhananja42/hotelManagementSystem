package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CustomerController {
    @FXML
    private TextField text;

    @FXML
    void testOnAction(ActionEvent event) {

        String text1 = text.getText();
        System.out.println(text1);
    }
    void test(){
        System.out.printf("custom method");
    }

}
