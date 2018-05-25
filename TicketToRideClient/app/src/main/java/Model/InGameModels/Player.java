package Model.InGameModels;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import Model.UserPass;

public class Player extends Observable {

    private List<DestinationCard> destCards;
    private List<TrainCard> trainCards;
    private Set<Route> routesClaimed;
    private Color color;
    private int trainPiecesLeft;
    private int currentScore;
    private UserPass userName;
    private int turnNumber;

    public Player(UserPass name, int queuePosition, Color color) {
        this.userName = name;
        this.color = color;
        this.turnNumber = queuePosition;
        trainCards = new ArrayList<>();
        destCards = new ArrayList<>();
        routesClaimed = new HashSet<>();
        trainPiecesLeft = 45;
        currentScore = 0;
    }

    public void addToDestinationHand(ArrayList<DestinationCard> toAdd)
    {
        for (DestinationCard card: toAdd)
        {
            destCards.add(card);
        }

    }

    public void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public UserPass getUserName() {
        return userName;
    }

    public void setUserName(UserPass userName) {
        this.userName = userName;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(List<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public List<DestinationCard> getDestCards() {
        return destCards;
    }

    public void setDestCards(List<DestinationCard> destCards) {
        this.destCards = destCards;
    }

    public Set<Route> getRoutesClaimed() {
        return routesClaimed;
    }

    public void setRoutesClaimed(Set<Route> routesClaimed) {
        this.routesClaimed = routesClaimed;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getTrainPiecesLeft() {
        return trainPiecesLeft;
    }

    public void setTrainPiecesLeft(int trainPiecesLeft) {
        this.trainPiecesLeft = trainPiecesLeft;
    }

    public int getCurrentScore()
    {
        return currentScore;
    }

    public void setCurrentScore(int currentScore)
    {
        this.currentScore = currentScore;
    }




}
