package root.tickettorideclient.Views;

/**
 * Created by Massiel on 6/5/2018.
 */

public class PlayerFinalStats {

    String name;
    int longestRoutePoints;
    int claimedRoutesPoints;
    int reachedDestinationsPoints;
    int lostDestinations;
    int totalPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLongestRoutePoints() {
        return longestRoutePoints;
    }

    public void setLongestRoutePoints(int longestRoutePoints) {
        this.longestRoutePoints = longestRoutePoints;
    }

    public int getClaimedRoutesPoints() {
        return claimedRoutesPoints;
    }

    public void setClaimedRoutesPoints(int claimedRoutesPoints) {
        this.claimedRoutesPoints = claimedRoutesPoints;
    }

    public int getReachedDestinationsPoints() {
        return reachedDestinationsPoints;
    }

    public void setReachedDestinationsPoints(int reachedDestinationsPoints) {
        this.reachedDestinationsPoints = reachedDestinationsPoints;
    }

    public int getLostDestinations() {
        return lostDestinations;
    }

    public void setLostDestinations(int lostDestinations) {
        this.lostDestinations = lostDestinations;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

}
