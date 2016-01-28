package com.example.ole.mygame;

import sheep.game.Game;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game game = new Game(this, null);
        game.pushState(new GameScreen());

        setContentView(game);
    }
}
