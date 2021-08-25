package com.example.numbersandletters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class NumberViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Integer>> numbers;
    private MutableLiveData<Integer> numbersLength;

    private static final int SMALL_NUMBERS_CEIL = 9;
    private static final int SMALL_NUMBERS_FLOOR = 1;
    private static final int[] LARGE_NUMBERS_AVAIL = {10, 25, 50, 100};
    private static final int MAX_NUMBERS = 6;

    public MutableLiveData<ArrayList<Integer>> getNumbers(){
        if(numbers == null){
            numbers = new MutableLiveData<ArrayList<Integer>>();
            numbers.setValue(new ArrayList<Integer>());
        }
        return numbers;
    }

    public void genSmall(){
        if(lengthCheck().getValue() < MAX_NUMBERS) {
            ArrayList<Integer> generatedNumbers = getNumbers().getValue();
            // A 1 is added in this instance since java's random number generation is inclusive of the floor but not the ceiling
            generatedNumbers.add(new Random().nextInt(SMALL_NUMBERS_CEIL - SMALL_NUMBERS_FLOOR + 1) + SMALL_NUMBERS_FLOOR);
            numbers.setValue(generatedNumbers);
        }
    }

    public void genLarge(){
        if(lengthCheck().getValue() < MAX_NUMBERS) {
            ArrayList<Integer> generatedNumbers = getNumbers().getValue();
            generatedNumbers.add(LARGE_NUMBERS_AVAIL[new Random().nextInt(LARGE_NUMBERS_AVAIL.length)]);
            numbers.setValue(generatedNumbers);
        }
    }

    public MutableLiveData<Integer> lengthCheck(){
        int length = getNumbers().getValue().size();
        if(numbersLength == null){
            numbersLength = new MutableLiveData<Integer>();
            numbersLength.setValue(length);
        }
        numbersLength.setValue(length);
        return numbersLength;
    }
}
