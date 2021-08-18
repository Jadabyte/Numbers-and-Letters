package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class NumbersActivity extends AppCompatActivity {

    private Button mHighNumber;
    private Button mLowNumber;
    private TextView mSelectedNumbers;
    private int[] selectedNumbers;
    private int[] highNumberArray = {10, 25, 50, 100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mSelectedNumbers = (TextView) findViewById(R.id.tv_selected_numbers);
        mHighNumber = (Button) findViewById(R.id.b_high_number);

        mHighNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(highNumberArray.length);
                mSelectedNumbers.setText(String.valueOf(highNumberArray[random]));
            }
        });

    }
}