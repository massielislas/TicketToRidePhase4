package clientModel;

import java.util.Observable;
import java.util.UUID;

public class Game extends Observable
{
    int gameNumber;

    String ID;

    int playerCount; //player count set for game

    int currentPlayers; //current in game

    Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
    }

    void addPlayer()
    {
        currentPlayers++;
        setChanged(); //set change has occurred
        notifyObservers(currentPlayers); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    int getGameNumber() {
        return gameNumber;
    }

    void setGameNumber(int gameNumber) {
    }

    int getPlayerCount()
    {
        return playerCount;
    }

    void setPlayerCount(int playerCount)
    {
        this.playerCount = playerCount;
    }
}
