package Model;

import android.graphics.Color;

import java.util.Observable;

public class Route extends Observable {

    int length;
    Color color;
    boolean isClaimed;
    UserPass claimedBy;
    int score;
    int ID;
    City cityOne;
    City cityTwo;

    public Route(int length, Color color, int score, int ID, City cityOne, City cityTwo) {
        this.length = length;
        this.color = color;
        this.score = score;
        this.ID = ID;
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        isClaimed = false;
        claimedBy = null;
    }

    public int getLength() {
        return length;
    }

    public Color getColor() {
        return color;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public UserPass getClaimedBy() {
        return claimedBy;
    }

    public int getScore() {
        return score;
    }

    public int getID() {
        return ID;
    }

    public City getCityOne() {
        return cityOne;
    }

    public City getCityTwo() {
        return cityTwo;
    }
}
