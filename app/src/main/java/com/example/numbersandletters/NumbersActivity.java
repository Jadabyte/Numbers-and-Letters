package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

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
        selectedNumbers = new ArrayList<String>();

        mHighNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(highNumberArray.length);
                selectedNumbers.add(String.valueOf(highNumberArray[random]));
                mSelectedNumbers.setText(String.valueOf(selectedNumbers));

                //Toast.makeText(getBaseContext(), String.valueOf(selectedNumbers) , Toast.LENGTH_SHORT ).show();
                if(selectedNumbers.size() == 9){
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

                if(selectedNumbers.size() == 9){
                    mHighNumber.setVisibility(View.INVISIBLE);
                    mLowNumber.setVisibility(View.INVISIBLE);
                    mContinue.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}