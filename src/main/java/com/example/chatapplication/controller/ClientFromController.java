package com.example.chatapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFromController implements Initializable {
    @FXML
    public AnchorPane root;

    @FXML
    private Label lblUsername;

    @FXML
    private VBox vBoxSendMsg;

    @FXML
    private TextField txtTextField;

    Socket socket;
    DataInputStream dataInputStream;
    ServerSocket serverSocket;
    DataOutputStream dataOutputStream;
    String message = "";

    public void sendOnAction(MouseEvent mouseEvent) {

    }

    public void setLblUsername(String text){
        lblUsername.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}