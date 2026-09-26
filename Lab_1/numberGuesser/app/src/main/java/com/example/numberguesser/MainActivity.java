package com.example.numberguesser;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText guessInput;
    Button guessButton;
    Button playAgainButton;

    TextView resultText;
    TextView guessCountText;

    int secretNumber;
    int guessCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        guessInput = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.guessButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        resultText = findViewById(R.id.resultText);
        guessCountText = findViewById(R.id.guessCountText);

        startGame();

        playAgainButton.setOnClickListener(v -> {
            guessButton.setEnabled(true);
            startGame();
        });

        guessButton.setOnClickListener(v -> checkGuess());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void startGame(){
        Random random = new Random();
        secretNumber = random.nextInt(30);
        guessCount = 0;
        resultText.setText("Make a guess");
        guessCountText.setText("Guesses: 0");

        guessInput.setText( "");
        playAgainButton.setVisibility(Button.GONE);
    }

    private void checkGuess(){
        int userGuess = Integer.parseInt(guessInput.getText().toString());
        guessCount++;
        guessCountText.setText("Guesses: " + guessCount);

        if(userGuess == secretNumber) {
            resultText.setText("Correct");

            guessButton.setEnabled(false);
            playAgainButton.setVisibility(Button.VISIBLE);
        } else if (userGuess < secretNumber){
            resultText.setText("Higher");
        } else{
            resultText.setText("Lower");
        }
    }
}

