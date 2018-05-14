package clientModel;

import java.util.Observable;
import java.util.UUID;

public class Game extends Observable
{

    int gameNumber;

    String ID;

    int playerCount; //player count set for game

    int currentPlayers; //current in game

    public Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
    }

    public void addPlayer()
    {
        currentPlayers++;
        setChanged(); //set change has occurred
        notifyObservers(currentPlayers); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
    }

    public int getPlayerCount()
    {
        return playerCount;
    }

    public void setPlayerCount(int playerCount)
    {
        this.playerCount = playerCount;
    }
}
