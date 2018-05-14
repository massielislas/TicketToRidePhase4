package Models;

import java.util.UUID;


/**
 * Created by Lance on 5/14/2018.
 */

public class Game {
    private int gameNumber;
    private int playerCount;
    private int currentPlayers;
    private String ID;

    public Game(int playerCount, int currentPlayers)
    {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
    }

    public int getPlayerCount()
    {
        return playerCount;
    }

    public void setPlayerCount(int playerCount)
    {
        this.playerCount = playerCount;
    }

    public int getCurrentPlayers()
    {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers)
    {
        this.currentPlayers = currentPlayers;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }
}