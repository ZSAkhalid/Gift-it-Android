package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CardsPage extends AppCompatActivity {
    // Define the image views for the gift cards
    private ImageView tenSar;
    private ImageView twentySar;
    private ImageView fiftySar;
    private ImageView oneHundredHSar;

    // Store the selected gift card type
    String cardSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the layout for displaying the gift cards
        setContentView(R.layout.activity_cards_page);

        // Find the image views for the gift cards in the layout
        tenSar = findViewById(R.id.tenSarID);
        twentySar = findViewById(R.id.twentySarID);
        fiftySar = findViewById(R.id.fiftySarID);
        oneHundredHSar = findViewById(R.id.oneHundredHSarID);

        // Set the images based on the user's choice
        setCardsImages();

    }

    private void setCardsImages(){
        // Retrieve the selected gift card type from the previous activity
        cardSelected = getIntent().getStringExtra("card selected");

        // Set the appropriate image for each gift card based on the selected type
        switch (cardSelected) {
            case "epicGames":
                tenSar.setImageResource(R.drawable.epic_games10sar);
                twentySar.setImageResource(R.drawable.epic_games20sar);
                fiftySar.setImageResource(R.drawable.epic_games50sar);
                oneHundredHSar.setImageResource(R.drawable.epic_games10sar);
                break;
            case "playstation":
                tenSar.setImageResource(R.drawable.playstation10sar);
                twentySar.setImageResource(R.drawable.playstation20sar);
                fiftySar.setImageResource(R.drawable.playstation50sar);
                oneHundredHSar.setImageResource(R.drawable.playstation100sar);
                break;
            case "xbox":
                tenSar.setImageResource(R.drawable.xbox10sar);
                twentySar.setImageResource(R.drawable.xbox20sar);
                fiftySar.setImageResource(R.drawable.xbox50sar);
                oneHundredHSar.setImageResource(R.drawable.xbox100sar);
                break;
            default: // "android"
                tenSar.setImageResource(R.drawable.android10sar);
                twentySar.setImageResource(R.drawable.android20sar);
                fiftySar.setImageResource(R.drawable.android50sar);
                oneHundredHSar.setImageResource(R.drawable.android100sar);
                break;
        }
    }

    // Method for handling the purchase button click event
    public void buyNow(View view) {
        Intent intent = new Intent(this, checkOutPage.class);

        // Pass the selected gift card type to the next activity
        intent.putExtra("card selected type", cardSelected);

        // Pass the selected gift card price to the next activity based on the button clicked
        switch (view.getId()) {
            case R.id.tenSarBtnID:
                intent.putExtra("card selected price", "10 SAR");
                break;
            case R.id.twentySarBtnID:
                intent.putExtra("card selected price", "20 SAR");
                break;
            case R.id.fiftySarBtnID:
                intent.putExtra("card selected price", "50 SAR");
                break;
            default:
                intent.putExtra("card selected price", "100 SAR");
                break;
        }

        // Start the next activity to complete the purchase
        startActivity(intent);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}