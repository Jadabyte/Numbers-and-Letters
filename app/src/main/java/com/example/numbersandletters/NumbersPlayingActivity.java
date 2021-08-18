package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_playing);

        ArrayList<String> selectedNumbers = getIntent().getStringArrayListExtra("selectedNumbers");
        Toast.makeText(getBaseContext(), String.valueOf(selectedNumbers) , Toast.LENGTH_SHORT ).show();
    }
}