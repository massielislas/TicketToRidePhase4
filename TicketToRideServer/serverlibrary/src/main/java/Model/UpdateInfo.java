package Model;

import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;


public class UpdateInfo {
    private int turnNumber;
    private int destDeckSize;
    private int trainDeckSize;
    private List<PlayerShallow> playerInfo;
    private TrainCard[] currentFaceUpCards;
    private Route[] gameRoutes;
    private Route[] playerRoutes;
    private List<TrainCard> hand;
    private int points;
    private int piecesLeft;

    public UpdateInfo(int turnNumber, List<PlayerShallow>otherplayers, TrainCard[] currentCards, int trainDeckSize, int destDeckSize) {
        this.turnNumber = turnNumber;
        this.trainDeckSize = trainDeckSize;
        this.destDeckSize = destDeckSize;
        currentFaceUpCards = currentCards;
        playerInfo = otherplayers;
    }

    public int getPoints() {
        return points;
    }

    public int getPiecesLeft() {
        return piecesLeft;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPiecesLeft(int trainCards) {
        this.piecesLeft = trainCards;
    }

    public Route[] getGameRoutes() {
        return gameRoutes;
    }

    public void setGameRoutes(Route[] gameRoutes) {
        this.gameRoutes = gameRoutes;
    }

    public Route[] getPlayerRoutes() {
        return playerRoutes;
    }

    public void setPlayerRoutes(Route[] playerRoutes) {
        this.playerRoutes = playerRoutes;
    }

    public List<TrainCard> getHand() {
        return hand;
    }

    public void setHand(List<TrainCard> hand) {
        this.hand = hand;
    }

    public int getDestDeckSize() {
        return destDeckSize;
    }

    public int getTrainDeckSize() {
        return trainDeckSize;
    }

    public TrainCard[] getCurrentFaceUpCards() {
        return currentFaceUpCards;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public List<PlayerShallow> getPlayerInfo() {
        return playerInfo;
    }

    public void setCurrentFaceUpCards(TrainCard[] currentFaceUpCards) {
        this.currentFaceUpCards = currentFaceUpCards;
    }

    public void setPlayerInfo(List<PlayerShallow> playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setDestDeckSize(int destDeckSize) {
        this.destDeckSize = destDeckSize;
    }

    public void setTrainDeckSize(int trainDeckSize) {
        this.trainDeckSize = trainDeckSize;
    }
}

