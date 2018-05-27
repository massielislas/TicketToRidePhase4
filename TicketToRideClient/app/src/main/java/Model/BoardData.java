package Model;

import java.util.List;

import Model.InGameModels.Chat;
import Model.InGameModels.City;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;

public class BoardData {

    private UserData userData = UserData.getUserData();
    private Chat chat = Chat.getInstance();
    private Player currentPlayer = userData.getCurrentPlayer();
    private List<PlayerShallow> otherPlayerInfo;
    private int destDeckSize;
    private int trainDeckSize;
    private List<TrainCard> faceUpCards;
    private List<City> cities;
    private List<Route> routes;

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

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
