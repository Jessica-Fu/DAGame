package com.example.jessica.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity {
    private Button updateButton;
    private int score = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore();
            }
        });
    }
    public void updateScore(){
        score=score + 1;
        String st=String.valueOf(score);
        final TextView textview=(TextView) findViewById(R.id.scoreText);
        textview.setText(st);
    }

}
