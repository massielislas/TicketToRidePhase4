import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Model.InGameModels.City;
import Model.InGameModels.Route;
import Model.RouteProcessor;

import static org.junit.Assert.assertEquals;

/**
 * Created by madeleineaydelotte on 6/11/18.
 */

public class TestRouteProcessor {

    RouteProcessor processor;

    List<Route> routes1;
    City start1a;
    City start1b;
    City end1;

    List<Route> routes2;
    City start2a;
    City start2b;
    City end2;

    List<Route> routes3;
    City start3a;
    City start3b;
    City end3;

    List<Route> routes4;
    City start4a;
    City start4b;
    City end4;

    List<Route> routes5;
    City start5a;
    City start5b;
    City end5;

    List<Route> routes6;
    City start6a;
    City start6b;
    City end6;

    List<Route> routes7;
    City start7;
    City end7;

    @Before
    public void setUp() {
        processor = new RouteProcessor();

        //Single route
        start1a = new City("Oz", 1.0, 2.0);
        start1b = new City("City of Gold", 5.0, 6.0);
        end1 = new City("Atlantis", 3.0, 4.0);
        Route route1 = new Route(start1a, end1, 2, "Green", 1); //Oz to Atlantis
        routes1 = new ArrayList<>();
        routes1.add(route1);

        //Y route pattern
        City city2 = new City("Gotham", 7.0, 8.0);
        start2a = start1b; //Gold
        start2b = new City("Metropolis", 9.0, 10.0);
        end2 = start1a; //Oz
        Route route2a = new Route(city2, end1, 6, "Red", 2); //Gotham to Atlantis
        Route route2b = new Route(start2a, end1, 7, "Purple", 3); //Gold to Atlantis
        Route route2c = route1; //Oz to Atlantis
        routes2 = new ArrayList<>();
        routes2.add(route2a);
        routes2.add(route2b);
        routes2.add(route2c);

        //Square route pattern
        //Shangri-La to avalon, avalon to metropolis, metropolis to smallville, smallville to Shangri-La
        City city3a = new City("Smallville", 11.0, 12.0);
        City city3b = new City("Shangri-La", 13.0, 14.0);
        City city3c = new City("Avalon", 15.0, 16.0);
        start3a = city3a; //Smallville
        start3b = city2; //Gotham
        end3 = city3c; //Avalon
        Route route3a = new Route(city3b, city3c, 4, "Orange", 4); //Shangri-La to Avalon
        Route route3b = new Route(start2b, city3c, 9, "Pink", 5); //Metropolis to Avalon
        Route route3c = new Route(start2b, city3a, 4, "Yellow", 6); //Metropolis to Smallville
        Route route3d = new Route(city3a, city3b, 3, "Blue", 7); //Smallville to Shangri-La
        routes3 = new ArrayList<>();
        routes3.add(route3a);
        routes3.add(route3b);
        routes3.add(route3c);
        routes3.add(route3d);

        //2 single routes, not connected
        start4a = start1a; // Oz
        start4b = city3a; // Smallville
        end4 = end1; // Atlantis
        routes4 = new ArrayList<>();
        routes4.add(route1); //Oz to Atlantis
        routes4.add(route3d); //Smallville to Gold


        //A Y pattern and Square pattern, disconnected
        //Y: Oz, Gold, Atlantis, Gotham -> routes2
        //S: Shangri-La, Avalon, Metropolis, Smallville -> routes3
        start5a = city2; //Gotham
        start5b = city3a; //Smallville
        end5 = start1a; //Oz
        routes5 = new ArrayList<>();
        for (int i = 0; i < routes2.size(); ++i) {
            routes5.add(routes2.get(i));
        }
        for (int i = 0; i < routes3.size(); ++i) {
            routes5.add(routes3.get(i));
        }


        //A Y pattern connected to a Square pattern (by a single route)
        //Y: Oz, Gold, Atlantis, Gotham -> routes2
        //S: Shangri-La, Avalon, Metropolis, Smallville -> routes3
        //+ Oz to Avalon
        start6a = city2; //Gotham
        start6b = new City("Hogsmeade", 17.0, 18.0);
        end6 = city3a; //Smallville
        Route route6 = new Route(start4a, city3c, 7, "Black", 8); //Oz to Avalon
        routes6 = new ArrayList<>();
        for (int i = 0; i < routes5.size(); ++i) {
            routes6.add(routes5.get(i));
        }
        routes6.add(route6);

        //Empty route list
        start7 = start6a; //Gotham
        end7 = end6; //Smallville
        routes7 = new ArrayList<>();
    }



