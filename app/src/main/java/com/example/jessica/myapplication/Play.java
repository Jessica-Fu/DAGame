package com.example.jessica.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Play extends AppCompatActivity {
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playButton = (Button) findViewById(R.id.playButton);
        print();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }
    public void print(){
        System.out.println("start~~~");
        //Log.wtf("myDebugTab", "class startGam enter.");


    }
    public void startGame(){

        Client new_client = new Client();
        new_client.main();

        Intent intent = new Intent(this,GameBoard.class);
        startActivity(intent);

    }

//    public void client(){
//        Log.d("STATE","aaaa");
//
//        System.out.print("client start.");
//
//        String ip = "127.0.0.1";
//        int port = 9876;
//
//        try(Socket socket = new Socket(ip, port)){
//            // Input and ouput stream
//            DataInputStream input = new DataInputStream(socket.getInputStream());
//            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
//            // Get input from user
////            Scanner scanner = new Scanner(System.in);
//
////            // User input
////            System.out.println("Enter the input");
////            String message_send = scanner.nextLine();
//            String message_send = "This is client.";
//
//            //Send message to Server
//            output.writeUTF(message_send);
////            System.out.printf("Send %s to Server.\n",message_send);
//            output.flush();
//
//            // Listen to Server
//            while (true){
//                if(input.available() > 0){
//                    String message_rec = input.readUTF();
////                    System.out.printf("Client Receive: %s. \n", message_rec);
//                }
//            }
//        }catch (IOException e){
//            System.out.println(e);
//        }
//    }


}
