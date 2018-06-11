package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.Routes;
import Model.InGameModels.TrainCard;
import Model.InGameModels.TrainCardDeck;
import Results.Result;


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
    private final int destinationCardDealNumber = 3;
    //Number of the game in the queue
    private int gameNumber;
    private int turnNumber;
    //Max number of players
    private int playerCount;
    private int currentPlayers;
    private boolean isLastRound;
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
    private Routes routes;

    public Game(int playerCount, int currentPlayers) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.ID = UUID.randomUUID().toString();
        playerList = new ArrayList<>();
        userList = new ArrayList<>();
        routes = new Routes();
        trainCardFacedownDeck = new ArrayList<>();
        trainCardFaceupDeck = new TrainCard[faceupSize];
        discardedTrainCards = new ArrayList<>();
        DestinationCardDeck deck = new DestinationCardDeck();
        destinationCardDeck = deck.getDestinationCards();
        chat = new ArrayList<>();
        turnNumber = 1;
        isLastRound = false;
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
        turnNumber = 1;
        isLastRound = false;
        routes = new Routes();
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
            trainCardFacedownDeck.remove(0);
        }
    }


    public SinglePlayerStartInfo dealStartingHand(Player p) {

        SinglePlayerStartInfo startingInfo = new SinglePlayerStartInfo(p.getUserName(), p.getTurnNumber());
        //Put the first 4 and 3 Train and Destination Cards to the starting hand package
        for (int i = 0; i < startingTrainHandSize; i ++) {
            startingInfo.addTrainCard(trainCardFacedownDeck.get(i));
            p.addTrainCard(trainCardFacedownDeck.get(i));
            if (i < destinationCardDealNumber) {
                startingInfo.addDestCard(destinationCardDeck.get(i));
                p.addDestCard(destinationCardDeck.get(i));
            }
        }
        //Then remove the corresponding cards from the corresponding decks
        for (int i = 0; i < startingTrainHandSize; i++) {
            trainCardFacedownDeck.remove(0);
            if (i < destinationCardDealNumber) {
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

    public UpdateInfo getUpdateInfo(Player p){
        UpdateInfo toReturn = new UpdateInfo(turnNumber,getPlayerShallows(p),getTrainCardFaceupDeck(),new Double(getTrainCardDeckSize()).intValue(),
                    new Double(getDestCardDeckSize()).intValue());
        toReturn.setHand(p.getTrainCards());
        toReturn.setPlayerRoutes(p.getRoutesClaimed().toArray(new Route[0]));
        toReturn.setGameRoutes(routes.getRouteList().toArray(new Route[0]));
        toReturn.setPiecesLeft(p.getTrainPiecesLeft());
        toReturn.setPoints(p.getCurrentScore());
        return toReturn;
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

    public void updateDeckSizeCommand(Double trainDeck, Double DestDeck) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.Double", "java.lang.Double"};
        Object[] methodArguments = { trainDeck, DestDeck };
        Command command = new Command("Model.PlayFacade", "getInstance",
                "updateDeckSize", instanceParamTypeNames, instanceMethodArgs,
                methodParamTypeNames, methodArguments);
        CommandManager.getInstance().addCommandAllUsers(command);
    }

    public void addDestCardBackIn(DestinationCard card) {
        destinationCardDeck.add(card);
    }

    public Result claimRoute(String username, Double routeID) {
        if(routeID < 0){
            int realID = (routeID.intValue())*(-1);
            Route toClaim = routes.getRoute(realID);
            return claimDoubleRoute(username,realID);
            //Claim the double route
        }
        else {
            Route toClaim = routes.getRoute(routeID.intValue());
            Player claimer = getPlayer(new UserPass(username));
            //If the route is a double route, use a different function
//            if (toClaim.isDouble()) {
//                return claimDoubleRoute(username, routeID.intValue());
//            }
            //If it's not a double route but it's already claimed, return false
            if (toClaim.isClaimed()) {
                return new Result(false, "That route is already claimed by" + toClaim.getClaimant() + "!");
            }
            //otherwise update the route's claimant, set its claimed bool as true, and return true
            else {
                toClaim.setClaimed(true);
                toClaim.setClaimant(claimer.getUserName().getNameOrPassword());
                List<TrainCard> toDiscard = claimer.addRoute(toClaim, false);
                discardTrainCards(toDiscard);
                claimer.setTrainPiecesLeft(claimer.getTrainPiecesLeft()-toClaim.getLength());
                claimer.setCurrentScore(claimer.getCurrentScore()+toClaim.getScoreValue());
                return new Result(true, "You claimed route " + toClaim.getID() + " from "
                        + toClaim.getCity1() + " to " + toClaim.getCity2());
            }
        }
    }

    private Result claimDoubleRoute(String userName, int routeID) {
        Route toClaim = routes.getRoute(routeID);
        Player claimer = getPlayer(new UserPass(userName));
//        if (toClaim.isDoubleClaimedl() && toClaim.isClaimed()) {
//            return new Result(false, "Both of those routes are already claimed!");
//            return new Result(false, "Both of those routes are already claimed!");
//        }
//        else if (!toClaim.isClaimed() && routeID > 0) {
//            toClaim.setClaimed(true);
//            List<TrainCard> toDiscard = claimer.addRoute(toClaim, false);
//            discardTrainCards(toDiscard);
//            return new Result(true, "You claimed route " +toClaim.getID() + " from "
//            + toClaim.getCity1() + " to " + toClaim.getCity2());
//        }
        if (!toClaim.isDoubleClaimed()) {
            toClaim.setDoubleClaimed(true);
            toClaim.setDoubleClaimant(claimer.getUserName().getNameOrPassword());
            List<TrainCard> toDiscard = claimer.addRoute(toClaim, true);
            discardTrainCards(toDiscard);
            claimer.setTrainPiecesLeft(claimer.getTrainPiecesLeft()-toClaim.getLength());
            claimer.setCurrentScore(claimer.getCurrentScore()+toClaim.getScoreValue());
            return new Result(true, "You claimed route " +toClaim.getID() + " from "
                    + toClaim.getCity1() + " to " + toClaim.getCity2());
        }
        else {
            return new Result(false, "That route is already claimed!");
        }
        //TODO finish this method
    }

    public Result chooseFaceUpCard(String username, Double cardID) {
        Player addToHand = getPlayer(new UserPass(username));
        TrainCard toAdd = getFromFaceUpByID(cardID.intValue());
        if (toAdd == null) {
            return new Result(false, "That Spot is Empty!!");
        }
        else {
            addToHand.addTrainCard(toAdd);
            updateFaceUpDeck();
            return new Result(true,toAdd.getType() +" card");
        }
    }
    public Result drawFromTrainDeck(String username) {
        if (trainCardFacedownDeck.isEmpty()) {
            reshuffleDiscardedTrains();
        }
        Player personDrawing = getPlayer(new UserPass(username));
        TrainCard toAdd = trainCardFacedownDeck.get(0);
        personDrawing.addTrainCard(toAdd);
        trainCardFacedownDeck.remove(0);
        return new Result(true, toAdd.getType() + " drawn from deck");
    }

    public Result drawDestCards(String username) {
        Player personDrawing = getPlayer(new UserPass(username));
        //If the destination card deck size is smaller than 3, draw all of the rest of the cards
        if (destinationCardDeck.size() < 3) {
            int size = destinationCardDeck.size();
            for (int i = 0; i < destinationCardDeck.size(); i++) {
                personDrawing.addDestCard(destinationCardDeck.get(0));
                //personDrawing.addDestCardToChoose(destinationCardDeck.get(0));
                destinationCardDeck.remove(0);
            }
            //if there are none, send a message saying so
            if (size == 0) {
                return new Result(false, "destination card deck is empty");
            }
            return new Result(true, "drew " + size + " destination cards");
        }
        //Otherwise draw three cards
        else {
            for (int i = 0; i < 3; i++) {
                personDrawing.addDestCard(destinationCardDeck.get(0));
                //personDrawing.addDestCardToChoose(destinationCardDeck.get(0));
                destinationCardDeck.remove(0);
            }
            return new Result(true, "drew 3 destination cards");
        }
    }

    private void discardTrainCards(List<TrainCard> toDiscard) {
        for (TrainCard card : toDiscard) {
            discardedTrainCards.add(card);
        }
    }

    public int updateTurn() {
        if (turnNumber == playerList.size()) {
            turnNumber = 1;
        }
        else {
            turnNumber++;
        }
        Player whoseTurn = playerList.get(turnNumber - 1);
        if (isLastRound = false && whoseTurn.getTrainPiecesLeft() <= 3) {
            isLastRound = true;
        }
        return turnNumber;
    }

    private TrainCard getFromFaceUpByID(int ID) {
        //Find the card by its ID number in the face up deck. Set the corresponding slot to null
        //then return it
        for (int i = 0; i < trainCardFaceupDeck.length; i ++) {
            if(trainCardFaceupDeck[i] != null) {
                if (trainCardFaceupDeck[i].getID() == ID) {
                    TrainCard toRet = trainCardFaceupDeck[i];
                    trainCardFaceupDeck[i] = null;
                    return toRet;
                }
            }
        }
        return null;
    }

    private void updateFaceUpDeck() {
        //Check if there are cards left in the trainCardDeck
        if (trainCardFacedownDeck.isEmpty()) {
            reshuffleDiscardedTrains();
        }
        //For each card missing from the face up deck, put the top card of the face down deck in its
        //place
        for (int i = 0; i < trainCardFaceupDeck.length; i++) {
            if (trainCardFaceupDeck[i] == null) {
                if (!trainCardFacedownDeck.isEmpty() || (discardedTrainCards.size() != 0)) {
                    TrainCard workWith = trainCardFacedownDeck.get(0);
                    trainCardFaceupDeck[i] = workWith;
                    trainCardFacedownDeck.remove(0);
                    //always check and reshuffle if needed
                    if (trainCardFacedownDeck.isEmpty()) {
                        reshuffleDiscardedTrains();
                    }
                }
            }
        }
        //Then check if there are 3 or more wilds, reset the whole face up deck if there are 3 or more
        checkAndResetFaceUp();
        //TODO Set up command to update face up deck on Client side
    }

    private void checkAndResetFaceUp() {
        //count of locomotives in the face up deck
        int count = 0;
        for (int i = 0; i < trainCardFaceupDeck.length; i++) {
            if ((trainCardFaceupDeck[i] != null) && (trainCardFaceupDeck[i].getType().equals("Locomotive"))) {
                count++;
            }
        }
        //Then if the count is greater than 2, discard all the cards and get 5 new cards from the
        //top of the face down deck
        if (count > 2) {
            for (int i = 0; i < trainCardFaceupDeck.length; i++) {
                discardedTrainCards.add(trainCardFaceupDeck[i]);
                trainCardFaceupDeck[i] = trainCardFacedownDeck.get(0);
                trainCardFacedownDeck.remove(0);
                if (trainCardFacedownDeck.isEmpty()) {
                    reshuffleDiscardedTrains();
                }
            }
            checkAndResetFaceUp();
        }
    }

    public int getTurnNumber() {
        return turnNumber;
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

    public double getTrainCardDeckSize() {
        return (double) trainCardFacedownDeck.size();
    }

    public double getDestCardDeckSize() {
        return (double) destinationCardDeck.size();
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