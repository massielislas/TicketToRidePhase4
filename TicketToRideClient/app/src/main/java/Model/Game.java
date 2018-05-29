package Model;

import android.annotation.TargetApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import Model.InGameModels.Chat;
import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;

public class Game extends Observable
{
    int gameNumber;

    String ID;

    int playerCount; //player count set for game

    int currentPlayers; //current in game

    ArrayList<DestinationCard> destinationDeck;
    ArrayList<Player> players;
    List<Route> routes;
    List<City> cities;
    ArrayList<TrainCard> faceUpTrainDeck;
    List<TrainCard> faceDownTrainDeck;
    Chat chat;

    Game(int playerCount, int currentPlayers, int gameNumber) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = UUID.randomUUID().toString();
    }

    Game(int playerCount, int currentPlayers, int gameNumber, String ID) {
        this.playerCount = playerCount;
        this.currentPlayers = currentPlayers;
        this.gameNumber = gameNumber;
        this.ID = ID;
        this.destinationDeck = new DestinationCardDeck().getDestinationCards();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public ArrayList<DestinationCard> getDestinationDeck() {
        return destinationDeck;
    }

    public void setDestinationDeck(ArrayList<DestinationCard> destinationDeck) {
        this.destinationDeck = destinationDeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<TrainCard> getFaceUpTrainDeck() {
        return faceUpTrainDeck;
    }

    public void setFaceUpTrainDeck(ArrayList<TrainCard> faceUpTrainDeck) {
        this.faceUpTrainDeck = faceUpTrainDeck;
    }

    public List<TrainCard> getFaceDownTrainDeck() {
        return faceDownTrainDeck;
    }

    public void setFaceDownTrainDeck(List<TrainCard> faceDownTrainDeck) {
        this.faceDownTrainDeck = faceDownTrainDeck;
    }

    void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public void removeAnObserver(Observer o) {deleteObserver(o);}

    public ArrayList<DestinationCard> getSelectedDestinationCards(ArrayList<Double> toGet)
    {
        ArrayList<DestinationCard> selectedCards = new ArrayList<DestinationCard>();
        for (Double cardID: toGet)
        {
            if (findSelectedDestinationCard(cardID) != null) selectedCards.add(findSelectedDestinationCard(cardID));
        }
        return selectedCards;
    }

    public DestinationCard findSelectedDestinationCard(Double cardID)
    {
        for (DestinationCard currentCard: destinationDeck)
        {
            if (cardID == currentCard.getID()) return currentCard;
        }
        return null; //we should never get here!
        //TODO
        //decide to make decks observable or just change how game implements observable?
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    void addPlayer()
    {
        currentPlayers++;
        setChanged(); //set change has occurred
        notifyObservers(currentPlayers); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    int getGameNumber() {
        return gameNumber;
    }

    void setGameNumber(int gameNumber) {
    }

    int getPlayerCount()
    {
        return playerCount;
    }

    void setPlayerCount(int playerCount)
    {
        this.playerCount = playerCount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    @Override
    @TargetApi(19)
    public int hashCode() {
        return Objects.hash(gameNumber, ID, currentPlayers, playerCount);
    }
}
