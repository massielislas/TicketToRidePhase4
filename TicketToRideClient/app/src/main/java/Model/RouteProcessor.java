package Model;

import java.util.ArrayList;
import java.util.List;

import Model.InGameModels.City;
import Model.InGameModels.Route;

/**
 * Created by Topper on 6/8/2018.
 */

public class RouteProcessor {
    public static int LongestRoute(List<Route> routeList){
        int bestLengthSoFar = 0;
        Route r = routeList.get(0);
        int currentLength = r.getLength();
        List<Route> copyList = new ArrayList<>(routeList);
        copyList.remove(r);
        for(Route r2:copyList){
            if(r2.getCity1().equals(r.getCity1())||r2.getCity2().equals(r.getCity2())
                    ||r2.getCity2().equals(r.getCity1())||r2.getCity2().equals(r.getCity2())){
                currentLength = currentLength + LongestRoute(copyList);
            }
        }
        if(currentLength > bestLengthSoFar){
            bestLengthSoFar = currentLength;
        }
        return bestLengthSoFar;
    }

    public static boolean DestinationComplete(City start, City end, List<Route> claimedRoutes){
        List<Route> copyList = new ArrayList<>(claimedRoutes);
        List<Route> possibleRoutes = new ArrayList<>();
        for(Route r2:copyList) {
            if (r2.getCity1().equals(start) || r2.getCity2().equals(start)) {
                if (r2.getCity1().equals(end) || r2.getCity2().equals(end)) {
                    return true;
                } else {
                    copyList.remove(r2);
                    if (DestinationComplete(r2.getCity2(), end, copyList)) {
                        return true;
                    }
                    if (DestinationComplete(r2.getCity1(), end, copyList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
