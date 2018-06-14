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
    private String claimant;
    private String doubleClaimant;
    private int scoreValue;
    private int ID;
    private int doubleID;
    private String color;
    private String doubleColor;

    public String getDoubleColor() {
        return doubleColor;
    }

    public void setDoubleColor(String doubleColor) {
        this.doubleColor = doubleColor;
    }


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
    public Route(City newCity1, City newCity2, int newLength, String newColor, int newID, String newDoubleColor, int newDoubleID){
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
        doubleID = newDoubleID;
    }


    public boolean isDouble() {
        return isDouble;
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

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getDoubleClaimant() {
        return doubleClaimant;
    }

    public void setDoubleClaimant(String doubleClaimant) {
        this.doubleClaimant = doubleClaimant;
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

    public boolean isDoubleClaimed() {
        return isDoubleClaimed;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (length != route.length) return false;
        if (isClaimed != route.isClaimed) return false;
        if (isDouble != route.isDouble) return false;
        if (isDoubleClaimed != route.isDoubleClaimed) return false;
        if (scoreValue != route.scoreValue) return false;
        if (ID != route.ID) return false;
        if (doubleID != route.doubleID) return false;
        if (!city1.equals(route.city1)) return false;
        if (!city2.equals(route.city2)) return false;
        if (claimant != null ? !claimant.equals(route.claimant) : route.claimant != null)
            return false;
        if (doubleClaimant != null ? !doubleClaimant.equals(route.doubleClaimant) : route.doubleClaimant != null)
            return false;
        if (!color.equals(route.color)) return false;
        return doubleColor.equals(route.doubleColor);
    }

    @Override
    public int hashCode()
    {
        int result = city1.hashCode();
        result = 31 * result + city2.hashCode();
        result = 31 * result + length;
        result = 31 * result + (isClaimed ? 1 : 0);
        result = 31 * result + (isDouble ? 1 : 0);
        result = 31 * result + (isDoubleClaimed ? 1 : 0);
        result = 31 * result + (claimant != null ? claimant.hashCode() : 0);
        result = 31 * result + (doubleClaimant != null ? doubleClaimant.hashCode() : 0);
        result = 31 * result + scoreValue;
        result = 31 * result + ID;
        result = 31 * result + doubleID;
        result = 31 * result + color.hashCode();
        result = 31 * result + doubleColor.hashCode();
        return result;
    }
}
