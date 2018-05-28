package root.tickettorideclient.Views;

/**
 * Created by Massiel on 5/25/2018.
 */

public class PlayerStats {

    int trainPieces;
    int trainCards;
    int destinationCards;
    int points;
    String username;

    public int getTrainPieces() {
        return trainPieces;
    }
    public int getTrainCards() {
        return trainCards;
    }
    public int getDestinationCards() {
        return destinationCards;
    }
    public int getPoints() { return points; }
    public String getUsername() {
        return username;
    }


    public void setTrainPieces(int trainPieces) {
        this.trainPieces = trainPieces;
    }
    public void setTrainCards(int trainCards) {
        this.trainCards = trainCards;
    }
    public void setDestinationCards(int destinationCards) { this.destinationCards = destinationCards; }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPoints(int points) { this.points = points;}
}
