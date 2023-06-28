module com.example.chatapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.chatapplication to javafx.fxml;
    exports com.example.chatapplication;
    exports com.example.chatapplication.controller;
    opens com.example.chatapplication.controller to javafx.fxml;
}