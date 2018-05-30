package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.TrainCard;
import Model.InGameModels.TrainCardDeck;


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
    private List<UserPass> userList;
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
        userList = new ArrayList<>();
        trainCardFacedownDeck = new ArrayList<>();
        trainCardFaceupDeck = new TrainCard[faceupSize];
        discardedTrainCards = new ArrayList<>();
        DestinationCardDeck deck = new DestinationCardDeck();
        destinationCardDeck = deck.getDestinationCards();
        chat = new ArrayList<>();
    }

    public Game(int playerCount, int currentPlayers, int gameNumber, String ID)
    {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
        playerList = new ArrayList<>();
        userList = new ArrayList<>();
        trainCardFacedownDeck = new ArrayList<>();
        trainCardFaceupDeck = new TrainCard[faceupSize];
        discardedTrainCards = new ArrayList<>();
        DestinationCardDeck deck = new DestinationCardDeck();
        destinationCardDeck = deck.getDestinationCards();
        chat = new ArrayList<>();
        initializeTrainCards();
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
            userList.add(user);
            currentPlayers++;
            return true;
        }
    }

    private void initializeTrainCards() {
        trainCardFacedownDeck = new TrainCardDeck().getTrainCards();
        Collections.shuffle(trainCardFacedownDeck);
        dealFaceupDeck();
    }

    public void reshuffleDiscardedTrains() {
        for (TrainCard card : discardedTrainCards) {
            trainCardFacedownDeck.add(card);
        }
        Collections.shuffle(trainCardFacedownDeck);
        discardedTrainCards.clear();
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

        SinglePlayerStartInfo startingInfo = new SinglePlayerStartInfo(p.getUserName(), p.getTurnNumber());
        //Put the first 4 and 3 Train and Destination Cards to the starting hand package
        for (int i = 0; i < startingTrainHandSize; i ++) {
            startingInfo.addTrainCard(trainCardFacedownDeck.get(i));
            if (i < 3) {
                startingInfo.addDestCard(destinationCardDeck.get(i));
            }
        }
        //Then remove the corresponding cards from the corresponding decks
        for (int i = 0; i < startingTrainHandSize; i++) {
            trainCardFacedownDeck.remove(0);
            if (i < 3) {
                destinationCardDeck.remove(0);
            }
        }
        //Then create a list with all the information about other players that they are allowed to
        //see. Allowing us to display the relevant info for them without actually telling them
        //anything they aren't supposed to know.
        List<PlayerShallow> list = getPlayerShallows(p);
        startingInfo.setPlayerInfo(list);
        startingInfo.setStartingFaceUpCards(trainCardFaceupDeck);
        return startingInfo;
    }

    //private function for retrieving information that players are allowed to know about other
    //players in the game (hand size, score, train pieces left)
    private List<PlayerShallow> getPlayerShallows(Player p) {

        List<PlayerShallow> list = new ArrayList<>();
        for (Player other : playerList) {
            if (!other.getUserName().equals(p.getUserName())) {
                PlayerShallow copy = new PlayerShallow(other.getnameString(),
                        other.getTrainHandSize(), other.getDestHandSize(),
                        other.getTrainPiecesLeft(),other.getTurnNumber(),other.getCurrentScore());
                list.add(copy);
            }
        }
        return list;
    }

    public void addChat(String msg, String userName) {
        chat.add(userName + ": " + msg);
    }

    public boolean userAlreadyInGame(UserPass name) {
        for (Player p : playerList) {
            if (p.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
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

    public List<UserPass> getUserList(){
        return userList;
    }

    public Player getPlayer(UserPass user){
        for(Player p: playerList){
            if(p.getUserName().equals(user)){
                return p;
            }
        }
        return null;
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