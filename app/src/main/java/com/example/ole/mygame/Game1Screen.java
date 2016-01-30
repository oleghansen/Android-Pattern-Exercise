package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;
import android.widget.TextView;

import org.w3c.dom.Text;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;


public class Game1Screen extends State {

    private android.graphics.Canvas deviceCanvas;
    private Image heliImageEast = new Image(R.drawable.heli1_east2);
    private Image heliImageWest= new Image(R.drawable.heli1_west2);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite westWall, eastWall;
    private Sprite backSprite;
    private Sprite heliRightSprite;

    private float x, y, xSpeed, ySpeed;
    private int canvasHeight, canvasWidth;



    public Game1Screen() {
        backSprite = new Sprite(backgroundImage);
        heliRightSprite = new Sprite(heliImageEast);


        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(300, 200);

    }
    @Override
    public void draw(android.graphics.Canvas canvas){
        deviceCanvas = canvas;

        if(canvas!=null)
        {
            canvasHeight = deviceCanvas.getHeight();
            canvasWidth = deviceCanvas.getWidth();
        }
        backSprite.draw(canvas);
      //westWall.draw(canvas);
      //eastWall.draw(canvas);
        heliRightSprite.draw(canvas);
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
