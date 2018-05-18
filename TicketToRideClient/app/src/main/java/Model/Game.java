package Model;

import android.annotation.TargetApi;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class Game extends Observable
{
    int gameNumber;

    String ID;

    int playerCount; //player count set for game

    int currentPlayers; //current in game

    Game(int playerCount, int currentPlayers, int gameNumber) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = UUID.randomUUID().toString();
    }

    Game(int playerCount, int currentPlayers, int gameNumber, String ID) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
    }

    void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    @Override
    @TargetApi(19)
    public int hashCode() {
        return Objects.hash(gameNumber, ID, currentPlayers, playerCount);
    }
}