    //TESTING DESTINATION COMPLETE

    @Test
    public void destinationComplete1A() throws Exception {
        //test: single route fulfills destination
        boolean result = processor.DestinationComplete(start1a, end1, routes1);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete1B() throws Exception {
        //test: single route, doesn't fulfill destination
        boolean result = processor.DestinationComplete(start1b, end1, routes1);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete2A() throws Exception {
        //test: Y pattern of routes (3 routes with same end city), fulfills destination
        boolean result = processor.DestinationComplete(start2a, end2, routes2);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete2B() throws Exception {
        //test: Y pattern of routes (3 routes with same end city), doesn't fulfill destination
        boolean result = processor.DestinationComplete(start2b, end2, routes2);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete3A() throws Exception {
        //test: Square pattern of routes, fulfills destination
        boolean result = processor.DestinationComplete(start3a, end3, routes3);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete3B() throws Exception {
        //test: Square pattern of routes, doesn't fulfill destination
        boolean result = processor.DestinationComplete(start3b, end3, routes3);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete4A() throws Exception {
        //test: 2 single routes, not connected, fulfills destination
        boolean result = processor.DestinationComplete(start4a, end4, routes4);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete4B() throws Exception {
        //test: 2 single routes, not connected, doesn't fulfill destination
        boolean result = processor.DestinationComplete(start4b, end4, routes4);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete5A() throws Exception {
        //test: A Y pattern and Square pattern, disconnected, fulfills destination
        boolean result = processor.DestinationComplete(start5a, end5, routes5);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete5B() throws Exception {
        //test: A Y pattern and Square pattern, disconnected, fulfills destination
        boolean result = processor.DestinationComplete(start5b, end5, routes5);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete6A() throws Exception {
        //test: A Y pattern connected to a Square pattern (by a single route), fulfills destination
        boolean result = processor.DestinationComplete(start6a, end6, routes6);
        assertEquals(result, true);
    }

    @Test
    public void destinationComplete6B() throws Exception {
        //test: A Y pattern connected to a Square pattern (by a single route), doesn't fulfill destination
        boolean result = processor.DestinationComplete(start6b, end6, routes6);
        assertEquals(result, false);
    }

    @Test
    public void destinationComplete7() throws Exception {
        //test: empty route list
        boolean result = processor.DestinationComplete(start7, end7, routes7);
        assertEquals(result, false);
    }


    //TESTING LONGEST ROUTE

    @Test
    public void longestRoute1() throws Exception {
        //test: single route
        int result = processor.LongestRoute(routes1);
        assertEquals(result, 2);
    }

    @Test
    public void longestRoute2() throws Exception {
        //test: Y pattern of routes (3 routes with same end city)
        int result = processor.LongestRoute(routes2);
        assertEquals(result, 13);
    }

    @Test
    public void longestRoute3() throws Exception {
        //test: Square pattern of routes
        int result = processor.LongestRoute(routes3);
        assertEquals(result, 20);
    }

    @Test
    public void longestRoute4() throws Exception {
        //test: 2 single routes, not connected
        int result = processor.LongestRoute(routes4);
        assertEquals(result, 3);
    }

    @Test
    public void longestRoute5() throws Exception {
        //test:  A Y pattern and Square pattern, disconnected
        int result = processor.LongestRoute(routes5);
        assertEquals(result, 20);
    }

    @Test
    public void longestRoute6() throws Exception {
        //test:  A Y pattern connected to a Square pattern (by a single route)
        int result = processor.LongestRoute(routes6);
        assertEquals(result, 36);
    }

    @Test
    public void longestRoute7() throws Exception {
        //test: empty route list
        int result = processor.LongestRoute(routes7);
        assertEquals(result, 0);
    }

}
