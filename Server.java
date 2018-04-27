package com.company;

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
        int port = 1342;
        ServerSocketFactory factory = ServerSocketFactory.getDefault();
        try(ServerSocket server = factory.createServerSocket(port)){
            // Wait for client connection
            while(true){
                Socket client = server.accept();
                //Start a new thread for a connection
                Thread t = new Thread(()->{
                    try{
                        server_client(client);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                t.start();

            }
        }
    }

    private static void server_client(Socket client){
        try(Socket client_socket = client){
            // Input Stream
            DataInputStream input = new DataInputStream(client_socket.getInputStream());
            // Output Stream
            DataOutputStream output = new DataOutputStream(client_socket.getOutputStream());

            //Get input from client
            String message_input = input.readUTF();
            System.out.printf("Receive: \"%s\" from client. \n", message_input);


            String message_output = "Server has received \""+message_input+"\"";

            // Send the message to client
            output.writeUTF(message_output);
            System.out.println("Send message back to client. \n");
            output.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
