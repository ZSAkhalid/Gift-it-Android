package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class checkOutPage extends AppCompatActivity {
    private ImageView cardImage;
    private TextView cardTypeTextView;
    private TextView cardPriceTextView;
    private TextView codeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);

        // Get the selected gift card type and price from the previous activity
        Intent intent = getIntent();
        String cardType = intent.getStringExtra("card selected type");
        String cardPrice = intent.getStringExtra("card selected price");

        // Find the views in the layout
        cardImage = findViewById(R.id.cardImageView);
        cardTypeTextView = findViewById(R.id.cardTypeTextView);
        cardPriceTextView = findViewById(R.id.cardPriceTextView);
        codeTextView = findViewById(R.id.cardCodeTextView);

        // Display the card type and price in the UI
        cardTypeTextView.setText(cardType.toUpperCase());
        cardPriceTextView.setText(cardPrice);

        // Display the selected card Image
        displayCard(cardType);

        // Generate a random code for the gift card and display it in the UI
        String code = generateRandomCode();
        codeTextView.setText(code);
    }

    private void displayCard(String cardType) {
        // Set the appropriate image for the gift card based on the selected type
        switch (cardType) {
            case "epicGames":
                cardImage.setImageResource(R.drawable.epic_games);
                break;
            case "playstation":
                cardImage.setImageResource(R.drawable.playstation);
                break;
            case "xbox":
                cardImage.setImageResource(R.drawable.xbox);
                break;
            default: // "android"
                cardImage.setImageResource(R.drawable.android);
                break;
        }
    }

    // Method for generating a random code for the gift card
    private String generateRandomCode() {
        String code = "";
        Random random = new Random();

        // Generate four groups of four characters each
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomInt = random.nextInt(36);
                if (randomInt < 10) {
                    code += randomInt;
                } else {
                    code += (char) (randomInt + 55);
                }
            }
            if (i < 3) {
                code += "-";
            }
        }

        return code;
    }

    // This method is called when the user taps on the "Done" button
    public void doneHandler(View view) {
        // Create an intent to start the HomePage activity
        Intent intent = new Intent(this, HomePage.class);

        // Start the activity
        startActivity(intent);
    }

}
