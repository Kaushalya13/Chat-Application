package com.example.chatapplication;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{
    private ArrayList<Client> clientArrayList;
    Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Client(Socket socket,ArrayList<Client> clientArrayList) throws IOException {
        this.socket = socket;
        this.clientArrayList = clientArrayList;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (Client cl : clientArrayList) {
                    cl.printWriter.println(msg);
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }
}
