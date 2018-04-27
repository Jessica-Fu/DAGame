package com.example.jessica.myapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by jessica on 4/27/18.
 */

public class Task implements Runnable {
    private Socket client;

    public Task(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        server_client(client);
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
