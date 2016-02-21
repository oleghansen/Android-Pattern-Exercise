package com.example.ole.patternexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    private Button task3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding to layout references
        task3Btn = (Button)findViewById(R.id.task3Button);


        // Give buttons onClickListener
        task3Btn.setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = new OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent startTask1 = new Intent("com.example.ole.patternexercise.TASK1ACTIVITY");
                    startActivity(startTask1);

                    switch(v.getId())
                    {
                        case R.id.task3Button:
                            System.out.println("Pressed 'Task 3'");
                            startTask1.putExtra("taskNumber", 3);
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
