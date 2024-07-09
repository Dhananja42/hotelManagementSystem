package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dto.CustomerDto;
import org.example.model.CustomerModel;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private RadioButton duluxe;

    @FXML
    private DatePicker checkin;

    @FXML
    private TextField name;

    @FXML
    private TextField nic;

    @FXML
    private DatePicker checkout;

    @FXML
    private TableView<CustomerDto> ctable;

    @FXML
    private RadioButton superior;

    @FXML
    private Label roomnum;

    @FXML
    private TableColumn<?, ?> colCheckingDate;

    @FXML
    private TableColumn<?, ?> colCheckout;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerNIC;
    @FXML
    private ComboBox<String> cmbRoomId;
    private final String[] room = {"room_1", "room_2", "room_3", "room_4", "room_5", "room_6"};
    private final String[] room1 = {"room_01", "room_02,room_03,room_04,room_05,room_06"};

    @FXML
    private TextField text;

    @FXML
    void testOnAction(ActionEvent event) {
        String text1 = text.getText();
        System.out.println(text1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRoomId.setItems(FXCollections.observableArrayList("room_01", "room_02", "room_03", "room_04", "room_05", "room_06", "room_07"));
        loadtable();
        customerCellValueFactory();
    }

    public void loadtable() {
        ObservableList observableList = new CustomerModel().loadDataFromDatabase();
        ctable.setItems(observableList);

    }

    public void customerCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerNIC.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colCheckingDate.setCellValueFactory(new PropertyValueFactory<>("sdate"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("edate"));
    }

    public void customerok(ActionEvent actionEvent) throws SQLException {
        String rm = cmbRoomId.getValue();
        String cm = null;
        if (duluxe.isSelected()) {
            cm = duluxe.getText();
        } else if (superior.isSelected()) {
            cm = superior.getText();
        } else {
            JOptionPane.showMessageDialog(null, "Select Duluxe or Superior");
            return;
        }

        System.out.println(cm);

        String nAme = name.getText();
        String nIc = nic.getText();
        if (nAme.isEmpty() || nIc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the Name and NIC");
            return;
        }

        LocalDate startDate = checkin.getValue();
        LocalDate endDate = checkout.getValue();

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(null, "Please select check-in and check-out dates");
            return;
        }

        if (new CustomerModel().isRoomAvailable(rm, startDate, endDate)) {
            try {
                CustomerDto custdt = new CustomerDto(nAme, nIc, startDate.toString(), endDate.toString(),rm);
                boolean ifSave = new CustomerModel().saveuser(custdt);

                if (ifSave) {
                    System.out.println("You can book");
                    loadtable();
                } else {
                    System.out.println("You can't book");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database load error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Room is not available for the selected dates");
        }
    }

    public void inaction(ActionEvent actionEvent) {
        // Handle action when check-in date is selected
    }

    public void outaction(ActionEvent actionEvent) {
        // Handle action when check-out date is selected
    }
}
