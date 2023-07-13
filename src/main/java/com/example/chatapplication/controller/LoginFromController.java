package com.example.chatapplication.controller;
import com.example.chatapplication.Server;
import com.example.chatapplication.model.clientModel;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFromController extends Application implements Initializable {

    public JFXButton btnLogin;
    public AnchorPane loadFormContext;
    @FXML
    private ComboBox<String> cmbUsername;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private AnchorPane root;

    private String username_id;

    ClientFromController clientFromController;

    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_from.fxml"))));
        stage.show();

        new Thread(()->{
            Server server = new Server();

                try {
                    server.Server();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }).start();
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/client_from.fxml"));
        AnchorPane anchorPane = loader.load();
        clientFromController = loader.getController();
        clientFromController.setLblUsername( cmbUsername.getValue());
        clear();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Space");
        stage.show();
    }

    public void clear(){
        cmbUsername.setValue("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbUsername.requestFocus();
        loadComboBox();
    }

    private void loadComboBox() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = clientModel.getUsername();

            for (String code : codes) {
                obList.add(code);
            }
            cmbUsername.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register_from.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register From");
        stage.show();
        root.getScene().getWindow().hide();
    }
}
