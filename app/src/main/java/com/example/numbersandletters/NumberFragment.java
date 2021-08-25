package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NumberFragment extends Fragment {

    NumberViewModel numberViewModel;

    public NumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        numberViewModel = new ViewModelProvider(getActivity()).get(NumberViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_number, container, false);

        v.findViewById(R.id.btn_gen_small).setOnClickListener(view -> {
            numberViewModel.genSmall();
        });

        v.findViewById(R.id.btn_gen_large).setOnClickListener(view -> {
            numberViewModel.genLarge();
        });

        return v;
    }

}