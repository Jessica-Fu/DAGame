package com.example.jessica.myapplication;


import com.example.jessica.myapplication.Task;

import javax.net.ServerSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
    First run the Server
*/
public class Server {
    public static  void main(String args[]) throws IOException{
        int port = 9876;
        ServerSocketFactory factory = ServerSocketFactory.getDefault();
        try(ServerSocket server = factory.createServerSocket(port)){
            // Wait for client connection
            while(true){
                Socket client = server.accept();
                //Start a new thread for a connection
                Task task = new Task(client);
                Thread t = new Thread(task);
                t.start();

            }
        }
    }


}
