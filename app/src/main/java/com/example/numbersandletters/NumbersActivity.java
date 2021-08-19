package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class NumbersActivity extends AppCompatActivity {

    private Button mHighNumber;
    private Button mLowNumber;
    private Button mContinue;
    private TextView mSelectedNumbers;
    private List<String> selectedNumbers;
    private int[] highNumberArray = {10, 25, 50, 100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mSelectedNumbers = (TextView) findViewById(R.id.tv_selected_numbers);
        mHighNumber = (Button) findViewById(R.id.b_high_number);
        mContinue = (Button) findViewById(R.id.b_continue);

        // Creates the ArrayList that will store the numbers
        selectedNumbers = new ArrayList<String>();

        mHighNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(highNumberArray.length);

                // Adds a random number from our high number array into the ArrayList
                selectedNumbers.add(String.valueOf(highNumberArray[random]));

                // Shows the current set of numbers in the application
                mSelectedNumbers.setText(String.valueOf(selectedNumbers));

                //Toast.makeText(getBaseContext(), String.valueOf(selectedNumbers) , Toast.LENGTH_SHORT ).show();

                // Replaces the high and low buttons with a continue button
                // once enough numbers have been generated
                if(selectedNumbers.size() == 6){
                    mHighNumber.setVisibility(View.INVISIBLE);
                    mLowNumber.setVisibility(View.INVISIBLE);
                    mContinue.setVisibility(View.VISIBLE);
                }
            }
        });

        mLowNumber = (Button) findViewById(R.id.b_low_number);
        mLowNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(9 - 1 + 1) + 1;
                selectedNumbers.add(String.valueOf(random));
                mSelectedNumbers.setText(String.valueOf(selectedNumbers));

                if(selectedNumbers.size() == 6){
                    mHighNumber.setVisibility(View.INVISIBLE);
                    mLowNumber.setVisibility(View.INVISIBLE);
                    mContinue.setVisibility(View.VISIBLE);
                }
            }
        });

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class destinationActivity = NumbersPlayingActivity.class;
                Intent startGame = new Intent(NumbersActivity.this, destinationActivity);

                // https://stackoverflow.com/a/6543850
                // This will transfer the ArrayList of selected numbers onto the next page
                startGame.putStringArrayListExtra("selectedNumbers", (ArrayList<String>) selectedNumbers);

                startActivity(startGame);
            }
        });


    }
}