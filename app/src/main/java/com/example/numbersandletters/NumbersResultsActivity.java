package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import be.bluebanana.zakisolver.NumberSolver;
import be.bluebanana.zakisolver.Solver;

public class NumbersResultsActivity extends AppCompatActivity {

    final NumberSolver solver = new NumberSolver();
    private TextView mAnswer1;
    private TextView mAnswer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_results);

        int Player1Answer = getIntent().getIntExtra("Player1Answer", 0);
        int Player2Answer = getIntent().getIntExtra("Player2Answer", 0);
        //int goalNumber = getIntent().getIntExtra("goalNumber", 0);

        mAnswer1 = (TextView) findViewById(R.id.tv_result_1);
        mAnswer2 = (TextView) findViewById(R.id.tv_result_2);

        mAnswer1.setText(String.valueOf(Player1Answer));
        mAnswer2.setText(String.valueOf(Player2Answer));

        //Toast.makeText(getBaseContext(), Player1Answer , Toast.LENGTH_SHORT ).show();
        //Toast.makeText(getBaseContext(), Player2Answer , Toast.LENGTH_SHORT ).show();
            ArrayList<Integer> selectedNumbers = getIntent().getIntegerArrayListExtra("selectedNumbers");
            int goalNumber = getIntent().getIntExtra("goalNumber", 0);

            solver.setInput(selectedNumbers, goalNumber, results -> {
                Log.d("ZAKI", String.format("Found %d matches.", results.size()));

                if(results.size() == 0){

                }
                results.stream()
                        .limit(10)
                        .forEach(result -> Log.d("TAG", String.format("%S\n", result)));
            });

            new Thread(solver).start();
    }


}