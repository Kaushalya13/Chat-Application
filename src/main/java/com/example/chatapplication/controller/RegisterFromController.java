package com.example.chatapplication.controller;

import com.example.chatapplication.model.clientModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFromController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUser_id;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;


    public void btnRegisterOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextUserId();
    }

    private void generateNextUserId() {
        try {
            String nextId = clientModel.generateNextUserId();
            txtUser_id.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
