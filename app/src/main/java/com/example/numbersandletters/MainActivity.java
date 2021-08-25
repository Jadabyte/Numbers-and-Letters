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

public class MainActivity extends AppCompatActivity {

    TextView tvGeneratedItems;
    TextView tvGoal;
    TextView tvTimer;
    TextView tvPlayerTurn;

    EditText etAnswer1;
    EditText etAnswer2;

    Button btnStartRound;
    Button btnEndRound;

    NumberViewModel numberViewModel;
    LetterViewModel letterViewModel;
    MetaViewModel metaViewModel;

    NumberFragment numberFragment;

    private final static int MAX_NUMBERS = 6;
    private final static int TIMER_LENGTH = 5; // Timer length in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        letterViewModel = new ViewModelProvider(this).get(LetterViewModel.class);
        metaViewModel = new ViewModelProvider(this).get(MetaViewModel.class);
        numberFragment = new NumberFragment();

        tvGeneratedItems = findViewById(R.id.tv_generated_items);
        tvGoal = findViewById(R.id.tv_goal);
        tvTimer = findViewById(R.id.tv_timer);
        tvPlayerTurn = findViewById(R.id.tv_player_choose);

        etAnswer1 = findViewById(R.id.et_answer_1);
        etAnswer2 = findViewById(R.id.et_answer_2);

        btnStartRound = findViewById(R.id.btn_start_round);
        btnEndRound = findViewById(R.id.btn_finish_round);

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
                //TO DO: hide number fragment when enough numbers have been generated
                //TO DO: show a 'start round' button
                if(numbersLength + 1 == MAX_NUMBERS) {
                    getSupportFragmentManager().beginTransaction()
                            .remove(numberFragment)
                            .commit();
                    btnStartRound.setVisibility(View.VISIBLE);
                    tvPlayerTurn.setVisibility(View.INVISIBLE);
                }
                });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_insert, numberFragment)
                .commit();
    }

    public void startRound(View v){
        tvGoal.setText(String.valueOf(numberViewModel.genGoal()));
        btnStartRound.setVisibility(View.INVISIBLE);
        startTimer();
    }

    public void endRound(View v){
        //TODO: Take the users' answers and send them to the solver
        //TODO: Show solver solutions
        //TODO: Show button for next round
    }

    public void nextRound(View v){
        //TODO: Implement nextRound from MetaViewModel
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