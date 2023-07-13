package com.example.chatapplication.controller;

import com.example.chatapplication.model.clientModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class RegisterFromController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306/chat_application";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }
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
    @FXML
    private ImageView imgBack;

    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtUser_id.getText();
        String name = txtUserName.getText();
        String password = txtPassword.getText();

        try (Connection connection = DriverManager.getConnection(URL, props)){
            String sql = "INSERT INTO user "+" VALUES(?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,id);
            pstm.setString(2,name);
            pstm.setString(3,password);

            int add = pstm.executeUpdate();

            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"User Added", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try again", ButtonType.OK).show();
            }
        }
        clear();
    }

    public void clear(){
        txtUser_id.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
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

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login_from.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login From");
        stage.show();
        root.getScene().getWindow().hide();
    }
}
