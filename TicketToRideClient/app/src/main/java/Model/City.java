package Model;

import com.google.android.gms.maps.model.LatLng;

public class City {

    String name;
    LatLng coordinates;

    public City(String name, LatLng coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}
