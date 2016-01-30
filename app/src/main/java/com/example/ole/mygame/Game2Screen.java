package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Typeface;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import android.view.MotionEvent;
import android.content.res.Resources;

public class Game2Screen extends State implements TouchListener{

    private android.graphics.Canvas deviceCanvas;
    private Image heliImageEast = new Image(R.drawable.heli1_east2);
    private Image heliImageWest= new Image(R.drawable.heli1_west2);
    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite backSprite;
    private Sprite heliRightSprite;

    private float x, y, xSpeed, ySpeed;
    private int canvasHeight, canvasWidth;

    @Override
    public boolean onTouchMove(MotionEvent event) {
        heliRightSprite.setSpeed((event.getX() - heliRightSprite.getX()), event.getY() - heliRightSprite.getY());
        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {

        heliRightSprite.setSpeed((event.getX() - heliRightSprite.getX()),event.getY() - heliRightSprite.getY());
        if(event.getX() < heliRightSprite.getX())
        {
            flip("left");
        }
        else if(event.getX() > heliRightSprite.getX())
        {
            flip("right");
        }
        return true;
    }
    public Game2Screen(Resources resources) {
        backSprite = new Sprite(backgroundImage);
        heliRightSprite = new Sprite(heliImageEast);

        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(0, 0);

    }
    @Override
    public void draw(android.graphics.Canvas canvas){
        deviceCanvas = canvas;
        canvasHeight = deviceCanvas.getHeight();
        canvasWidth = deviceCanvas.getWidth();
        backSprite.draw(canvas);
        heliRightSprite.draw(canvas);

        Font text = new Font(255, 255, 255, 20, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText("X: " + String.format("%.2f", heliRightSprite.getX() )  + " Y: " + String.format("%.2f", heliRightSprite.getY()), 30, 30, text);

    }

    public void flip(String direction){
        x = heliRightSprite.getX();
        y = heliRightSprite.getY();
        ySpeed = heliRightSprite.getSpeed().getY();
        xSpeed = heliRightSprite.getSpeed().getX();

        if(direction.equals("left"))
        {
            heliRightSprite = new Sprite(heliImageWest);
            heliRightSprite.setPosition(x-1, y);
            heliRightSprite.setSpeed(xSpeed, ySpeed);
        }
        else if(direction.equals("right"))
        {
            heliRightSprite = new Sprite(heliImageEast);
            heliRightSprite.setPosition(x+1, y);
            heliRightSprite.setSpeed(xSpeed, ySpeed);
        }
    }

    public void update(float dt) {

        if(heliRightSprite.getX()>=canvasWidth)
        {
            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            flip("left");
        }
        if(heliRightSprite.getX() <= 40) {

            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            flip("right");
        }

        if(heliRightSprite.getY() >= canvasHeight)
        {
            heliRightSprite.setSpeed(heliRightSprite.getSpeed().getX(), -heliRightSprite.getSpeed().getY());
        }

        if(heliRightSprite.getY() <= 20)
        {
            heliRightSprite.setSpeed(heliRightSprite.getSpeed().getX(), -heliRightSprite.getSpeed().getY());
        }
        System.out.println(heliRightSprite.getX() + " , " + heliRightSprite.getY());

        heliRightSprite.update(dt);
    }
}
