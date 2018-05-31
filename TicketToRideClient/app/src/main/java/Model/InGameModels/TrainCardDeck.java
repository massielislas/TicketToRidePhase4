package Model.InGameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topper on 5/26/2018.
 */

public class TrainCardDeck {
    private List<TrainCard> trainCards;
    public TrainCardDeck()
    {
        trainCards = new ArrayList<>();
        int id = 0;
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Pink","Box"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"White","Passenger"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Blue","Tanker"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Yellow","Reefer"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Orange","Freight"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Black","Hopper"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Red","Coal"));
        }
        for(int i = 0; i < 12; i++){
            trainCards.add(new TrainCard(id++,"Green","Caboose"));
        }
        for(int i = 0; i < 14; i++){
            trainCards.add(new TrainCard(id++,"Gray","Locomotive"));
        }
    }

    public TrainCard getCardByID(Integer ID)
    {
        for (TrainCard card: trainCards)
            if (card.getID() == ID) return card;
        return null;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }
}