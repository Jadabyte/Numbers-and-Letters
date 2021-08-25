package com.example.numbersandletters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class LetterViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Character>> letters;

    public MutableLiveData<ArrayList<Character>> getLetters(){
        if(letters == null){
            letters = new MutableLiveData<ArrayList<Character>>();
            letters.setValue(new ArrayList<Character>());
        }
        return letters;
    }

    public void genVowel(){
        ArrayList<Character> generatedLetters = getLetters().getValue();
        char generatedLetter;
        do {
            generatedLetter = genLetter();
        } while (!checkVowel(generatedLetter));
        generatedLetters.add(generatedLetter);
        letters.setValue(generatedLetters);
    }

    public void genConsonant(){
        ArrayList<Character> generatedLetters = getLetters().getValue();
        char generatedLetter;
        do{
            generatedLetter = genLetter();
        } while (!checkConsonant(generatedLetter));
        generatedLetters.add(generatedLetter);
        letters.setValue(generatedLetters);
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
}