package com.example.ole.mygame;

/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.game.World;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;

public class Game3Screen extends State implements CollisionListener {
    private final int RIGHT = 1, LEFT = 2;
    private Canvas deviceCanvas;
    private Image heliImageEast = new Image(R.drawable.heli1_east);
    private Image planeImageEast = new Image(R.drawable.heli1_east2);
    private Image ufoImage = new Image(R.drawable.ufo);

    private Image backgroundImage = new Image(R.drawable.backgroundstars2);

    private Sprite backSprite;
    private Sprite heliRightSprite, flySprite, ufoSprite;
    private CollisionLayer collisionLayer = new CollisionLayer();
    private int canvasHeight, canvasWidth;
    private float animationCounter = 0;
    private World world = new World();

    private SpriteView heliAnimEast1 = new Image(R.drawable.heli1_east_1);
    private SpriteView heliAnimEast2 = new Image(R.drawable.heli1_east_2);
    private SpriteView heliAnimEast3 = new Image(R.drawable.heli1_east_3);
    private SpriteView heliAnimEast4 = new Image(R.drawable.heli1_east_4);

    public Game3Screen() {
        backSprite = new Sprite(backgroundImage);

        flySprite = new Sprite(planeImageEast);
        flySprite.setPosition(400, 200);
        flySprite.setSpeed(-200, -100);

        ufoSprite = new Sprite(ufoImage);
        ufoSprite.setPosition(500, 400);
        ufoSprite.setSpeed(400, -50);

        heliRightSprite = new Sprite(heliImageEast);
        heliRightSprite.setView(heliAnimEast1);
        heliRightSprite.setPosition(250, 120);
        heliRightSprite.setSpeed(300, 200);

        collisionLayer.addSprite(heliRightSprite);
        collisionLayer.addSprite(ufoSprite);
        collisionLayer.addSprite(flySprite);

        heliRightSprite.addCollisionListener(this);
        flySprite.addCollisionListener(this);
        ufoSprite.addCollisionListener(this);

        world.addLayer(collisionLayer);

    }

    @Override
    public void draw(Canvas canvas) {
        if (deviceCanvas == null) {
            deviceCanvas = canvas;
            canvasHeight = deviceCanvas.getHeight();
            canvasWidth = deviceCanvas.getWidth();
        }

        backSprite.draw(deviceCanvas);
        heliRightSprite.draw(deviceCanvas);
        flySprite.draw(deviceCanvas);
        ufoSprite.draw(deviceCanvas);
        world.draw(deviceCanvas);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

    }

    public void flip(int spriteNumber, int direction) {
        if (direction == LEFT) {
            switch (spriteNumber) {
                case 1:
                    heliRightSprite.setScale(-1, 1);
                    break;
                case 2:
                    flySprite.setScale(-1, 1);
                    break;
                case 3:
                    ufoSprite.setScale(-1, 1);
                    break;
            }
        } else if (direction == RIGHT) {
            switch (spriteNumber) {
                case 1:
                    heliRightSprite.setScale(1, 1);
                    break;
                case 2:
                    flySprite.setScale(1, 1);
                    break;
                case 3:
                    ufoSprite.setScale(1, 1);
                    break;
            }
        }
    }

    public void update(float dt) {

        if (flySprite.collides(ufoSprite)) {

            flySprite.setSpeed(-flySprite.getSpeed().getX(), flySprite.getSpeed().getY());
            ufoSprite.setSpeed(-ufoSprite.getSpeed().getX(), ufoSprite.getSpeed().getY());
            System.out.println("Collision 1!");

        } else if (flySprite.collides(heliRightSprite)) {

            flySprite.setSpeed(-flySprite.getSpeed().getX(), flySprite.getSpeed().getY());
            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            System.out.println("Collision 2!");

        } else if (ufoSprite.collides(heliRightSprite)) {
            ufoSprite.setSpeed(-ufoSprite.getSpeed().getX(), ufoSprite.getSpeed().getY());
            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            System.out.println("Collision 3!");
        }

        animationCounter = animationCounter + dt;

        System.out.println(animationCounter);
        if (animationCounter >= 0.100) {
            if (heliRightSprite.getView() == heliAnimEast1) {
                heliRightSprite.setView(heliAnimEast2);
            } else if (heliRightSprite.getView() == heliAnimEast2) {
                heliRightSprite.setView(heliAnimEast3);
            } else if (heliRightSprite.getView() == heliAnimEast3) {
                heliRightSprite.setView(heliAnimEast4);
            } else if (heliRightSprite.getView() == heliAnimEast4) {
                heliRightSprite.setView(heliAnimEast1);
            }
            animationCounter = 0;
        }

        if (heliRightSprite.getX() >= canvasWidth) {
            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            flip(1, LEFT);
        }
        if (heliRightSprite.getX() <= 40) {

            heliRightSprite.setSpeed(-heliRightSprite.getSpeed().getX(), heliRightSprite.getSpeed().getY());
            flip(1, RIGHT);
        }

        if (heliRightSprite.getY() >= canvasHeight) {
            heliRightSprite.setSpeed(heliRightSprite.getSpeed().getX(), -heliRightSprite.getSpeed().getY());
        }

        if (heliRightSprite.getY() <= 20) {
            heliRightSprite.setSpeed(heliRightSprite.getSpeed().getX(), -heliRightSprite.getSpeed().getY());
        }

        if (flySprite.getX() >= canvasWidth) {
            flySprite.setSpeed(-flySprite.getSpeed().getX(), flySprite.getSpeed().getY());
            flip(2, LEFT);
        }
        if (flySprite.getX() <= 40) {
            flySprite.setSpeed(-flySprite.getSpeed().getX(), flySprite.getSpeed().getY());
            flip(2, RIGHT);
        }

        if (flySprite.getY() >= canvasHeight) {
            flySprite.setSpeed(flySprite.getSpeed().getX(), -flySprite.getSpeed().getY());
        }

        if (flySprite.getY() <= 20) {
            flySprite.setSpeed(flySprite.getSpeed().getX(), -flySprite.getSpeed().getY());
        }

        if (ufoSprite.getX() >= canvasWidth) {
            ufoSprite.setSpeed(-ufoSprite.getSpeed().getX(), ufoSprite.getSpeed().getY());
            flip(3, LEFT);
        }
        if (ufoSprite.getX() <= 40) {
            ufoSprite.setSpeed(-ufoSprite.getSpeed().getX(), ufoSprite.getSpeed().getY());
            flip(3, RIGHT);
        }

        if (ufoSprite.getY() >= canvasHeight) {
            ufoSprite.setSpeed(ufoSprite.getSpeed().getX(), -ufoSprite.getSpeed().getY());
        }

        if (ufoSprite.getY() <= 20) {
            ufoSprite.setSpeed(ufoSprite.getSpeed().getX(), -ufoSprite.getSpeed().getY());
        }


        heliRightSprite.update(dt);
        flySprite.update(dt);
        ufoSprite.update(dt);
        world.update(dt);
    }
}
