package com.example.chatapplication.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {

    public JFXButton btnLogin;
    public AnchorPane loadFormContext;
    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane root;

    ClientFromController clientFromController;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("/view/client_from.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage1 = new Stage();
//        stage1.setScene(scene);
//        stage1.setTitle("Chat Space");
//        stage1.centerOnScreen();
//        stage1.setResizable(false);
//        stage1.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/client_from.fxml"));
        AnchorPane anchorPane = loader.load();
        clientFromController = loader.getController();
        clientFromController.setLblUsername(txtUserName.getText());
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Space");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUserName.requestFocus();
    }
}
