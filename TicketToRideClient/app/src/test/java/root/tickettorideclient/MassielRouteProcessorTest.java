package root.tickettorideclient;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Model.InGameModels.City;
import Model.InGameModels.Route;
import Model.RouteProcessor;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Massiel on 6/9/2018.
 */

public class MassielRouteProcessorTest {
    RouteProcessor routeProcessor = new RouteProcessor();
    List<Route> allRoutes = new ArrayList<>();


    City vancouver;
    City losAngeles;
    City stLouis;
    City dallas;
    City mexicoCity;
    City acapulco;
    City newYork;
    City connecticut;
    City charlotte;
    City cancun;

    Route vancouverMexico;
    Route losAngelesDallas;
    Route vancouverStLouis;
    Route stLouisDallas;
    Route dallasMexico;
    Route vancouverNY;
    Route dallasNY;
    Route NYConnecticut;
    Route connecticutCharlotte;
    Route dallasCharlotte;
    Route charlotteAcapulco;
    Route acapulcoMexico;
    Route mexicoCancun;

    @Before
    public void createRoutes(){
        vancouver = new City("Vancouver", 0, 0);
        losAngeles = new City("LosAngeles", 0, 0);
        stLouis = new City("St. Louis", 0, 0);
        dallas = new City("Dallas", 0, 0);
        mexicoCity = new City("Mexico City", 0, 0);
        acapulco = new City("Acapulco", 0, 0);
        newYork = new City("New York", 0, 0);
        connecticut = new City("Connecticut", 0, 0);
        charlotte = new City("Charlotte", 0, 0);
        cancun = new City("Cancun", 0, 0);

        vancouverMexico = new Route(vancouver, mexicoCity, 15, "", 0);
        allRoutes.add(vancouverMexico);
        losAngelesDallas = new Route(losAngeles, dallas, 6, "", 1);
        allRoutes.add(losAngelesDallas);
        vancouverStLouis = new Route(vancouver, stLouis, 8, "", 2);
        allRoutes.add(vancouverStLouis);
        stLouisDallas = new Route(stLouis, dallas, 6, "", 3);
        allRoutes.add(stLouisDallas);
        dallasMexico = new Route(dallas, mexicoCity, 10, "", 4);
        allRoutes.add(dallasMexico);
        vancouverNY = new Route(vancouver, newYork, 12, "", 5);
        allRoutes.add(vancouverNY);
        dallasNY = new Route(dallas, newYork, 10, "", 6);
        allRoutes.add(dallasNY);
        NYConnecticut = new Route(newYork, connecticut, 2, "", 7);
        allRoutes.add(NYConnecticut);
        connecticutCharlotte = new Route(connecticut, charlotte, 4, "", 8);
        allRoutes.add(connecticutCharlotte);
        dallasCharlotte = new Route(dallas, charlotte, 8, "", 9);
        allRoutes.add(dallasCharlotte);
        charlotteAcapulco = new Route(charlotte, acapulco, 13, "", 10);
        allRoutes.add(charlotteAcapulco);
        acapulcoMexico = new Route(acapulco, mexicoCity, 4, "", 11);
        allRoutes.add(acapulcoMexico);
        mexicoCancun = new Route(mexicoCity, cancun, 7, "", 12);
        allRoutes.add(mexicoCancun);
    }


    @Test
    public void regularFunctionality(){
        ArrayList<Route>routes = new ArrayList<>();
        routes.add(vancouverNY);

        routes.add(stLouisDallas);
        routes.add(dallasMexico);

        routes.add(connecticutCharlotte);
        routes.add(charlotteAcapulco);

        int longestRouteLength = RouteProcessor.LongestRoute(routes, 0);
        assertTrue(longestRouteLength == 16);
    }

    @Test
    public void sameLength(){
        ArrayList<Route>routes = new ArrayList<>();

        routes.add(losAngelesDallas);

        routes.add(NYConnecticut);
        routes.add(connecticutCharlotte);

        int longestRouteLength = RouteProcessor.LongestRoute(routes, 0);
        assertTrue(longestRouteLength == 6);
    }
}
