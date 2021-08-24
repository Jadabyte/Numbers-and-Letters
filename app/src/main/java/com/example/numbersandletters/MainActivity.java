package com.example.numbersandletters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mStartGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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