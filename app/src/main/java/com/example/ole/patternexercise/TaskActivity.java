package com.example.ole.patternexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sheep.game.Game;

/**
 * Created by Ole on 29.01.2016.
 */
public class TaskActivity extends AppCompatActivity {
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);

        if (getIntent() != null)
        {
            extras = getIntent().getExtras();
        }


        if(extras != null)
        {
            if(extras.getInt("taskNumber") == 3)
            {
                game.pushState(new GameScreen().getInstance());
                System.out.println("New game instance!");
            }
        }
        setContentView(game);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
