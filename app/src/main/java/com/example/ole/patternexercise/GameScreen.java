package com.example.ole.patternexercise;

/**
 * Created by  Ole on 18.02.2016.
 */
/**
 * Created by Ole on 28.01.2016.
 */


import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.MotionEvent;

import java.util.Random;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import java.util.Observable;
import java.util.ArrayList;

public class GameScreen extends State implements TouchListener, CollisionListener {

    private boolean begun;
    private Canvas deviceCanvas;
    private Image ballImage = new Image(R.drawable.ball);
    private Image playerOneImage = new Image(R.drawable.player1);
    private Image playerTwoImage = new Image(R.drawable.player2);
    private Image backgroundImage = new Image(R.drawable.pongbg);
    private Image midwallImage = new Image(R.drawable.midwall);

    private Sprite backSprite;
    private Sprite ballSprite;
    private Sprite player1Sprite;
    private Sprite player2Sprite;
    private Sprite midwallSprite;

    private int canvasHeight, canvasWidth;
    private int playerOneScore, playerTwoScore;
    private CollisionLayer collisionLayer = new CollisionLayer();
    private World world = new World();
    private static GameScreen instance = null;

    private Score score;
    private ArrayList<Score> scores;

    public GameScreen() {
        playerOneScore = 0;
        playerTwoScore = 0;
        begun = false;
        backSprite = new Sprite(backgroundImage);
        midwallSprite = new Sprite(midwallImage);

        ballSprite = new Sprite(ballImage);
        player1Sprite = new Sprite(playerOneImage);
        player2Sprite = new Sprite(playerTwoImage);

        collisionLayer.addSprite(player1Sprite);
        collisionLayer.addSprite(player2Sprite);
        collisionLayer.addSprite(ballSprite);

        ballSprite.setPosition(canvasWidth / 2, canvasHeight / 2);
        ballSprite.setSpeed(400, 200);

        player1Sprite.addCollisionListener(this);
        player2Sprite.addCollisionListener(this);
        ballSprite.addCollisionListener(this);

        world.addLayer(collisionLayer);
    }

    public static GameScreen getInstance() {
        if(instance == null) {
            instance = new GameScreen();
        }
        return instance;
    }

    public void resetBall() {
        Random random = new Random();
        int randomXspeed = (random.nextInt(400) + 200) * (Math.random() < 0.5 ? -1 : 1);
        int randomYspeed = (random.nextInt(400) + 200) * (Math.random() < 0.5 ? -1 : 1);

        ballSprite.setPosition(canvasWidth / 2, canvasHeight / 2);
        ballSprite.setSpeed(randomXspeed, randomYspeed);
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {

        int eventaction = event.getActionMasked();
        int num = event.getPointerCount();
        for (int a = 0; a < num; a++) {
            int X = (int) event.getX(event.getPointerId(a));
            int allowed_touch_range = canvasWidth / 2; // Your screen width divided by 2
            switch (eventaction) {
                case MotionEvent.ACTION_MOVE:
                    if (X < allowed_touch_range) {
                        player2Sprite.setPosition((player2Sprite.getX()), event.getY());
                    }
                    if (X > allowed_touch_range) {
                        player1Sprite.setPosition((player1Sprite.getX()), event.getY());
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {

        if (deviceCanvas == null) {
            deviceCanvas = canvas;
            canvasHeight = deviceCanvas.getHeight();
            canvasWidth = deviceCanvas.getWidth();
            midwallSprite.setPosition(canvasWidth / 2, canvasHeight);
        }
        if (!begun) {
            player1Sprite.setPosition(canvasWidth - 20, canvasHeight / 2);
            player2Sprite.setPosition(20, canvasHeight / 2);

            begun = true;
        }

        backSprite.draw(deviceCanvas);
        midwallSprite.draw(deviceCanvas);
        ballSprite.draw(deviceCanvas);
        player1Sprite.draw(deviceCanvas);
        player2Sprite.draw(deviceCanvas);
        world.draw(deviceCanvas);

        Font playerOneScoreText = new Font(50, 50, 255, 50, Typeface.SANS_SERIF, Typeface.NORMAL);
        deviceCanvas.drawText("" + playerOneScore, (canvasWidth / 2 + 50), 50, playerOneScoreText);
        Font playerTwoScoreText = new Font(255, 50, 50, 50, Typeface.SANS_SERIF, Typeface.NORMAL);
        deviceCanvas.drawText("" + playerTwoScore, (canvasWidth / 2 - 80), 50, playerTwoScoreText);
    }

    public void update(float dt) {

        if (playerOneScore == 21 || playerTwoScore == 21) {
            System.out.println("Game over");
            playerOneScore = 0;
            playerTwoScore = 0;
            resetBall();
        }

        if (ballSprite.collides(player1Sprite) || ballSprite.collides(player2Sprite)) {
            ballSprite.setSpeed(-ballSprite.getSpeed().getX(), ballSprite.getSpeed().getY());
        }
        /// Screen borders ///

        if (ballSprite.getX() >= canvasWidth) {
            resetBall();
            playerTwoScore++;
        }
        if (ballSprite.getX() <= 0) {
            resetBall();
            playerOneScore++;
        }

        if (ballSprite.getY() >= canvasHeight) {
            ballSprite.setSpeed(ballSprite.getSpeed().getX(), -ballSprite.getSpeed().getY());
        }

        if (ballSprite.getY() <= 0) {
            ballSprite.setSpeed(ballSprite.getSpeed().getX(), -ballSprite.getSpeed().getY());
        }
        player1Sprite.update(dt);
        player2Sprite.update(dt);
        ballSprite.update(dt);
        midwallSprite.update(dt);
        world.update(dt);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

    }
}
