package com.example.chatapplication;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {

    public JFXButton btnLogin;
    public JFXButton btnRegister;
    @FXML
    private TextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUserName.requestFocus();
    }
}
