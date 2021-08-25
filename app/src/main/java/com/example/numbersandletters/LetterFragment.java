package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LetterFragment extends Fragment {

    LetterViewModel letterViewModel;

    public LetterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        letterViewModel = new ViewModelProvider(getActivity()).get(LetterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_letter, container, false);

        v.findViewById(R.id.btn_gen_consonant).setOnClickListener(view -> {
            letterViewModel.genConsonant();
        });

        v.findViewById(R.id.btn_gen_vowel).setOnClickListener(view -> {
            letterViewModel.genVowel();
        });

        return v;
    }
}