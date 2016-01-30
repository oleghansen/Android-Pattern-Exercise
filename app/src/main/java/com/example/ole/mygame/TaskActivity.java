package com.example.ole.mygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sheep.game.Game;

/**
 * Created by Ole on 29.01.2016.
 */
public class TaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this, null);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            if(extras.getInt("taskNumber") == 1)
            {
                game.pushState(new Game1Screen());
            }
            else if(extras.getInt("taskNumber") == 2)
            {
                game.pushState(new Game2Screen(game.getResources()));
            }
            else if(extras.getInt("taskNumber") == 3)
            {
                game.pushState(new Game3Screen());
            }
            else if(extras.getInt("taskNumber") == 4)
            {
                game.pushState(new Pong());
            }
        }
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
