package Model;

import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.TrainCard;

public class SetUpData {

    private int turnNumber;
    private String color;
    private List<DestinationCard> startingDestCards;
    private List<TrainCard> startingTrainCards;

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
