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
        for(Route r: routeList) {
            List<Route> copyList = new ArrayList<>(routeList);
            copyList.remove(r);

            int currentLength = r.getLength() + LongestRouteRec(copyList,r.getCity2());

            if (currentLength > bestLengthSoFar) {
                bestLengthSoFar = currentLength;
            }
        }
        return bestLengthSoFar;
    }

    private static int LongestRouteRec(List<Route> routeList, City start){
        int bestLengthSoFar = 0;
        for(Route r:routeList){
            if(r.getCity1().equals(start)){
                List<Route> copyList = new ArrayList<>(routeList);
                copyList.remove(r);
                int currentLength = r.getLength() + LongestRouteRec(copyList, r.getCity2());
                if(currentLength > bestLengthSoFar){
                    bestLengthSoFar = currentLength;
                }
            }
            if(r.getCity2().equals(start)){
                List<Route> copyList = new ArrayList<>(routeList);
                copyList.remove(r);
                int currentLength = r.getLength() + LongestRouteRec(copyList, r.getCity1());
                if(currentLength > bestLengthSoFar){
                    bestLengthSoFar = currentLength;
                }
            }
        }
        return bestLengthSoFar;
    }

    public static boolean DestinationComplete(City start, City end, List<Route> claimedRoutes){
        for(Route r:claimedRoutes) {
            if (r.getCity1().equals(start) || r.getCity2().equals(start)) {
                if (r.getCity1().equals(end) || r.getCity2().equals(end)) {
                    return true;
                } else {
                    List<Route> copyList = new ArrayList<>(claimedRoutes);
                    copyList.remove(r);
                    if (DestinationComplete(r.getCity2(), end, copyList)) {
                        return true;
                    }
                    if (DestinationComplete(r.getCity1(), end, copyList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
