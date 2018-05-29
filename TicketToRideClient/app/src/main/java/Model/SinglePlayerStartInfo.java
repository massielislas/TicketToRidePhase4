package Model;

import java.util.ArrayList;
import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.TrainCard;

/**
 * Created by Master_Chief on 5/24/2018.
 */

public class SinglePlayerStartInfo {
    private UserPass username;
    private int turnNumber;
    private List<DestinationCard> startingDestCards;
    private List<TrainCard> startingTrainCards;
    private List<PlayerShallow> playerInfo;
    private TrainCard[] startingFaceUpCards;

    public SinglePlayerStartInfo(UserPass username, int turnNumber) {
        this.username = username;
        this.turnNumber = turnNumber;
        startingDestCards = new ArrayList<>();
        startingTrainCards = new ArrayList<>();
    }

    public TrainCard[] getStartingFaceUpCards() {
        return startingFaceUpCards;
    }

    public void setStartingFaceUpCards(TrainCard[] startingFaceUpCards) {
        this.startingFaceUpCards = startingFaceUpCards;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public List<PlayerShallow> getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(List<PlayerShallow> playerInfo) {
        this.playerInfo = playerInfo;
    }

    public void addTrainCard(TrainCard toAdd) {
        startingTrainCards.add(toAdd);
    }

    public void addDestCard(DestinationCard toAdd) {
        startingDestCards.add(toAdd);
    }

    public UserPass getUsername() {
        return username;
    }

    public void setUsername(UserPass username) {
        this.username = username;
    }

    public List<DestinationCard> getStartingDestCards() {
        return startingDestCards;
    }

    public void setStartingDestCards(List<DestinationCard> startingDestCards) {
        this.startingDestCards = startingDestCards;
    }

    public List<TrainCard> getStartingTrainCards() {
        return startingTrainCards;
    }

    public void setStartingTrainCards(List<TrainCard> startingTrainCards) {
        this.startingTrainCards = startingTrainCards;
    }
}

