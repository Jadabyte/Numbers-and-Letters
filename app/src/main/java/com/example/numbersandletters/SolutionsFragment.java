package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import be.bluebanana.zakisolver.NumberSolver;

public class SolutionsFragment extends Fragment {

    MetaViewModel metaViewModel;
    NumberViewModel numberViewModel;
    LetterViewModel letterViewModel;
    EditText answer1;
    EditText answer2;

    public SolutionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        metaViewModel = new ViewModelProvider(getActivity()).get(MetaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_solutions, container, false);

        answer1 = v.findViewById(R.id.et_answer_1);
        answer2 = v.findViewById(R.id.et_answer_2);
        return v;
    }

    public String[] getNumberSolutions(){
        String[] emptyAnswers = null;

        if(checkInputField()){
            String[] answers = {answer1.getText().toString(), answer2.getText().toString()};
            return answers;
        }

        return emptyAnswers;
    }

    public String[] getLetterSolutions(){
        String[] emptyAnswers = null;

        if(checkInputField()){
            String[] answers = {String.valueOf(answer1.getText().toString().length()), String.valueOf(answer2.getText().toString().length())};
            return answers;
        }

        return emptyAnswers;
    }

    public void clearAnswers(){
        answer1.setText("");
        answer2.setText("");
    }

    public boolean checkInputField(){
        boolean check = false;

        if(answer1.getText().toString().trim().equalsIgnoreCase("")){
            answer1.setError("Please provide an answer");
        } else if(answer2.getText().toString().trim().equalsIgnoreCase("")){
            answer2.setError("Please provide an answer");
        } else {
            check = true;
        }

        return check;
    }
}