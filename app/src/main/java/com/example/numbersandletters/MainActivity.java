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

    NumberViewModel numberViewModel;
    NumberFragment numberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberViewModel = new ViewModelProvider(this).get(NumberViewModel.class);
        numberFragment = new NumberFragment();

        tvGeneratedItems = findViewById(R.id.tv_generated_items);

        numberViewModel.getNumbers().observe(this, number ->
                tvGeneratedItems.setText(number.toString()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_insert, numberFragment)
                .commit();
        /*
        Fragment numbersFragment = new numbersFragment();
        Fragment lettersFragment = new lettersFragment();

        mStartGameButton = (Button) findViewById(R.id.b_start_game);

        mStartGameButton.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.numbersFragmentLayout, numbersFragment)
                    .addToBackStack(null)
                    .commit();
        });*/
    }
}