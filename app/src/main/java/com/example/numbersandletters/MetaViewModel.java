package com.example.numbersandletters;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MetaViewModel extends ViewModel {
    private MutableLiveData<Integer> checkRound;
    public int currentRound = 1;
    public int player1Score;
    public int player2Score;

    public static final int TOTAL_ROUNDS = 6;
    public static final int NUMBERS_ROUND = 0;
    public static final int LETTERS_ROUND_1 = 1;
    public static final int LETTERS_ROUND_2 = 6;

    public MutableLiveData<Integer> getCheckRound() {
        if(checkRound == null){
            checkRound = new MutableLiveData<Integer>(NUMBERS_ROUND);
        }
        return checkRound;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void nextRound(){
        //TODO: After each round, the counter must go up and the correct round type must be shown
        //TODO: Make it so there's 2 number rounds followed by 1 letters round (x2)
        if (currentRound == LETTERS_ROUND_1 || currentRound == LETTERS_ROUND_2){
            getCheckRound().setValue(1);
        }
        else{
            getCheckRound().setValue(0);
        }
        currentRound = currentRound + 1;
    }

    public int scoreCheck(int answer, int goal){
        //TODO: Change this to using abs() for a simpler function (https://www.tutorialspoint.com/java/number_abs.htm)
        int distanceCheck;

        if(answer > goal){
            distanceCheck = answer - goal;
        }
        else{
            distanceCheck = goal - answer;
        }
        return distanceCheck;
    }

    public void updateScores(int answer1, int answer2, int goal){
        if(scoreCheck(answer1, goal) < scoreCheck(answer2, goal)){
            player1Score++;
        }
        else{
            player2Score++;
        }
    }
}
