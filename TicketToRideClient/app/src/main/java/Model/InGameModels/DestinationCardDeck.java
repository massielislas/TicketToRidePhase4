package Model.InGameModels;

import java.util.ArrayList;


public class DestinationCardDeck {

    public ArrayList<DestinationCard> destinationCards;
    public DestinationCardDeck(){
        int i = 0;
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Montreal"),
                Cities.getInstance().findCity("Atlanta"),9,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Montreal"),
                Cities.getInstance().findCity("New Orleans"),13,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Duluth"),
                Cities.getInstance().findCity("El Paso"),10,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("San Fran"),
                Cities.getInstance().findCity("Atlanta"),17,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Duluth"),
                Cities.getInstance().findCity("Houston"),8,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("New York"),
                Cities.getInstance().findCity("Atlanta"),6,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Sault Ste. Marie"),
                Cities.getInstance().findCity("Atlanta"),9,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Helena"),
                Cities.getInstance().findCity("Los Angeles"),8,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Los Angeles"),
                Cities.getInstance().findCity("New York"),21,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Los Angeles"),
                Cities.getInstance().findCity("Chicago"),16,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Vancouver"),
                Cities.getInstance().findCity("Montreal"),20,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Seattle"),
                Cities.getInstance().findCity("Los Angeles"),9,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Chicago"),
                Cities.getInstance().findCity("Sante Fe"),9,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Denver"),
                Cities.getInstance().findCity("Pittsburgh"),11,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Portland"),
                Cities.getInstance().findCity("Nashville"),17,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Toronto"),
                Cities.getInstance().findCity("Miami"),10,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Los Angeles"),
                Cities.getInstance().findCity("Miami"),20,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Kansas City"),
                Cities.getInstance().findCity("Houston"),5,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Winnipeg"),
                Cities.getInstance().findCity("Litle Rock"),11,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Chicago"),
                Cities.getInstance().findCity("New Orleans"),7,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Seattle"),
                Cities.getInstance().findCity("New York"),22,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Vancouver"),
                Cities.getInstance().findCity("Seattle"),13,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Calgary"),
                Cities.getInstance().findCity("Phoenix"),13,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Boston"),
                Cities.getInstance().findCity("Miami"),12,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Calgary"),
                Cities.getInstance().findCity("Salt Lake"),7,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Sault Ste. Marie"),
                Cities.getInstance().findCity("Nashville"),8,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Winnipeg"),
                Cities.getInstance().findCity("Houston"),12,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Denver"),
                Cities.getInstance().findCity("El Paso"),4,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Portland"),
                Cities.getInstance().findCity("Phoenix"),11,i++));
        destinationCards.add(new DestinationCard(Cities.getInstance().findCity("Dallas"),
                Cities.getInstance().findCity("New York"),11,i++));
    }
    public DestinationCard getDestinationCard(int id) {
        for(DestinationCard card:destinationCards){
            if(card.getID() == id)
            {
                return card;
            }
        }
        return null;
    }
    public ArrayList<DestinationCard> getDestinationCards(){
        return destinationCards;
    }
}
