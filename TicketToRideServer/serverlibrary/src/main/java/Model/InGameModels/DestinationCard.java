package Model.InGameModels;

/**
 * Created by Master_Chief on 5/23/2018.
 */

public class DestinationCard {
    private int ID;
    private int pointValue;
    private City city1;
    private City city2;

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
