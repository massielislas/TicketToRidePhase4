package Model.InGameModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.UserPass;

/**
 * Created by Master_Chief on 5/23/2018.
 */

public class Player {
    private UserPass userName;
    private List<TrainCard> trainCards;
    private List<DestinationCard> destCards;
    private Set<Route> routesClaimed;
    private int turnNumber;
    private int trainPiecesLeft;
    private int currentScore;

    public Player(UserPass name, int queuePosition) {
        userName = name;
        trainCards = new ArrayList<>();
        destCards = new ArrayList<>();
        routesClaimed = new HashSet<>();
        turnNumber = queuePosition;
        trainPiecesLeft = 45;
        currentScore = 0;
    }

    public int getTrainHandSize() {
        return trainCards.size();
    }

    public int getDestHandSize() {
        return destCards.size();
    }

    public String getnameString() {
        return userName.getNameOrPassword();
    }

    public UserPass getUserName() {
        return userName;
    }

    public void addTrainCard(TrainCard card) {
        trainCards.add(card);
    }

    public void addDestCard(DestinationCard card) {
        destCards.add(card);
    }

    public DestinationCard removeDestCard(int id) {
        for (DestinationCard d : destCards) {
            if(d.getID() == id) {
                DestinationCard toRemove = d;
                destCards.remove(d);
                return toRemove;
            }
        }
        return null;
    }

    public void addRoute(Route r) {
        routesClaimed.add(r);
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
