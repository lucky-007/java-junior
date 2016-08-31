package com.acme.edu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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
            while (true) {
                Socket client = serverSocket.accept();

                try (
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        new BufferedInputStream(
                                                client.getInputStream()
                                        )
                                )
                        )
                ) {
                    while (true) {
                        System.out.println(in.readLine());
                    }
                } catch (SocketException e) {
                    if (e.getMessage().equals("Connection reset")) {
                        System.out.println("Connection closed");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
}