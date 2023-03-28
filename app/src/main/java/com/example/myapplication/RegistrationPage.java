package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationPage extends AppCompatActivity {
    // Declare class-level variables
    private EditText name;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        // Initialize Firebase authentication instance
        mAuth = FirebaseAuth.getInstance();

        // Get references to the name, email, and password input fields
        name = findViewById(R.id.nameID);
        email = findViewById(R.id.emailID);
        password = findViewById(R.id.passwordID);
    }

    public void signUp(View view) {
        // Get the text entered by the user in the input fields
        String nameString = name.getText().toString().trim();
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        if (!validateInputFields()) {
            // If the input fields are not filled properly, return without creating a new user account
            return;
        }

        // Create a new user account in Firebase authentication with the email and password
        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If account creation is successful, save the user data to the Firebase Realtime Database
                            saveUserToDatabase(nameString, emailString, passwordString);
                            moveToLogIn();
                        } else {
                            // Error
                        }
                    }
                });
    }
    private void saveUserToDatabase(String name, String email, String password) {
        String userName = name;
        String useremail = email;
        String userpassword = password;
        // Get a reference to the Firebase Realtime Database
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        // Get the ID of the current user
        String userId = mAuth.getCurrentUser().getUid();
        // Create a new User object with the name, email, and password
        User user = new User(userName, useremail, userpassword);
        // Save the User object to the "users" node in the database with the current user ID as the key
        mDatabase.child("users").child(userId).setValue(user);
    }


    private void moveToLogIn() {
        Intent logInPage = new Intent(this, MainActivity.class);
        startActivity(logInPage);
    }

    private boolean validateInputFields() {
        // Get the text entered by the user in the input fields
        String nameString = name.getText().toString().trim();
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();

        // Check if the name field is empty
        if (TextUtils.isEmpty(nameString)) {
            // Request focus on the name field
            name.requestFocus();
            // Display a toast message to the user
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if the email field is empty or not a valid email address
        if (TextUtils.isEmpty(emailString) || !Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            // Request focus on the email field
            email.requestFocus();
            // Display a toast message to the user
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if the password field is empty or has less than 6 characters
        if (TextUtils.isEmpty(passwordString) || passwordString.length() < 6) {
            // Request focus on the password field
            password.requestFocus();
            // Display a toast message to the user
            Toast.makeText(this, "Please enter a password with at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        // If all input fields are filled properly, return true
        return true;
    }



}