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
    private boolean isDouble;
    private boolean isDoubleClaimed;
    private UserPass claimant;
    private int scoreValue;
    private int ID;
    private String color;
    private String doubleColor;


    public Route(City newCity1, City newCity2, int newLength, String newColor, int newID){
        city1 = newCity1;
        city2 = newCity2;
        length = newLength;
        switch (newLength){
            case 1:
                scoreValue = 1;
                break;
            case 2:
                scoreValue = 2;
                break;
            case 3:
                scoreValue = 4;
                break;
            case 4:
                scoreValue = 7;
                break;
            case 5:
                scoreValue = 10;
                break;
            case 6:
                scoreValue = 15;
                break;
        }
        color = newColor;
        ID = newID;
    }
    public Route(City newCity1, City newCity2, int newLength, String newColor, int newID, String newDoubleColor){
        city1 = newCity1;
        city2 = newCity2;
        length = newLength;
        switch (newLength){
            case 1:
                scoreValue = 1;
                break;
            case 2:
                scoreValue = 2;
                break;
            case 3:
                scoreValue = 4;
                break;
            case 4:
                scoreValue = 7;
                break;
            case 5:
                scoreValue = 10;
                break;
            case 6:
                scoreValue = 15;
                break;
        }
        color = newColor;
        ID = newID;
        isDouble = true;
        doubleColor = newDoubleColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (length != route.length) return false;
        if (isClaimed != route.isClaimed) return false;
        if (isDouble != route.isDouble) return false;
        if (isDoubleClaimed != route.isDoubleClaimed) return false;
        if (scoreValue != route.scoreValue) return false;
        if (ID != route.ID) return false;
        if (!city1.equals(route.city1)) return false;
        if (!city2.equals(route.city2)) return false;
        if (claimant != null ? !claimant.equals(route.claimant) : route.claimant != null)
            return false;
        if (color != null ? !color.equals(route.color) : route.color != null) return false;
        return doubleColor != null ? doubleColor.equals(route.doubleColor) : route.doubleColor == null;
    }

    @Override
    public int hashCode() {
        int result = city1.hashCode();
        result = 31 * result + city2.hashCode();
        result = 31 * result + length;
        result = 31 * result + (isClaimed ? 1 : 0);
        result = 31 * result + (isDouble ? 1 : 0);
        result = 31 * result + (isDoubleClaimed ? 1 : 0);
        result = 31 * result + (claimant != null ? claimant.hashCode() : 0);
        result = 31 * result + scoreValue;
        result = 31 * result + ID;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (doubleColor != null ? doubleColor.hashCode() : 0);
        return result;
    }

    public boolean isDouble() {
        return isDouble;
    }

    public boolean isDoubleClaimedl() {
        return isDoubleClaimed;
    }

    public void setDoubleClaimed(boolean doubleClaimed) {
        isDoubleClaimed = doubleClaimed;
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

    public void setColor(String color) {
        this.color = color;
    }
}
