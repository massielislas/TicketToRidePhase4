package Model;

import java.util.List;

import Model.InGameModels.City;

public class RestoreClientInfo {
    private String ID;
    private Double playerCount;
    private String jsonUpdateInfo;
    private Double turnNumber;
    private List<City> cities;
    private List<String> chat;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Double playerCount) {
        this.playerCount = playerCount;
    }

    public String getJsonUpdateInfo() {
        return jsonUpdateInfo;
    }

    public void setJsonUpdateInfo(String jsonUpdateInfo) {
        this.jsonUpdateInfo = jsonUpdateInfo;
    }

    public Double getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Double turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<String> getChat()
    {
        return chat;
    }

    public void setChat(List<String> chat)
    {
        this.chat = chat;
    }
}