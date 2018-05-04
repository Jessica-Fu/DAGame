package com.example.jessica.myapplication;

import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;


public class Client {

    public void main(){
        //TextView server;
        //server= (TextView) findViewById(serverConnection);
       // server.setText("Client is running");
        String ip = "10.0.2.2";
        int port = 8080;

        System.out.print("Client start.");

        //Log.wtf("myDebug", "Client start.");

        try(Socket socket = new Socket(ip, port)){
            // Input and ouput stream
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            // Get input from user
            Scanner scanner = new Scanner(System.in);

            // User input
//            System.out.println("Enter the input");
//            String message_send = scanner.nextLine();
            String message_send = "This is client";

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
