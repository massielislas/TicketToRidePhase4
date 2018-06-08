package Model.InGameModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

    public List<TrainCard> addRoute(Route r, boolean isClaimingDouble) {
        if (isClaimingDouble) {
            routesClaimed.add(r);
            return claimDoubleRoute(r);
        }
        String color = r.getColor();
        if (color.equals("Gray")) {
            routesClaimed.add(r);
            return claimGrayRoute(r.getLength());
        }
        else {
            routesClaimed.add(r);
            return removeClaimCards(color, r.getLength());
        }
    }

    private List<TrainCard> claimGrayRoute(int routeLength) {
        int redCount, blueCount,
                yellowCount, greenCount,
                pinkCount, whiteCount,
                orangeCount, grayCount, blackCount = 0;
        //get the counts of each color card the person has in their hand
        redCount = getNumberOfColor("red");
        blueCount = getNumberOfColor("blue");
        yellowCount = getNumberOfColor("yellow");
        greenCount = getNumberOfColor("green");
        pinkCount = getNumberOfColor("pink");
        whiteCount = getNumberOfColor("white");
        orangeCount = getNumberOfColor("orange");
        blackCount = getNumberOfColor("black");
        grayCount = getNumberOfColor("gray");

        Map<String, Integer> colorCounts = new TreeMap<>();
        colorCounts.put("Red", redCount);
        colorCounts.put("Blue", blueCount);
        colorCounts.put("Yellow", yellowCount);
        colorCounts.put("Green", greenCount);
        colorCounts.put("Pink", pinkCount);
        colorCounts.put("White", whiteCount);
        colorCounts.put("Orange", orangeCount);
        colorCounts.put("Black", blackCount);

//        for (String key : colorCounts.keySet()) {
//            if (colorCounts.get(key) == routeLength) {
//                return removeClaimCards(key, routeLength);
//            }
//        }
        int currentChoice = 0;
        int maxValue = 0;
        //find the largest value so we can work down from there
        for (Integer counts : colorCounts.values()) {
            if (counts > currentChoice) {
                currentChoice = counts;
                maxValue = counts;
            }
        }
        //Then find the smallest value that is still greater than or equal to the routeLength
        for (Integer counts : colorCounts.values()) {
            if (counts >= routeLength && counts < currentChoice) {
                currentChoice = counts;
            }
        }
        //Then find the first color that we have that many cards of, and
        for (String key : colorCounts.keySet()) {
            //If we don't have enough color cards to pay for the route OR we just barely have enough
            //of one color to pay for it then we will use wilds to claim the route
            if (currentChoice == maxValue) {
                if ((colorCounts.get(key) + grayCount) == currentChoice
                        || colorCounts.get(key) + grayCount >= currentChoice) {
                    return removeClaimCards(key, routeLength);
                }
            }
            //Otherwise we will not use wilds
            else {
                if (colorCounts.get(key) == currentChoice) {
                    return removeClaimCards(key, routeLength);
                }
            }
        }
        //This should be unreachable
        return null;
    }

    private int getNumberOfColor(String color) {
        int toRet = 0;
        for (int i = 0; i < trainCards.size(); i ++) {
            String cardColor = trainCards.get(i).getColor().toLowerCase();
            if (cardColor.equals(color)) {
                toRet++;
            }
        }
        return toRet;
    }

    private List<TrainCard> claimDoubleRoute(Route r) {
        String color = r.getDoubleColor();
        if (color.equals("Gray")) {
            return claimGrayRoute(r.getLength());
        }
        return removeClaimCards(color, r.getLength());
    }

    private List<TrainCard> removeClaimCards(String color, int routeLength) {
        List<TrainCard> cardsToDiscard = new ArrayList<>();
        int cardCount = 0;
        //Remove cards with the corresponding color until you have removed them all OR you have
        //enough cards to pay for the route
        for (int i = 0; i < trainCards.size(); i ++) {
            TrainCard checkColor = trainCards.get(i);
            if (checkColor.getColor().equals(color) && cardCount < routeLength)
            {
                cardsToDiscard.add(checkColor);
                cardCount++;
            }
        }
        //Then if the count has not reached the length of the route, get wilds until you do
        if (cardCount < routeLength) {
            for (int i = 0; i < trainCards.size(); i ++) {
                TrainCard checkColor = trainCards.get(i);
                if (checkColor.getColor().equals("Gray") && cardCount < routeLength) {
                    cardsToDiscard.add(checkColor);
                    cardCount++;
                }
            }
        }
        for (TrainCard toDisc : cardsToDiscard) {
            trainCards.remove(toDisc);
        }
        return cardsToDiscard;
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
