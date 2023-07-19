package com.example.chatapplication.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

public class ClientFromController extends Thread {
    @FXML
    public AnchorPane root;

    @FXML
    private Label lblUsername;

    @FXML
    private VBox vBoxSendMsg;

    @FXML
    private TextField txtTextField;

    public static String userName;
    Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    private FileChooser fileChooser;

    DataOutputStream dataOutputStream;
    private File file;

    public void initialize(){
        try {
            socket = new Socket("localhost",6000);
//            System.out.println("Socket is connected");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setLblUsername(String text) {
        lblUsername.setText(text);
    }

    @Override
    public void run(){
        try {
            while (true) {

                String msg = bufferedReader.readLine();
                System.out.println("msg : " + msg);

                String[] message = msg.split(":");

                Text text = new Text("");

                if (message[1].startsWith(" img")) {

                    File file = new File("C:"+message[2]);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!message[0].equalsIgnoreCase(lblUsername.getText())) {
                        vBoxSendMsg.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1 = new Text("  " + message[0] + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                        hBox.setStyle("-fx-alignment: center-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);
                        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                    }

                    Platform.runLater(() -> vBoxSendMsg.getChildren().addAll(hBox));
                } else {
                    TextFlow tempFlow = new TextFlow();

                    if (!message[0].equalsIgnoreCase(lblUsername.getText())) {
                        Text txtName = new Text(message[0] + " : " + message[1]);
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12

                    if (!message[0].equalsIgnoreCase(lblUsername.getText())) {
                        vBoxSendMsg.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setStyle("-fx-alignment: center-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                        flow.setStyle("-fx-background-color:#778beb;-fx-background-radius:15;-fx-font-size: 15;-fx-font-weight: normal;-fx-text-fill: black;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                        hBox.getChildren().add(flow);
                    } else {
                        Text text2 = new Text(message[1] + ": Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                        flow2.setStyle("-fx-background-color:#CCFF9A;-fx-background-radius:15;-fx-font-size: 15;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
                        hBox.getChildren().add(flow2);
                    }
                    Platform.runLater(() -> vBoxSendMsg.getChildren().addAll(hBox));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMsg() throws IOException {
        String msg = txtTextField.getText();
        printWriter.println(lblUsername.getText() + ":" + msg);
        System.out.println(lblUsername.getText() + ":" +msg);
        txtTextField.clear();

    }
    public void imgOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.file = fileChooser.showOpenDialog(stage);
        printWriter.println(lblUsername.getText() + ": img #" + file.getPath());
    }

    public void emojiOnAction(MouseEvent mouseEvent) {
    }

    public void sendOnAction(MouseEvent mouseEvent) throws IOException {
        sendMsg();
    }
}