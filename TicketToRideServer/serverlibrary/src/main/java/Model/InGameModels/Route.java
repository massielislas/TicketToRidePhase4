package Model.InGameModels;

import Model.UserPass;

/**
 * Created by Master_Chief on 5/23/2018.
 */

public class Route {
    private City city1;
    private City city2;
    private int length;
    private boolean isClaimed;
    private UserPass claimant;
    private int scoreValue;
    private int ID;
    private double Color;

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

    public double getColor() {
        return Color;
    }

    public void setColor(double color) {
        Color = color;
    }
}
