package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.widget.ImageView;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

public class Game3Screen extends State {
    private AnimationDrawable heliWestAnim, heliEastAnim;
    private Canvas deviceCanvas;
    private Image heliImageEast= new Image(R.drawable.heli1_east);
    private Image heliImageWest= new Image(R.drawable.heli1_west);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite westWall, eastWall;
    private Sprite backSprite;
    private Sprite heliRightSprite;

    private float x, y, xSpeed, ySpeed;
    private int canvasHeight, canvasWidth;


    private Image heliAnimEast1= new Image(R.drawable.heli1_east_1);
    private Image heliAnimEast2= new Image(R.drawable.heli1_east_2);
    private Image heliAnimEast3= new Image(R.drawable.heli1_east_3);
    private Image heliAnimEast4= new Image(R.drawable.heli1_east_4);


    public Game3Screen() {
        backSprite = new Sprite(backgroundImage);
        heliRightSprite = new Sprite(heliImageEast);



        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(300, 200);

    }
    @Override
    public void draw(Canvas canvas){
        deviceCanvas = canvas;

        if(canvas!=null)
        {
            canvasHeight = deviceCanvas.getHeight();
            canvasWidth = deviceCanvas.getWidth();
        }
        backSprite.draw(canvas);
        heliRightSprite.draw(canvas);
        startAnimation();
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

        heliRightSprite.update(dt);
    }

    public void startAnimation()
    {
        long lastSec = 0;
        while(true){
            long sec = System.currentTimeMillis() / 1000;
            if (sec != lastSec) {

                lastSec = sec;
            }
        }
    }
}
