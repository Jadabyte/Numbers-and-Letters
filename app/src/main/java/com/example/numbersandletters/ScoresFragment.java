package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.lang.String.valueOf;

public class ScoresFragment extends Fragment {

    MetaViewModel metaViewModel;
    TextView score1;
    TextView score2;

    public ScoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scores, container, false);
        metaViewModel = new ViewModelProvider(getActivity()).get(MetaViewModel.class);

        score1 = v.findViewById(R.id.tv_score_1);
        score2 = v.findViewById(R.id.tv_score_2);

        score1.setText(String.valueOf(metaViewModel.player1Score));
        score2.setText(String.valueOf(metaViewModel.player2Score));
        return v;
    }
}