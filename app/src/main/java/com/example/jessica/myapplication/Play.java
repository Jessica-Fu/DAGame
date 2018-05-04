package com.example.jessica.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Play extends AppCompatActivity {
    private Button playButton;
    Play.ClientHandler clientHandler;
    ClientThread clientThread;
    TextView textViewState, textViewRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playButton = (Button) findViewById(R.id.playButton);
        textViewState = (TextView)findViewById(R.id.state);
        textViewRx = (TextView)findViewById(R.id.received);
        clientHandler = new Play.ClientHandler(this);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startGame();
            }
        });
    }
    public void print(){

        //Log.wtf("myDebugTab", "class startGam enter.");


    }
    public void startGame(){

//        Client new_client = new Client();
//        new_client.main();
        //client();

        clientThread = new ClientThread(
                "172.20.10.8",
                7777,
                clientHandler);
        clientThread.start();
        clientThread.txMsg("msgToSend");
        System.out.println("start~~~");


        Intent intent = new Intent(this,GameBoard.class);
        startActivity(intent);

    }

    public static class ClientHandler extends Handler {
        public static final int UPDATE_STATE = 0;
        public static final int UPDATE_MSG = 1;
        public static final int UPDATE_END = 2;
        private Play parent;

        public ClientHandler(Play parent) {
            super();
            this.parent = parent;
        }

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case UPDATE_STATE:
                    parent.updateState((String)msg.obj);
                    break;
                case UPDATE_MSG:
                    parent.updateRxMsg((String)msg.obj);
                    break;
                case UPDATE_END:
                    parent.clientEnd();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }

    }

    private void updateState(String state){
        textViewState.setText(state);
    }

    private void updateRxMsg(String rxmsg){
        textViewRx.append(rxmsg + "\n");
    }

    private void clientEnd(){
        clientThread = null;
        textViewState.setText("clientEnd()");


    }

    public void client(){
        Log.d("STATE","aaaa");

        System.out.print("client start.");

        String ip = "172.20.10.8";
        int port = 7777;

        try(Socket socket = new Socket(ip, port)){
            // Input and ouput stream
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            // Get input from user
//            Scanner scanner = new Scanner(System.in);

//            // User input
//            System.out.println("Enter the input");
//            String message_send = scanner.nextLine();
            String message_send = "This is client.";

            //Send message to Server
            output.writeUTF(message_send);
//            System.out.printf("Send %s to Server.\n",message_send);
            output.flush();

            // Listen to Server
            while (true){
                if(input.available() > 0){
                    String message_rec = input.readUTF();
//                    System.out.printf("Client Receive: %s. \n", message_rec);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }


}
