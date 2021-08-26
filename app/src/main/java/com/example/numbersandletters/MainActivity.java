package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    TextView tvGeneratedItems;
    TextView tvGoal;
    TextView tvTimer;
    TextView tvPlayerTurn;
    TextView tvScore1;
    TextView tvScore2;
    TextView tvRoundNumber;
    TextView tvRoundType;

    EditText etAnswer1;
    EditText etAnswer2;

    Button btnStartRound;
    Button btnEndRound;
    Button btnNextRound;

    NumberViewModel numberViewModel;
    LetterViewModel letterViewModel;
    MetaViewModel metaViewModel;

    NumberFragment numberFragment;
    LetterFragment letterFragment;

    private final static int TIMER_LENGTH = 1; // Timer length in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * ------------------------------------------
         * Area for defining ViewModels and Fragments
         * ------------------------------------------
         * */
        numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        letterViewModel = new ViewModelProvider(this).get(LetterViewModel.class);
        metaViewModel = new ViewModelProvider(this).get(MetaViewModel.class);
        numberFragment = new NumberFragment();
        letterFragment = new LetterFragment();

        /**
         * ------------------------------------------
         * Area for defining items in views
         * ------------------------------------------
         * */
        tvGeneratedItems = findViewById(R.id.tv_generated_items);
        tvGoal = findViewById(R.id.tv_goal);
        tvTimer = findViewById(R.id.tv_timer);
        tvPlayerTurn = findViewById(R.id.tv_player_choose);
        tvScore1 = findViewById(R.id.tv_score_1);
        tvScore2 = findViewById(R.id.tv_score_2);
        tvRoundNumber = findViewById(R.id.tv_round_number);
        tvRoundType = findViewById(R.id.tv_round_type);

        etAnswer1 = findViewById(R.id.et_answer_1);
        etAnswer2 = findViewById(R.id.et_answer_2);

        btnStartRound = findViewById(R.id.btn_start_round);
        btnEndRound = findViewById(R.id.btn_finish_round);
        btnNextRound = findViewById(R.id.btn_next_round);

        /**
         * ------------------------------------------
         * Rounds logic
         * ------------------------------------------
         * */

        tvPlayerTurn.setText("Player 1");
        numberViewModel.getNumbers().observe(this, number -> {
            tvGeneratedItems.setText(number.toString());

            if(tvPlayerTurn.getText().toString() == "Player 1"){
                tvPlayerTurn.setText("Player 2");
            }
            else{
                tvPlayerTurn.setText("Player 1");
            }
        });

        numberViewModel.lengthCheck().observe(this, numbersLength -> {
            if(numbersLength + 1 == NumberViewModel.MAX_NUMBERS) {
                getSupportFragmentManager().beginTransaction()
                        .remove(numberFragment)
                        .commit();
                btnStartRound.setVisibility(View.VISIBLE);
                tvPlayerTurn.setVisibility(View.INVISIBLE);
            }
        });

        letterViewModel.getLetters().observe(this, character -> {
            tvGeneratedItems.setText(character.toString());

            if(tvPlayerTurn.getText().toString() == "Player 1"){
                tvPlayerTurn.setText("Player 2");
            }
            else{
                tvPlayerTurn.setText("Player 1");
            }
        });

        metaViewModel.getCheckRound().observe(this, round -> {
            if(round == MetaViewModel.NUMBERS_ROUND){
                tvRoundType.setText("Numbers");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_insert, numberFragment)
                        .commit();
            }
            else{
                tvRoundType.setText("Letters");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_insert, letterFragment)
                        .commit();
            }
            tvRoundNumber.setText("Round " + String.valueOf(metaViewModel.currentRound) + ":");
            Toast.makeText(getApplicationContext(), String.valueOf(metaViewModel.currentRound),Toast.LENGTH_SHORT).show();
        });

    }

    public void startRound(View v){
        tvGoal.setText(valueOf(numberViewModel.genGoal()));
        btnStartRound.setVisibility(View.INVISIBLE);
        startTimer();
    }

    public void endRound(View v){
        //TODO: Take the users' answers and send them to the solver
        //TODO: Show solver solutions
        //TODO: Show button for next round
        metaViewModel.updateScores(
                Integer.parseInt(etAnswer1.getText().toString()),
                Integer.parseInt(etAnswer2.getText().toString()),
                numberViewModel.getGoalNumber());

        etAnswer1.setVisibility(View.INVISIBLE);
        tvScore1.append(valueOf(metaViewModel.getPlayer1Score()));
        tvScore1.setVisibility(View.VISIBLE);

        etAnswer2.setVisibility(View.INVISIBLE);
        tvScore2.append(valueOf(metaViewModel.getPlayer2Score()));
        tvScore2.setVisibility(View.VISIBLE);

        btnEndRound.setVisibility(View.INVISIBLE);
        btnNextRound.setVisibility(View.VISIBLE);

    }

    public void nextRound(View v){
        //TODO: Implement nextRound from MetaViewModel
        metaViewModel.nextRound();
        numberViewModel.clearNumbers();
        letterViewModel.clearLetters();
        btnNextRound.setVisibility(View.INVISIBLE);
    }

    public void newGame(View v){
        //TODO: Only usable after final round is completed
        //TODO: Clear all values and return to first screen
    }

    public void startTimer(){
        new CountDownTimer(TIMER_LENGTH * 1000, 1000){
            public void onTick(long millisUntilFinished){
                tvTimer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish(){
                etAnswer1.setVisibility(View.VISIBLE);
                etAnswer2.setVisibility(View.VISIBLE);
                btnEndRound.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}