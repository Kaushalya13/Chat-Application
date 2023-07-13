package com.example.chatapplication.controller;

import com.example.chatapplication.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFromController {
    private static ArrayList<Client> clientArrayList = new ArrayList<>();
    private static void Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        Socket socket;
        int index = 1;
        while (true){
            System.out.println("Waiting for client ....");
            socket = serverSocket.accept();
            System.out.println("Client" + index +"Connected");
            Client client = new Client(socket,clientArrayList);
            clientArrayList.add(client);
            client.start();
            index++;
        }
    }
}
