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
    private Route[] routes;

    public UpdateInfo(int turnNumber, List<PlayerShallow>otherplayers, TrainCard[] currentCards) {
        this.turnNumber = turnNumber;
        currentFaceUpCards = currentCards;
        playerInfo = otherplayers;
    }

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
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

