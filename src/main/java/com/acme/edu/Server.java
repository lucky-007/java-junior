package com.acme.edu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(1010);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            Socket client = serverSocket.accept();

            try(
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            client.getInputStream()
                                    )
                            )
                    )
            ) {
                while (true){
                    System.out.println(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
