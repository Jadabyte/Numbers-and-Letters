package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NumbersPlayingActivity extends AppCompatActivity {

    private TextView mGoalNumber;
    private TextView mSelectedNumbers;
    private TextView mTimer;
    private EditText mAnswer1;
    private EditText mAnswer2;
    private Button mConfirm;
    private int goalNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_playing);

        // Translates the transferred intent into a usable variable
        ArrayList<Integer> selectedNumbers = getIntent().getIntegerArrayListExtra("selectedNumbers");

        mGoalNumber = (TextView) findViewById(R.id.tv_goal_number);
        mSelectedNumbers = (TextView) findViewById(R.id.tv_selected_numbers);
        mTimer = (TextView) findViewById(R.id.tv_timer);

        mConfirm = (Button) findViewById(R.id.b_confirm);

        mAnswer1 = (EditText) findViewById(R.id.et_answer_1);
        mAnswer2 = (EditText) findViewById(R.id.et_answer_2);

        // Generates a random number and places it in the view
        goalNumber = new Random().nextInt(1000 - 100 + 1) + 100;
        mGoalNumber.setText(String.valueOf(goalNumber));

        // Places the selected numbers into the view
        mSelectedNumbers.setText(String.valueOf(selectedNumbers));

        new CountDownTimer(2000, 1000){ // Change this back to 30 seconds
            public void onTick(long millisUntilFinished){
                mTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish(){
                mTimer.setText("done!");
                mConfirm.setVisibility(View.VISIBLE);
            }
        }.start();

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Player1Answer = Integer.parseInt(mAnswer1.getText().toString());
                int Player2Answer = Integer.parseInt(mAnswer2.getText().toString());

                Class destinationActivity = NumbersResultsActivity.class;
                Intent showResults = new Intent(NumbersPlayingActivity.this, destinationActivity);

                showResults.putExtra("Player1Answer", Player1Answer);
                showResults.putExtra("Player2Answer", Player2Answer);
                showResults.putExtra("goalNumber", goalNumber);
                showResults.putIntegerArrayListExtra("selectedNumbers", (ArrayList<Integer>) selectedNumbers);

                startActivity(showResults);
            }
        });
    }
}