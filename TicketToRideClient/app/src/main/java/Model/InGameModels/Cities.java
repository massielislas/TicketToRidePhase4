package Model.InGameModels;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topper on 5/25/2018.
 */

public class Cities {
    private static Cities instance = null;
    List<City> cityList = new ArrayList<>();
    final int NUM_CITIES = 35;
    private Cities(){
        Double[] lats = {33.749, 42.3601, 51.0486, 32.7765, 41.8781, 32.7767, 39.7392, 46.7867,
                31.7619, 46.5891, 29.7604, 39.0997, 34.7465, 34.0522, 36.1699, 25.7617, 45.5017,
                36.1627, 29.9511, 40.7128, 35.4676, 41.2565, 33.4484, 40.4406, 45.5231, 35.7796,
                38.627, 40.7608, 37.7749, 35.687, 47.6062, 43.6532, 49.2827, 38.9072, 49.8951,
                46.5136};
        Double[] longs = {84.388, 71.0589, 114.0708, 79.9311, 87.6298, 96.797, 104.9903, 92.1005,
                106.485, 112.0391, 95.3698, 94.5786, 92.2896, 118.2437, 115.1398, 80.1918, 73.5673,
                86.7816, 90.0715, 74.006, 97.5164, 95.9345, 112.074, 79.9959, 122.6765, 78.6382,
                90.1994, 111.891, 122.4194, 105.9378, 122.3321, 79.3832, 123.1207, 77.0369, 97.1384,
                84.3358};
        String[] cityNames = {"Atlanta", "Boston", "Calgary", "Charleston", "Chicago", "Dallas", "Denver", "Duluth",
                "El Paso", "Helena", "Houston", "Kansas City", "Little Rock", "Los Angeles", "Los Vegas",
                "Miami", "Montreal", "Nashville", "New Orleans", "New York", "Oklahoma City", "Omaha",
                "Phoenix", "Pittsburgh", "Portland", "Raleigh", "Saint Louis", "Salt Lake",
                "San Francisco", "Santa Fe", "Seattle", "Toronto", "Vancouver", "Washington DC",
                "Winnipeg", "Sault St. Marie"};
       for(int i = 0; i < NUM_CITIES; i++){
           cityList.add(new City(cityNames[i],lats[i],longs[i]));
       }
    }

    public static Cities getInstance(){
        if(instance == null)
            instance = new Cities();
        return  instance;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public City findCity(String cityName){
        for(City c: cityList){
            if(c.getName().equals(cityName))
            {
                return c;
            }
        }
        return null;
    }
    public List<City> getCities() {
        return cityList;
    }
}
