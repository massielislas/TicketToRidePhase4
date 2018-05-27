package Model.InGameModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.InGameModels.Route;

/**
 * Created by Topper on 5/25/2018.
 */

public class Routes
{
    List<Route> routeList;
    public Routes(){
        String[] startCities = {"Vancouver", "Vancouver", "Vancouver", "Seattle", "Seattle", "Seattle",
                "Seattle", "Portland", "Portland", "Portland", "San Fran", "San Fran", "San Fran",
                "San Fran", "Los Angeles", "Los Angeles", "Los Angeles", "Las Vegas", "Salt Lake",
                "Salt Lake", "Salt Lake", "Helena", "Helena", "Helena", "Helena", "Helena", "Denver",
                "Denver", "Denver", "Denver", "Denver", "Denver", "Phoenix", "Phoenix", "Santa Fe",
                "Santa Fe", "El Paso", "El Paso", "El Paso", "Winnipeg", "Winnipeg", "Duluth", "Duluth",
                "Duluth", "Duluth", "Duluth", "Omaha", "Omaha", "Omaha", "Kansas City", "Kansas City",
                "Kansas City", "Kansas City", "Oklahoma City", "Oklahoma City", "Oklahoma City",
                "Dallas", "Dallas", "Dallas", "Houston", "New Orleans", "New Orleans", "New Orleans",
                "New Orleans", "Little Rock", "Little Rock", "Saint Louis", "Saint Louis", "Saint Louis",
                "Saint Louis", "Sault St. Marie", "Sault St. Marie", "Chicago", "Chicago", "Chicago",
                "Nashville", "Nashville", "Nashville", "Atlanta", "Atlanta", "Atlanta", "Atlanta", "Pittsbourgh",
                "Pittsbourgh", "Pittsbourgh", "Pittsbourgh", "Toronto", "Raleigh", "Raleigh", "Raleigh",
                "Charleston", "Washington DC", "Washington DC", "New York", "New York", "New York",
                "Montreal", "Montreal"
        };
        String[] endCities = {"Calgary", "Seattle", "Seattle", "Portland", "Portland", "Calgary",
                "Helena", "Salt Lake", "San Fran", "San Fran", "Salt Lake", "Salt Lake", "Los Angeles",
                "Los Angeles", "Las Vegas", "Phoenix", "El Paso", "Salt Lake", "Denver", "Denver",
                "Helena", "Calgary", "Denver", "Omaha", "Duluth", "Winnipeg", "Santa Fe", "Phoenix",
                "Omaha", "Kansas City", "Kansas City", "Oklahoma City", "El Paso", "Santa Fe", "El Paso",
                "Oklahoma City", "Houston", "Dallas", "Oklahoma City", "Sault St. Marie", "Duluth",
                "Omaha", "Omaha", "Chicago", "Toronto", "Sault St. Marie", "Kansas City", "Kansas City",
                "Chicago", "Oklahoma City", "Oklahoma City", "Saint Louis", "Saint Louis", "Little Rock",
                "Dallas", "Dallas", "Little Rock", "Houston", "Houston", "New Orleans", "Little Rock",
                "Miami", "Atlanta", "Atlanta", "Nashville", "Saint Louis", "Nashville", "Pittsbourgh",
                "Chicago", "Chicago", "Montreal", "Toronto", "Toronto", "Pittsbourgh", "Pittsbourgh",
                "Atlanta", "Raleigh", "Pittsbourgh", "Miami", "Charleston", "Raleigh", "Raleigh",
                "Raleigh", "Washington DC", "New York", "New Yost", "Montreal", "Charleston", "Washington DC",
                "Washington DC", "Miami", "New York", "New York", "Boston", "Boston", "Montreal", "Boston",
                "Boston"};
        int[] lengths = {3, 1, 1, 1, 1, 4, 6, 6, 6, 6, 5, 5, 3, 3, 2, 3, 6, 3, 3, 3, 3, 4, 4, 5, 6,
                4, 2, 5, 4, 4, 4, 4, 3, 3, 2, 3, 6, 4, 5, 6, 4, 2, 2, 3, 6, 3, 1, 1, 4, 2, 2, 2, 2,
                2, 2, 2, 2, 1, 1, 2, 3, 6, 4, 4, 3, 2, 2, 5, 2, 2, 5, 2, 4, 3, 3, 1, 1, 4, 5, 2, 2,
                2, 2, 2, 2, 2, 3, 2, 2, 2, 4, 2, 2, 2, 2, 3, 2, 2};
        String[] colors = {"Grey", "Grey", "Grey", "Grey", "Grey", "Grey", "Yellow", "Blue", "Green",
                "Pink", "Orange", "White", "Pink", "Yellow", "Grey", "Grey", "Black", "Orange", "Red",
                "Yellow", "Pink", "Grey", "Green", "Red", "Orange", "Blue", "Grey", "White", "Pink",
                "Orange", "Black", "Red", "Grey", "Grey", "Grey", "Blue", "Green", "Red", "Yellow",
                "Grey", "Black", "Grey", "Grey", "Red", "Pink", "Grey", "Grey", "Grey", "Blue", "Grey",
                "Grey", "Blue", "Green", "Grey", "Grey", "Grey", "Grey", "Grey", "Grey", "Grey", "Green",
                "Red", "Orange", "Yellow", "White", "Grey", "Grey", "Green", "White", "Green", "Black",
                "Grey", "White", "Orange", "Black", "Grey", "Black", "Yellow", "Blue", "Grey", "Grey",
                "Grey", "Grey", "Grey", "Green", "White", "Grey", "Grey", "Grey", "Grey", "Pink",
                "Orange", "Black", "Yellow", "Red", "Blue", "Grey", "Grey"};
        routeList = new ArrayList<>();
        for(int i = 0; i < startCities.length; i++){
            routeList.add(new Route())
        }
    }
}
