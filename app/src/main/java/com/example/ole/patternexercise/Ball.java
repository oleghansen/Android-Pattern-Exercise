package com.example.ole.patternexercise;

import java.util.Random;

/**
 * Created by Ole on 21.02.2016.
 */
public class Ball {
    private int xSpeed, ySpeed;

    public Ball()
    {
        xSpeed = 400;
        ySpeed = 400;
    }

    public int getSpeedX()
    {
        return xSpeed;
    }

    public int getSpeedY()
    {
        return ySpeed;
    }

    public void setSpeedX(int speed)
    {
        xSpeed = speed;
    }

    public void setSpeedY(int speed)
    {
        ySpeed = speed;
    }

    public void setRandomSpeed()
    {
        Random random = new Random();
        xSpeed = (random.nextInt(400) + 200) * (Math.random() < 0.5 ? -1 : 1);
        ySpeed = (random.nextInt(400) + 200) * (Math.random() < 0.5 ? -1 : 1);
    }
}
