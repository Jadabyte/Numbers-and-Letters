package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvGeneratedItems;
    Button btnStartRound;

    NumberViewModel numberViewModel;
    NumberFragment numberFragment;

    private static int MAX_NUMBERS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        numberFragment = new NumberFragment();

        tvGeneratedItems = findViewById(R.id.tv_generated_items);
        btnStartRound = findViewById(R.id.btn_start_round);

        numberViewModel.getNumbers().observe(this, number ->
                tvGeneratedItems.setText(number.toString()));

        numberViewModel.lengthCheck().observe(this, numbersLength -> {
                //TO DO: hide number fragment when enough numbers have been generated
                //TO DO: show a 'start round' button
                if(numbersLength + 1 == MAX_NUMBERS) {
                    getSupportFragmentManager().beginTransaction()
                            .remove(numberFragment)
                            .commit();
                    btnStartRound.setVisibility(View.VISIBLE);
                }
            });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_insert, numberFragment)
                .commit();
    }
}