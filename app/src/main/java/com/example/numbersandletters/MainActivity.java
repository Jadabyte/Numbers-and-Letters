package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mStartGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartGameButton = (Button) findViewById(R.id.b_start_game);

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class destinationActivity = NumbersActivity.class;
                Intent startGame = new Intent(MainActivity.this, destinationActivity);
                startActivity(startGame);
            }
        });
    }
}