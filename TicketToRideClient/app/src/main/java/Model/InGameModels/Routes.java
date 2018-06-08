package Model.InGameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Topper on 5/25/2018.
 */

public class Routes
{
    //private final static Routes instance = new Routes();
    List<Route> routeList = new ArrayList<>();
    public Routes(){
        String[] startCities = {"Vancouver", "Vancouver", "Vancouver", "Seattle", "Seattle", "Seattle",
                "Seattle", "Portland", "Portland", "Portland", "San Francisco", "San Francisco", "San Francisco",
                "San Francisco", "Los Angeles", "Los Angeles", "Los Angeles", "Las Vegas", "Salt Lake",
                "Salt Lake", "Salt Lake", "Helena", "Helena", "Helena", "Helena", "Helena", "Denver",
                "Denver", "Denver", "Denver", "Denver", "Denver", "Phoenix", "Phoenix", "Santa Fe",
                "Santa Fe", "El Paso", "El Paso", "El Paso", "Winnipeg", "Winnipeg", "Duluth", "Duluth",
                "Duluth", "Duluth", "Duluth", "Omaha", "Omaha", "Omaha", "Kansas City", "Kansas City",
                "Kansas City", "Kansas City", "Oklahoma City", "Oklahoma City", "Oklahoma City",
                "Dallas", "Dallas", "Dallas", "Houston", "New Orleans", "New Orleans", "New Orleans",
                "New Orleans", "Little Rock", "Little Rock", "Saint Louis", "Saint Louis", "Saint Louis",
                "Saint Louis", "Sault St. Marie", "Sault St. Marie", "Chicago", "Chicago", "Chicago",
                "Nashville", "Nashville", "Nashville", "Atlanta", "Atlanta", "Atlanta", "Atlanta", "Pittsburgh",
                "Pittsburgh", "Pittsburgh", "Pittsburgh", "Toronto", "Raleigh", "Raleigh", "Raleigh",
                "Charleston", "Washington DC", "Washington DC", "New York", "New York", "New York",
                "Montreal", "Montreal"
        };
        String[] endCities = {"Calgary", "Seattle", "Seattle", "Portland", "Portland", "Calgary",
                "Helena", "Salt Lake", "San Francisco", "San Francisco", "Salt Lake", "Salt Lake", "Los Angeles",
                "Los Angeles", "Las Vegas", "Phoenix", "El Paso", "Salt Lake", "Denver", "Denver",
                "Helena", "Calgary", "Denver", "Omaha", "Duluth", "Winnipeg", "Santa Fe", "Phoenix",
                "Omaha", "Kansas City", "Kansas City", "Oklahoma City", "El Paso", "Santa Fe", "El Paso",
                "Oklahoma City", "Houston", "Dallas", "Oklahoma City", "Sault St. Marie", "Duluth",
                "Omaha", "Omaha", "Chicago", "Toronto", "Sault St. Marie", "Kansas City", "Kansas City",
                "Chicago", "Oklahoma City", "Oklahoma City", "Saint Louis", "Saint Louis", "Little Rock",
                "Dallas", "Dallas", "Little Rock", "Houston", "Houston", "New Orleans", "Little Rock",
                "Miami", "Atlanta", "Atlanta", "Nashville", "Saint Louis", "Nashville", "Pittsburgh",
                "Chicago", "Chicago", "Montreal", "Toronto", "Toronto", "Pittsburgh", "Pittsburgh",
                "Atlanta", "Raleigh", "Pittsburgh", "Miami", "Charleston", "Raleigh", "Raleigh",
                "Raleigh", "Washington DC", "New York", "New York", "Montreal", "Charleston", "Washington DC",
                "Washington DC", "Miami", "New York", "New York", "Boston", "Boston", "Montreal", "Boston",
                "Boston"};
        int[] lengths = {3, 1, 1, 1, 1, 4, 6, 6, 6, 6, 5, 5, 3, 3, 2, 3, 6, 3, 3, 3, 3, 4, 4, 5, 6,
                4, 2, 5, 4, 4, 4, 4, 3, 3, 2, 3, 6, 4, 5, 6, 4, 2, 2, 3, 6, 3, 1, 1, 4, 2, 2, 2, 2,
                2, 2, 2, 2, 1, 1, 2, 3, 6, 4, 4, 3, 2, 2, 5, 2, 2, 5, 2, 4, 3, 3, 1, 1, 4, 5, 2, 2,
                2, 2, 2, 2, 2, 3, 2, 2, 2, 4, 2, 2, 2, 2, 3, 2, 2};
        String[] colors = {"Gray", "Gray", "Gray", "Gray", "Gray", "Gray", "Yellow", "Blue", "Green",
                "Pink", "Orange", "White", "Pink", "Yellow", "Gray", "Gray", "Black", "Orange", "Red",
                "Yellow", "Pink", "Gray", "Green", "Red", "Orange", "Blue", "Gray", "White", "Pink",
                "Orange", "Black", "Red", "Gray", "Gray", "Gray", "Blue", "Green", "Red", "Yellow",
                "Gray", "Black", "Gray", "Gray", "Red", "Pink", "Gray", "Gray", "Gray", "Blue", "Gray",
                "Gray", "Blue", "Green", "Gray", "Gray", "Gray", "Gray", "Gray", "Gray", "Gray", "Green",
                "Red", "Orange", "Yellow", "White", "Gray", "Gray", "Green", "White", "Green", "Black",
                "Gray", "White", "Orange", "Black", "Gray", "Black", "Yellow", "Blue", "Gray", "Gray",
                "Gray", "Gray", "Gray", "Green", "White", "Gray", "Gray", "Gray", "Gray", "Pink",
                "Orange", "Black", "Yellow", "Red", "Blue", "Gray", "Gray"};
        routeList = new ArrayList<>();
        int id = 0;
        for(int i = 0; i < startCities.length; i++){
            if(i != startCities.length-1){
                if(startCities[i].equals(startCities[i+1])&&endCities[i].equals(endCities[i+1]))
                {
                    routeList.add(new Route(Cities.getInstance().findCity(startCities[i]),
                            Cities.getInstance().findCity(endCities[i]),lengths[i],colors[i],id++,colors[i+1], id++));
                    i++;
                }
                else{
                    routeList.add(new Route(Cities.getInstance().findCity(startCities[i]),
                            Cities.getInstance().findCity(endCities[i]),lengths[i],colors[i],id++));
                }
            }
            else{
                routeList.add(new Route(Cities.getInstance().findCity(startCities[i]),
                        Cities.getInstance().findCity(endCities[i]),lengths[i],colors[i],id++));
            }
        }

    }

    /*public static Routes getInstance() {
        return instance;
    }*/
    public Route getRoute(int ID) {
        for (Route toRet : routeList) {
            if ((toRet.getID() == ID) || (toRet.getID() * -1 == ID)) {
                return toRet;
            }
        }
        return null;
    }
    public List<Route> getRouteList() {
        return routeList;
    }

}
