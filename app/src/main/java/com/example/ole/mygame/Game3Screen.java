package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;

public class Game3Screen extends State {
    private Canvas deviceCanvas;
    private Image heliImageEast= new Image(R.drawable.heli1_east);
    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite backSprite;
    private Sprite heliRightSprite;

    private int canvasHeight, canvasWidth;
    private float animationCounter = 0;

    private SpriteView heliAnimEast1= new Image(R.drawable.heli1_east_1);
    private SpriteView heliAnimEast2= new Image(R.drawable.heli1_east_2);
    private SpriteView heliAnimEast3= new Image(R.drawable.heli1_east_3);
    private SpriteView heliAnimEast4= new Image(R.drawable.heli1_east_4);

    public Game3Screen() {
        backSprite = new Sprite(backgroundImage);
        heliRightSprite = new Sprite(heliImageEast);
        heliRightSprite.setView(heliAnimEast1);
        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(300, 200);

    }
    @Override
    public void draw(Canvas canvas){
        if(deviceCanvas==null)
        {
            deviceCanvas = canvas;
            canvasHeight = deviceCanvas.getHeight();
            canvasWidth = deviceCanvas.getWidth();
        }
        backSprite.draw(deviceCanvas);
        heliRightSprite.draw(deviceCanvas);
    }

    public void flip(String direction){
        if(direction.equals("left"))
        {
            heliRightSprite.setScale(-1, 1);
        }
        else if(direction.equals("right"))
        {
            heliRightSprite.setScale(1, 1);
        }
    }

    public void update(float dt)
    {
        animationCounter = animationCounter +  dt;

        System.out.println(animationCounter);
        if(animationCounter >= 0.100)
        {
           if(heliRightSprite.getView() == heliAnimEast1)
           {
               heliRightSprite.setView(heliAnimEast2);
           }
           else if(heliRightSprite.getView() == heliAnimEast2)
           {
               heliRightSprite.setView(heliAnimEast3);
           }
           else if(heliRightSprite.getView() == heliAnimEast3)
           {
               heliRightSprite.setView(heliAnimEast4);
           }
           else if (heliRightSprite.getView() == heliAnimEast4)
           {
                heliRightSprite.setView(heliAnimEast1);
           }
            animationCounter = 0;
        }

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
}
