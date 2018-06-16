package Model.InGameModels;

/**
 * Created by Master_Chief on 5/23/2018.
 */

public class DestinationCard {
    private int ID;
    private int pointValue;
    private City city1;
    private City city2;
    private boolean complete;

    public DestinationCard(City city1, City city2, int pointValue, int ID) {
        this.city1 = city1;
        this.city2 = city2;
        this.pointValue = pointValue;
        this.ID = ID;
        complete = false;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinationCard that = (DestinationCard) o;

        if (ID != that.ID) return false;
        if (pointValue != that.pointValue) return false;
        if (!city1.equals(that.city1)) return false;
        return city2.equals(that.city2);
    }

    @Override
    public int hashCode()
    {
        int result = ID;
        result = 31 * result + pointValue;
        result = 31 * result + city1.hashCode();
        result = 31 * result + city2.hashCode();
        return result;
    }
}
