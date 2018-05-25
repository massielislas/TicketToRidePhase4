package Model.InGameModels;

import Model.InGameModels.City;

public class DestinationCard {

    City firstcity;
    City secondCity;
    int pointvalue;
    int cardNo;

    public DestinationCard(City firstcity, City secondCity, int pointvalue, int cardNo) {
        this.firstcity = firstcity;
        this.secondCity = secondCity;
        this.pointvalue = pointvalue;
        this.cardNo = cardNo;
    }

    public City getFirstcity() {
        return firstcity;
    }

    public City getSecondCity() {
        return secondCity;
    }

    public int getPointvalue() {
        return pointvalue;
    }

    public int getCardNo() {
        return cardNo;
    }


}
