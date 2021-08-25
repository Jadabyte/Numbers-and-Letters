package com.example.numbersandletters;

import android.os.CountDownTimer;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MetaViewModel extends ViewModel {
    private MutableLiveData<Integer> currentRound;

    public static final int ROUND_NUMBERS = 0;
    public static final int ROUND_LETTERS = 1;

    public MutableLiveData<Integer> getCurrentRound() {
        if(currentRound == null){
            currentRound = new MutableLiveData<Integer>(ROUND_NUMBERS)
        }

        return currentRound;
    }
}
