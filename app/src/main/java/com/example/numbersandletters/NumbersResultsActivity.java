package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import be.bluebanana.zakisolver.NumberSolver;
import be.bluebanana.zakisolver.Solver;

public class NumbersResultsActivity extends AppCompatActivity {

    final NumberSolver solver = new NumberSolver();
    private TextView mScore1;
    private TextView mScore2;
    private TextView mSolverResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_results);

        int Player1Answer = getIntent().getIntExtra("Player1Answer", 0);
        int Player2Answer = getIntent().getIntExtra("Player2Answer", 0);

        int answer1 = scoreCheck(Player1Answer);
        int answer2 = scoreCheck(Player2Answer);

        int[] scores = winnerCheck(answer1, answer2);

        mScore1 = (TextView) findViewById(R.id.tv_result_1);
        mScore2 = (TextView) findViewById(R.id.tv_result_2);

        mScore1.setText(String.valueOf(scores[0]));
        mScore2.setText(String.valueOf(scores[1]));
    }

    int scoreCheck(int answer){
        int goalNumber = getIntent().getIntExtra("goalNumber", 0);
        int distanceCheck;

        if(answer > goalNumber){
            distanceCheck = answer - goalNumber;
        }
        else{
            distanceCheck = goalNumber - answer;
        }
        return distanceCheck;
    }

    int[] winnerCheck(int answer1, int answer2){
        int player1score = getIntent().getIntExtra("player1Score", 0);
        int player2score = getIntent().getIntExtra("player2Score", 0);

        if(answer1 < answer2){
            player1score++;
        }
        else{
            player2score++;
        }

        int[] scores = new int[2];
        scores[0] = player1score;
        scores[1] = player2score;

        return scores;
    }



}