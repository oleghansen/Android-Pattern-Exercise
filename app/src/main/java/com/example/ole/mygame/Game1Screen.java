package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;


public class Game1Screen extends State {

    private android.graphics.Canvas deviceCanvas;
    private Image heliImageEast = new Image(R.drawable.heli1_east2);
    private Image heliImageWest= new Image(R.drawable.heli1_west2);
    private Image wallVerImage = new Image(R.drawable.wall_vertical);
    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite backSprite;
    private Sprite heliRightSprite;
    private int canvasHeight, canvasWidth;



    public Game1Screen() {
        backSprite = new Sprite(backgroundImage);
        heliRightSprite = new Sprite(heliImageEast);


        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(300, 200);

    }
    @Override
    public void draw(android.graphics.Canvas canvas){
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
