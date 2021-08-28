package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StartFragment extends Fragment {

    MetaViewModel metaViewModel;
    TextView tvName1;
    TextView tvName2;

    public StartFragment() {
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
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        tvName1 = v.findViewById(R.id.tv_name_1);
        tvName2 = v.findViewById(R.id.tv_name_2);

        return v;
    }

    public String[] registerUserNames(){
        String[] userNames = {tvName1.getText().toString(), tvName2.getText().toString()};
        return userNames;
    }
}