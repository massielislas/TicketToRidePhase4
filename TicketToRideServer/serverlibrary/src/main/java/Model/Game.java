package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Lance on 5/14/2018.
 */

public class Game {
    private int gameNumber;
    private int playerCount;
    private int currentPlayers;
    private String ID;
    private List<UserPass> players;

    public Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
    }

    public Game(int playerCount, int currentPlayers, int gameNumber, String ID) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
        this.players = new ArrayList<>();
    }

    public boolean addPlayerToGame(UserPass user) {
        if (currentPlayers == playerCount) {
            return false;
        }
        else {
            currentPlayers++;
            players.add(user);
            return true;
        }
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

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!(((Game) obj).getID().equals(this.ID))) {
            return false;
        }
        return true;
    }

    public List<UserPass> getPlayers() {
        return players;
    }
}