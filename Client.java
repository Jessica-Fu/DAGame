package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static  void main(String args[]){
        String ip = "127.0.0.1";
        int port = 1342;

        try(Socket socket = new Socket(ip, port)){
            // Input and ouput stream
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            // Get input from user
            Scanner scanner = new Scanner(System.in);

            // User input
            System.out.println("Enter the input");
            String message_send = scanner.nextLine();

            //Send message to Server
            output.writeUTF(message_send);
            System.out.printf("Send %s to Server.\n",message_send);
            output.flush();

            // Listen to Server
            while (true){
                if(input.available() > 0){
                    String message_rec = input.readUTF();
                    System.out.printf("Client Receive: %s. \n", message_rec);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

}
