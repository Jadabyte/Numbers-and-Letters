package com.example.numbersandletters;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MetaViewModel extends ViewModel {
    private MutableLiveData<Integer> currentRound;
    private int player1Score;
    private int player2Score;

    public static final int TOTAL_ROUNDS = 6;
    public static final int NUMBERS_ROUND = 0;
    public static final int LETTERS_ROUND_1 = 3;
    public static final int LETTERS_ROUND_2 = 6;

    public MutableLiveData<Integer> getCurrentRound() {
        if(currentRound == null){
            currentRound = new MutableLiveData<Integer>(NUMBERS_ROUND);
        }
        return currentRound;
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
        int round = getCurrentRound().getValue();

        if(round < TOTAL_ROUNDS){
            round++;
        }
        currentRound.setValue(round);
    }

    public int scoreCheck(int answer, int goal){
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
