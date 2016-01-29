package com.example.ole.mygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sheep.game.Game;

/**
 * Created by Ole on 29.01.2016.
 */
public class Task2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        game.pushState(new Game2Screen(game.getResources()));
        setContentView(game);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
