package com.example.chatapplication.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {

    public JFXButton btnLogin;
    public AnchorPane loadFormContext;
    @FXML
    private ComboBox<String> cmbUsername;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private AnchorPane root;

    ClientFromController clientFromController;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/client_from.fxml"));
        AnchorPane anchorPane = loader.load();
        clientFromController = loader.getController();
        clientFromController.setLblUsername(cmbUsername.getValue());
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Space");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbUsername.requestFocus();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register_from.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register From");
        stage.show();
    }

    public void cmbUsernameOnAction(ActionEvent actionEvent) {

    }
}
