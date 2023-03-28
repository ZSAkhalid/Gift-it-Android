package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements OnCompleteListener<AuthResult> {
    // Define the input taken from the user to retrieve it
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the username and password EditText fields
        email = findViewById(R.id.emailID);
        password = findViewById(R.id.passwordID);

        // Get the instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
    }

    // Called when the user taps the sign in button
    public void signIn(View view) {
        // Get the email and password entered by the user
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        // Check if email or password is empty
        if (emailString.isEmpty() || passwordString.isEmpty()) {
            Toast.makeText(this, "Username or password is empty", Toast.LENGTH_SHORT).show();
        } else {
            // Use FirebaseAuth to sign in the user with email and password
            mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, this);
        }
    }

    // Called when the user taps the create account button
    public void createAccount(View view) {
        Intent registrationPage = new Intent(this, RegistrationPage.class);
        startActivity(registrationPage);
    }

    // Listener called when the sign in task is complete
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        // Check if the sign in task was successful
        if (task.isSuccessful()) {
            // Sign in success, show a message to the user
            Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show();
            Intent mainPage = new Intent(this, HomePage.class);
            startActivity(mainPage);
        } else {
            // Sign in failed, show a message to the user
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
        }
    }
}