package com.example.numbersandletters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.stream.Collectors;

import be.bluebanana.zakisolver.LetterSolver;
import be.bluebanana.zakisolver.NumberSolver;

import static java.lang.String.valueOf;

public class ScoresFragment extends Fragment {

    MetaViewModel metaViewModel;
    NumberViewModel numberViewModel;
    LetterViewModel letterViewModel;
    final LetterSolver solver = new LetterSolver();
    TextView score1;
    TextView score2;
    TextView tvSolverSolutions;

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
        tvSolverSolutions = v.findViewById(R.id.tv_solver_solutions);

        score1.setText(String.valueOf(metaViewModel.player1Score));
        score2.setText(String.valueOf(metaViewModel.player2Score));
        return v;
    }

    public void wordCheck (String input) {
        List<Character> charsList = input
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        // set up the solver
        solver.setInput(charsList,
                results -> {
                    Log.d("ZAKI", String.format("Found %d matches.", results.size()));

                    if (results.size() == 0) {
                        //results_tv.append("No solutions found.");
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