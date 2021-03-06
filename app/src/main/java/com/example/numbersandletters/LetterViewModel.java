package com.example.numbersandletters;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

import be.bluebanana.zakisolver.LetterSolver;
import be.bluebanana.zakisolver.NumberSolver;

public class LetterViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Character>> letters;
    private MutableLiveData<Integer> lettersLength;

    public static final int MAX_LETTERS = 9;
    final LetterSolver solver = new LetterSolver();

    public MutableLiveData<ArrayList<Character>> getLetters(){
        if(letters == null){
            letters = new MutableLiveData<ArrayList<Character>>();
            letters.setValue(new ArrayList<Character>());
        }
        return letters;
    }

    public void genVowel(){
        if(lengthCheck().getValue() < MAX_LETTERS) {
            ArrayList<Character> generatedLetters = getLetters().getValue();
            char generatedLetter;
            do {
                generatedLetter = genLetter();
            } while (!checkVowel(generatedLetter));
            generatedLetters.add(generatedLetter);
            letters.setValue(generatedLetters);
        }
    }

    public void genConsonant(){
        if(lengthCheck().getValue() < MAX_LETTERS) {
            ArrayList<Character> generatedLetters = getLetters().getValue();
            char generatedLetter;
            do {
                generatedLetter = genLetter();
            } while (!checkConsonant(generatedLetter));
            generatedLetters.add(generatedLetter);
            letters.setValue(generatedLetters);
        }
    }

    public char genLetter(){
        int ascii = new Random().nextInt(26) + 65;
        return (char)ascii;
    }

    private boolean checkVowel(char generatedLetter){
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};

        for(char vowel: vowels){
            if(generatedLetter == vowel){
                return true;
            }
        }
        return false;
    }

    private boolean checkConsonant(char generatedLetter){
        return !checkVowel(generatedLetter);
    }

    //TODO: Think about moving this to the MetaViewModel since it's the same function for both letters and numbers
    public MutableLiveData<Integer> lengthCheck(){
        int length = getLetters().getValue().size();
        if(lettersLength == null){
            lettersLength = new MutableLiveData<Integer>();
            lettersLength.setValue(length);
        }
        lettersLength.setValue(length);
        return lettersLength;
    }

    public void clearLetters(){
        ArrayList<Character> generatedLetters = getLetters().getValue();
        generatedLetters.clear();
        letters.setValue(generatedLetters);
    }

    public void letterSolver (View v) {
        // set up the solver
        solver.loadDictionary(v.getContext(), R.raw.dictionary_nl);
        solver.setInput(letters.getValue(),
                results -> {
                    Log.d("ZAKI", String.format("Found %d matches.", results.size()));

                    if (results.size() == 0) {
                        Log.d("Warn", "No solutions found.");
                        return;
                    }
                    results.stream()
                            .limit(10)
                            .forEach(result -> Log.d("TAG", String.format("%s (%d)\n", result, result.length())));
                });

        // Start the solver
        new Thread(solver).start();
    }
}