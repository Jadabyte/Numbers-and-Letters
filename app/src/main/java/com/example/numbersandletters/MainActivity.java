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
    TextView tvRoundNumber;
    TextView tvRoundType;

    Button btnStartRound;
    Button btnEndRound;
    Button btnNextRound;

    NumberViewModel numberViewModel;
    LetterViewModel letterViewModel;
    MetaViewModel metaViewModel;

    NumberFragment numberFragment;
    LetterFragment letterFragment;
    SolutionsFragment solutionsFragment;
    ScoresFragment scoresFragment;

    private final static int TIMER_LENGTH = 1; // Timer length in seconds
    private final static String ROUND_TYPE_1 = "Numbers";
    private final static String ROUND_TYPE_2 = "Letters";

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
        solutionsFragment = new SolutionsFragment();
        scoresFragment = new ScoresFragment();

        /**
         * ------------------------------------------
         * Area for defining items in views
         * ------------------------------------------
         * */
        tvGeneratedItems = findViewById(R.id.tv_generated_items);
        tvGoal = findViewById(R.id.tv_goal);
        tvTimer = findViewById(R.id.tv_timer);
        tvPlayerTurn = findViewById(R.id.tv_player_choose);
        tvRoundNumber = findViewById(R.id.tv_round_number);
        tvRoundType = findViewById(R.id.tv_round_type);

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
            playerSwitch();
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
            playerSwitch();
        });

        letterViewModel.lengthCheck().observe(this, numbersLength -> {
            if(numbersLength + 1 == LetterViewModel.MAX_LETTERS) {
                getSupportFragmentManager().beginTransaction()
                        .remove(letterFragment)
                        .commit();
                btnStartRound.setVisibility(View.VISIBLE);
                tvPlayerTurn.setVisibility(View.INVISIBLE);
            }
        });

        metaViewModel.getCheckRound().observe(this, round -> {
            if(round == MetaViewModel.NUMBERS_ROUND){
                tvRoundType.setText(ROUND_TYPE_1);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_insert, numberFragment)
                        .commit();
            }
            else{
                tvRoundType.setText(ROUND_TYPE_2);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_insert, letterFragment)
                        .commit();
            }
            tvRoundNumber.setText("Round " + String.valueOf(metaViewModel.currentRound) + ":");
        });

    }

    public void startRound(View v){
        if(tvRoundType.getText() == ROUND_TYPE_1) {
            tvGoal.setText(valueOf(numberViewModel.genGoal()));
        }
        btnStartRound.setVisibility(View.INVISIBLE);
        startTimer();
    }

    public void endRound(View v){
        //TODO: Take the users' answers and send them to the solver
        //TODO: Show solver solutions
        //TODO: Show button for next round
        if(tvRoundType.getText() == ROUND_TYPE_1) {
            try {
                metaViewModel.updateScores(
                        Integer.parseInt(solutionsFragment.getNumberSolutions()[0]),
                        Integer.parseInt(solutionsFragment.getNumberSolutions()[1]),
                        numberViewModel.getGoalNumber());
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }
        else{
            try {
                metaViewModel.updateScores(
                        Integer.parseInt(solutionsFragment.getLetterSolutions()[0]),
                        Integer.parseInt(solutionsFragment.getLetterSolutions()[1]),
                        letterViewModel.MAX_LETTERS);
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }

        solutionsFragment.clearAnswers();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_insert, scoresFragment)
                .commit();

        btnEndRound.setVisibility(View.INVISIBLE);
        btnNextRound.setVisibility(View.VISIBLE);

    }

    public void nextRound(View v){
        //TODO: Implement nextRound from MetaViewModel
        metaViewModel.nextRound();
        numberViewModel.clearNumbers();
        tvGoal.setText("");
        numberViewModel.clearGoal();
        letterViewModel.clearLetters();
        tvPlayerTurn.setVisibility(View.VISIBLE);
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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_insert, solutionsFragment)
                        .commit();
                btnEndRound.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playerSwitch(){
        if(tvPlayerTurn.getText().toString() == "Player 1"){
            tvPlayerTurn.setText("Player 2");
        }
        else{
            tvPlayerTurn.setText("Player 1");
        }
    }
}