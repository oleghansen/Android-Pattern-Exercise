package com.example.ole.patternexercise;

/**
 * Created by Ole on 21.02.2016.
 */
public class Score {

    private int playerOneScore, playerTwoScore;

    public Score()
    {
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    public void addPoint(int player)
    {
        if(player == 1)
        {
            playerOneScore++;
        }
        else if(player == 2)
        {
            playerTwoScore++;
        }
    }

    public int getPlayerOneScore()
    {
        return playerOneScore;
    }

    public int getPlayerTwoScore()
    {
        return playerTwoScore;
    }
}
