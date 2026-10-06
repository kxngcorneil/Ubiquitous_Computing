package com.example.formapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    EditText passwordInput;
    EditText phoneInput;
    EditText emailInput;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        passwordInput = findViewById(R.id.passwordInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailInput);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> submitForm());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void submitForm(){
        String name = nameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();

        // Check for empty fields
        if (name.isEmpty() || password.isEmpty() ||
                phone.isEmpty() || email.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            nameInput.setError("Name can only contain letters");
            return;
        }

        if (!email.contains("@")) {

            emailInput.setError(
                    "Enter a valid email address"
            );

            return;
        }

       Intent intent = new Intent(MainActivity.this, thankYouActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
    }

