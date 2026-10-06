package com.example.formapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class validationActivity extends AppCompatActivity {

    EditText codeInput;
    Button validateButton;
    int validateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_validation);

        codeInput = findViewById(R.id.codeInput);
        validateButton = findViewById(R.id.validateButton);

        validateCode =
                getIntent().getIntExtra("validationCode", 0);

        validateButton.setOnClickListener(v -> validateCode());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });


    }

    private void validateCode() {

        String enteredCode = codeInput.getText().toString();

        if (enteredCode.equals(String.valueOf(validateCode))) {

            Toast.makeText(
                    this,
                    "Account validated successfully!",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            Toast.makeText(
                    this,
                    "Incorrect validation code",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
