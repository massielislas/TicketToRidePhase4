package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.InGameModels.Chat;
import Model.InGameModels.City;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;

public class BoardData extends Observable{

    private UserData userData = UserData.getUserData();
    private Chat chat = Chat.getInstance();
    private Player currentPlayer;
    private String userPlaying;
    private List<PlayerShallow> otherPlayerInfo;
    private int destDeckSize;
    private int trainDeckSize;
    private TrainCard[] faceUpCards;
    private List<City> cities;
    private List<Route> routes;

    public void setChange()
    {
        setChanged(); //set change has occurred
        notifyObservers(this); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public void removeAnObserver(Observer o) {deleteObserver(o);}

    public Chat getChat() {
        return chat;
    }

    public String getUserPlaying() {
        return userPlaying;
    }

    public void setUserPlaying(String userPlaying) {
        this.userPlaying = userPlaying;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<PlayerShallow> getOtherPlayerInfo() {
        return otherPlayerInfo;
    }

    public void setOtherPlayerInfo(List<PlayerShallow> otherPlayerInfo) {
        this.otherPlayerInfo = otherPlayerInfo;
    }

    public int getDestDeckSize() {
        return destDeckSize;
    }

    public void setDestDeckSize(int destDeckSize) {
        this.destDeckSize = destDeckSize;
    }

    public int getTrainDeckSize() {
        return trainDeckSize;
    }

    public void setTrainDeckSize(int trainDeckSize) {
        this.trainDeckSize = trainDeckSize;
    }

    public TrainCard[] getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(TrainCard[] faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
