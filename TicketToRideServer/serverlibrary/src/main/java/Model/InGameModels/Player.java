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
    private List<DestinationCard> toChoose;
    private Set<Route> routesClaimed;
    private int turnNumber;
    private int trainPiecesLeft;
    private int currentScore;

    public Player(UserPass name, int queuePosition) {
        userName = name;
        trainCards = new ArrayList<>();
        destCards = new ArrayList<>();
        routesClaimed = new HashSet<>();
        toChoose = new ArrayList<>();
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

    public List<TrainCard> addRoute(Route r, boolean isDouble) {
        List<TrainCard> cardsToDiscard = new ArrayList<>();
        List<Integer> indicesToDiscard = new ArrayList<>();
        int cardCount = 0;
        String color = r.getColor();
        if (color.equals("Gray")) {
            return claimGrayRoute(r, isDouble);
        }
        //Remove cards with the corresponding color until you have removed them all OR you have
        //enough cards to pay for the route
        for (int i = 0; i < trainCards.size(); i ++) {
            TrainCard checkColor = trainCards.get(i);
            if (isDouble) {

            }
            else {
                if (checkColor.getColor().equals(color) && cardCount < r.getLength())
                {
                    cardsToDiscard.add(checkColor);
                    cardCount++;
                }
            }
        }
        //Then if the count has not reached the length of the route, get wilds until you do
        if (cardCount < r.getLength()) {
            for (int i = 0; i < trainCards.size(); i ++) {
                TrainCard checkColor = trainCards.get(i);
                if (checkColor.getColor().equals("Gray") && cardCount < r.getLength()) {
                    cardsToDiscard.add(checkColor);
                    cardCount++;
                }
            }
        }
        routesClaimed.add(r);
    }

    private List<TrainCard> claimGrayRoute(Route r, boolean isDouble) {

    }

    public void addDestCardToChoose(DestinationCard toAdd) {
        toChoose.add(toAdd);
    }
    public List<DestinationCard> getToChoose() {
        return toChoose;
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
