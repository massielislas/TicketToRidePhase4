package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.TrainCard;


/**
 * Created by Lance on 5/14/2018.
 */

public class Game {
    //Static variables for use in intializing the deck of TrainCards
    private final int numberOfEachType = 12;
    private final int locomotiveCount = 14;
    private final int countOfCardTypes = 8;
    private final int totalNormalCards = numberOfEachType * countOfCardTypes;
    private final int faceupSize = 5;
    private final int startingTrainHandSize = 4;
    //Number of the game in the queue
    private int gameNumber;
    //Max number of players
    private int playerCount;
    private int currentPlayers;
    //UUID for this specific game
    private String ID;
    //List of players in the game
    private List<Player> playerList;
    private List<TrainCard> trainCardFacedownDeck;
    private TrainCard[] trainCardFaceupDeck;
    private List<TrainCard> discardedTrainCards;
    private List<DestinationCard> destinationCardDeck;
    private List<String> chat;

    public Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
        playerList = new ArrayList<>();
        trainCardFacedownDeck = new ArrayList<>();
        trainCardFaceupDeck = new TrainCard[faceupSize];
        discardedTrainCards = new ArrayList<>();
        destinationCardDeck = new ArrayList<>();
        chat = new ArrayList<>();
    }

    public Game(int playerCount, int currentPlayers, int gameNumber, String ID) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
        initializeTrainCards();
        for (Player p: playerList) {

        }
    }

    public boolean addPlayerToGame(UserPass user) {
        //If the game is full return false
        if (currentPlayers == playerCount) {
            return false;
        }
        else {
            //otherwise, Add the player to the playerList and set their turn position to the spot
            //they will be the list. So we can access it later
            playerList.add(new Player(user, playerList.size() + 1));
            currentPlayers++;
            return true;
        }
    }

    private void initializeTrainCards() {
        String type = "TrainCar";
        String color = "Blue";
        //If the discard pile doesn't have any cards in it, then we're doing the initial deck creation
        //Otherwise we're doing the reshuffle of the discard pile
        if (discardedTrainCards.size() == 0) {
            for (int j = 0; j < countOfCardTypes; j ++) {
                //switch Statement for type and color. This makes it so the entire deck of normal
                //train cards is initialized. After this we will add the 14 locomotives
                switch (j) {
                    case 0: {
                        type = "Box";
                        color = "pink";
                        break;
                    }
                    case 1: {
                        type = "Passenger";
                        color = "white";
                        break;
                    }
                    case 2: {
                        type = "Tanker";
                        color = "blue";
                        break;
                    }
                    case 3: {
                        type = "Reefer";
                        color = "yellow";
                        break;
                    }
                    case 4: {
                        type = "Freight";
                        color = "orange";
                        break;
                    }
                    case 5: {
                        type = "Hopper";
                        color = "black";
                        break;
                    }
                    case 6: {
                        type = "Coal";
                        color = "red";
                        break;
                    }
                    case 7: {
                        type = "Caboose";
                        color = "green";
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
                trainCardFacedownDeck.add(new TrainCard(totalNormalCards + i, "silver", "Locomotive"));
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

    public SinglePlayerStartInfo dealStartingHand(Player p) {

        SinglePlayerStartInfo startingHand = new SinglePlayerStartInfo(p.getUserName(), p.getTurnNumber());
        //Put the first 4 and 3 Train and Destination Cards to the starting hand package
        for (int i = 0; i < startingTrainHandSize; i ++) {
            startingHand.addTrainCard(trainCardFacedownDeck.get(i));
            if (i < 3) {
                startingHand.addDestCard(destinationCardDeck.get(i));
            }
        }
        //Then remove the corresponding cards from the corresponding decks
        for (int i = 0; i < startingTrainHandSize; i++) {
            trainCardFacedownDeck.remove(i);
            if (i < 3) {
                destinationCardDeck.remove(i);
            }
        }
        //Then create a list with all the information about other players that they are allowed to
        //see. Allowing us to display the relevant info for them without actually telling them
        //anything they aren't supposed to know.
        List<PlayerShallow> list = new ArrayList<>();
        for (Player other : playerList) {
            if (!other.getUserName().equals(p.getUserName())) {
                PlayerShallow copy = new PlayerShallow(other.getnameString(),
                        other.getTrainHandSize(), other.getDestHandSize(),
                        other.getTrainPiecesLeft(),other.getTurnNumber());
                list.add(copy);
            }
        }
        startingHand.setPlayerInfo(list);
        return startingHand;
    }

    public void addChat(String msg, String userName) {
        chat.add(userName + ": " + msg);
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