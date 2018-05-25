package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Model.InGameModels.TrainCard;


/**
 * Created by Lance on 5/14/2018.
 */

public class Game {
    private final int numberOfEachType = 12;
    private final int locomotiveCount = 14;
    private final int countOfCardTypes = 8;
    private final int totalNormalCards = numberOfEachType * countOfCardTypes;
    private final int faceupSize = 5;
    private int gameNumber;
    private int playerCount;
    private int currentPlayers;
    private String ID;
    private List<Player> playerList;
    private List<TrainCard> trainCardFacedownDeck;
    private TrainCard[] trainCardFaceupDeck;
    private List<TrainCard> discardedTrainCards;
    private List<DestinationCard> destinationCardDeck;

    public Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
        playerList = new ArrayList<>();
        trainCardFacedownDeck = new ArrayList<>();
        trainCardFaceupDeck = new TrainCard[faceupSize];
        discardedTrainCards = new ArrayList<>();
        destinationCardDeck = new ArrayList<>();
    }

    public Game(int playerCount, int currentPlayers, int gameNumber, String ID) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
    }

    public boolean addPlayerToGame(UserPass user) {
        //If the game is full return false
        if (currentPlayers == playerCount) {
            return false;
        }
        else {
            //otherwise, Add the player to the playerList and set their turn position to the spot
            //they will be the list. So we can access it later
            playerList.add(new Player(user, playerList.size()));
            currentPlayers++;
            return true;
        }
    }

    private void initializeTrainCards() {
        String type = "TrainCar";
        Color color = Color.BLUE;
        //If the discard pile doesn't have any cards in it, then we're doing the initial deck creation
        //Otherwise we're doing the reshuffle of the discard pile
        if (discardedTrainCards.size() == 0) {
            for (int j = 0; j < countOfCardTypes; j ++) {
                //switch Statement for type and color. This makes it so the entire deck of normal
                //train cards is initialized. After this we will add the 14 locomotives
                switch (j) {
                    case 0: {
                        type = "Box";
                        color = Color.pink;
                        break;
                    }
                    case 1: {
                        type = "Passenger";
                        color = Color.white;
                        break;
                    }
                    case 2: {
                        type = "Tanker";
                        color = Color.blue;
                        break;
                    }
                    case 3: {
                        type = "Reefer";
                        color = Color.yellow;
                        break;
                    }
                    case 4: {
                        type = "Freight";
                        color = Color.orange;
                        break;
                    }
                    case 5: {
                        type = "Hopper";
                        color = Color.black;
                        break;
                    }
                    case 6: {
                        type = "Coal";
                        color = Color.red;
                        break;
                    }
                    case 7: {
                        type = "Caboose";
                        color = Color.green;
                        break;
                    }
                }
                //Once we know the type and color, add the corresponding card to the deck
                for (int i = 0; i < numberOfEachType; i++) {
                    trainCardFacedownDeck.add(new TrainCard(((i*j) + i), color, type));
                }
            }
            //then add the locomotive cards
            for (int i = 0; i < locomotiveCount; i++) {
                trainCardFacedownDeck.add(new TrainCard(totalNormalCards + i, Color.gray, "Locomotive"));
            }
            //shuffle the deck
            Collections.shuffle(trainCardFacedownDeck);
            dealFaceupDeck();
        }
        //If the discard pile has a size greater than 0 and we're calling this method it means that
        //We are reshuffling the cards back into main deck for re-use
        else {
            for (TrainCard card : discardedTrainCards) {
                trainCardFacedownDeck.add(card);
            }
            Collections.shuffle(trainCardFacedownDeck);
            discardedTrainCards.clear();
        }
    }

    private void dealFaceupDeck() {
        //Take the first 5 cards and put them as the face up deck
        for (int i = 0; i < faceupSize; i ++) {
            trainCardFaceupDeck[i] = trainCardFacedownDeck.get(i);
        }
        //then remove those cards from the deck
        for (int i = 0; i < faceupSize; i ++) {
            trainCardFacedownDeck.remove(i);
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

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<TrainCard> getTrainCardFacedownDeck() {
        return trainCardFacedownDeck;
    }

    public void setTrainCardFacedownDeck(List<TrainCard> trainCardFacedownDeck) {
        this.trainCardFacedownDeck = trainCardFacedownDeck;
    }

    public List<DestinationCard> getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public void setDestinationCardDeck(List<DestinationCard> destinationCardDeck) {
        this.destinationCardDeck = destinationCardDeck;
    }

    public TrainCard[] getTrainCardFaceupDeck() {
        return trainCardFaceupDeck;
    }

    public void setTrainCardFaceupDeck(TrainCard[] faceupDeck) {
        this.trainCardFaceupDeck = trainCardFaceupDeck;
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


}