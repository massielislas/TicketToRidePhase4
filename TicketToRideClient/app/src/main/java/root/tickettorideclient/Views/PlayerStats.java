package root.tickettorideclient.Views;

/**
 * Created by Massiel on 5/25/2018.
 */

public class PlayerStats {
    public int getTrainPieces() {
        return trainPieces;
    }

    public void setTrainPieces(int trainPieces) {
        this.trainPieces = trainPieces;
    }

    public int getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(int trainCards) {
        this.trainCards = trainCards;
    }

    public int getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(int destinationCards) {
        this.destinationCards = destinationCards;
    }

    int trainPieces;
    int trainCards;
    int destinationCards;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;
}
