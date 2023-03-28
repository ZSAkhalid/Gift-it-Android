package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize the intent to the next page
        intent = new Intent(this,CardsPage.class);

    }

    // This method is called when a card button is clicked.
    public void selectStoreCard(View view) {
        // Determine which card button was clicked and create a new intent for the appropriate card.
        switch (view.getId()) {
            case R.id.epic_games_button:
                intent.putExtra("card selected", "epicGames");
                break;
            case R.id.playstation_button:
                intent.putExtra("card selected", "playstation");
                break;
            case R.id.xbox_button:
                intent.putExtra("card selected", "xbox");
                break;
            default:
                intent.putExtra("card selected", "android");
                break;
        }
        startActivity(intent);
    }
}