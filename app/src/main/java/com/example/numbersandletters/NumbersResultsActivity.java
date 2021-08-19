package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersResultsActivity extends AppCompatActivity {

    private TextView mAnswer1;
    private TextView mAnswer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_results);

        ArrayList<String> selectedNumbers = getIntent().getStringArrayListExtra("selectedNumbers");
        int Player1Answer = getIntent().getIntExtra("Player1Answer", 0);
        int Player2Answer = getIntent().getIntExtra("Player2Answer", 0);

        mAnswer1 = (TextView) findViewById(R.id.tv_result_1);
        mAnswer2 = (TextView) findViewById(R.id.tv_result_2);

        mAnswer1.setText(String.valueOf(Player1Answer));
        mAnswer2.setText(String.valueOf(Player2Answer));

        Toast.makeText(getBaseContext(), String.valueOf(selectedNumbers) , Toast.LENGTH_SHORT ).show();
        //Toast.makeText(getBaseContext(), Player1Answer , Toast.LENGTH_SHORT ).show();
        //Toast.makeText(getBaseContext(), Player2Answer , Toast.LENGTH_SHORT ).show();
    }
}