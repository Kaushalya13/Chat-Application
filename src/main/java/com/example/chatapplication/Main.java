package com.example.chatapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
       /* stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_from.fxml"))));
        stage.show();*/
        Parent root =FXMLLoader.load(getClass().getResource("/view/login_from.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
