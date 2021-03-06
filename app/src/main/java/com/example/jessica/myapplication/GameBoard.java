package com.example.jessica.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends AppCompatActivity {
    private Button updateButton;
    private EditText editText;
    private TextView result;
    private int score = 10;
    private String userInput;
    private int userNumber;
    private int ramNumber;
    private int numToServer;
    private int numFromServer;
    public final int MIN = 0;
    public final int MAX = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        updateButton = (Button) findViewById(R.id.updateButton);
        editText = (EditText) findViewById(R.id.editText);
        result= (TextView) findViewById(R.id.winLose);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = editText.getText().toString();
                if (validation(userInput)) {
                   if(compareNum()) {
                       result.setText("You Win!"+ "Number is: " +ramNumber);
                       numToServer = 1;
                   }else{
                       result.setText("You Lose..." + "Number is: " +ramNumber);
                       numToServer = -1;
                   }
                    updateScore();
                }else{
                    result.setText("Please enter the correct integer!");
                }

            }
        });
    }
    // this method is used to validate the user input
    public boolean validation(String userInput){
        if(intValidation(userInput)){
            userNumber = Integer.parseInt(userInput);
            if(rangeValidation(userNumber)){
                return true;
            }
        }
        return false;
    }
    // this method is used to check whether the user input is an int
    public boolean intValidation(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    // this method is used to check whether the user input int is with in the given range
    public boolean rangeValidation(int userNumber){
        if(userNumber>=MIN && userNumber<=MAX){
            return true;
        }else{
            return false;
        }
    }
    // this method is used to compare the user input with system ramdon number
    public boolean compareNum(){
        ramNumber = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
        if(userNumber==ramNumber){
            return true;
        }
        return false;
    }

    // this method is used to update the current score
    public void updateScore(){
        score=score + numToServer; //should be numFromServer,after combined with our client server code;
        String st=String.valueOf(score);
        final TextView textview=(TextView) findViewById(R.id.scoreText);
        textview.setText(st);
    }

}
