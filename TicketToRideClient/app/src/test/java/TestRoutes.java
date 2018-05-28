import org.junit.Test;

import java.util.ArrayList;

import Model.InGameModels.Cities;
import Model.InGameModels.City;
import Model.InGameModels.Route;
import Model.InGameModels.Routes;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public class TestRoutes {
    @Test
    public void testRoutes() {
        Routes newRouest = new Routes();
        ArrayList<Route> routes =  new ArrayList<>(newRouest.getRouteList());

     //   System.out.println("Find Sault St. Marie");

      //  Cities cities = Cities.getInstance();
     //   City city = cities.findCity("Sault St. Marie");
     //   System.out.println(city.getName());

/*
        for (int i = 0; i < routes.size(); ++i) {
            System.out.println("i: " + i);
            System.out.println("city 1: " + routes.get(i).getCity1().getName());
            System.out.println("city 2: " + routes.get(i).getCity2().getName());

        }
        */
    }

    @Test
    public void testRoutes2() {
        Routes newRouest = new Routes();
        ArrayList<Route> routes =  new ArrayList<>(newRouest.getRouteList());

        for (int i = 0; i < routes.size(); ++i) {
            System.out.println("i: " + i);
            System.out.println("city 1: " + routes.get(i).getCity1().getName());
            System.out.println("city 2: " + routes.get(i).getCity2().getName());

        }
    }
}
