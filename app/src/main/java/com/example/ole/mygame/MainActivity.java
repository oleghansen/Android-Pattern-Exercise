package com.example.ole.mygame;

import sheep.game.Game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private Button task1Btn, task2Btn, task3Btn, task4Btn;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Binding to layout references
        task1Btn = (Button)findViewById(R.id.task1Button);
        task2Btn = (Button)findViewById(R.id.task2Button);
        task3Btn = (Button)findViewById(R.id.task3Button);
        task4Btn = (Button)findViewById(R.id.task4Button);

        // Give buttons onClickListener
        task1Btn.setOnClickListener(onClickListener);
        task2Btn.setOnClickListener(onClickListener);
        task3Btn.setOnClickListener(onClickListener);
        task4Btn.setOnClickListener(onClickListener);
        //Game game = new Game(this, null);
        //game.pushState(new Game1Screen());
        //setContentView(game);
    }

    private OnClickListener onClickListener = new OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent startTask1 = new Intent("com.example.ole.mygame.TASK1ACTIVITY");
                    startActivity(startTask1);

                    switch(v.getId())
                    {
                        case R.id.task1Button:
                            System.out.println("Pressed 'Task 1'");
                            startTask1.putExtra("taskNumber", 1);
                            break;
                        case R.id.task2Button:
                            System.out.println("Pressed 'Task 2'");
                            startTask1.putExtra("taskNumber", 2);
                            break;
                        case R.id.task3Button:
                            System.out.println("Pressed 'Task 3'");
                            break;
                        case R.id.task4Button:
                            System.out.println("Pressed 'Task 4'");
                            break;

                    }
                    startActivity(startTask1);
                }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
