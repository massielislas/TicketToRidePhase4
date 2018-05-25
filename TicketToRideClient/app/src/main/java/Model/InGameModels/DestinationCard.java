package Model.InGameModels;

import Model.InGameModels.City;

public class DestinationCard {

    private City city1;
    private City city2;
    private int pointValue;
    private int ID;

    public DestinationCard(City city1, City city2, int pointValue, int ID) {
        this.city1 = city1;
        this.city2 = city2;
        this.pointValue = pointValue;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
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


}
