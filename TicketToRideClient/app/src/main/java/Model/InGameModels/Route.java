package Model.InGameModels;

import android.graphics.Color;

import java.util.Observable;

import Model.InGameModels.City;
import Model.UserPass;

public class Route extends Observable {

    private int length;
    private String color;
    private boolean isClaimed;
    private UserPass claimant;
    private int scoreValue;
    private int ID;
    private City city1;
    private City city2;

    public Route(int length, String color, int scoreValue, int ID, City city1, City city2) {
        this.length = length;
        this.color = color;
        this.scoreValue = scoreValue;
        this.ID = ID;
        this.city1 = city1;
        this.city2 = city2;
        isClaimed = false;
        claimant = null;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public UserPass getClaimant() {
        return claimant;
    }

    public void setClaimant(UserPass claimant) {
        this.claimant = claimant;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) { this.color = color;
    }
}
