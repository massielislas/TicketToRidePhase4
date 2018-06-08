package Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.InGameModels.PlayerShallow;

public class EndGameData extends Observable {

    private String winner;
    private List<PlayerShallow> playerInfo;
    private String playerWithLongestRoute;
    private int pointsFromLongestRoute;

    public void setChange()
    {
        setChanged(); //set change has occurred
        notifyObservers(this); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    public void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public void removeAnObserver(Observer o)
    {
        removeAnObserver(o);
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<PlayerShallow> getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(List<PlayerShallow> playerInfo) {
        this.playerInfo = playerInfo;
    }

    public String getPlayerWithLongestRoute() {
        return playerWithLongestRoute;
    }

    public void setPlayerWithLongestRoute(String playerWithLongestRoute) {
        this.playerWithLongestRoute = playerWithLongestRoute;
    }

    public int getPointsFromLongestRoute() {
        return pointsFromLongestRoute;
    }

    public void setPointsFromLongestRoute(int pointsFromLongestRoute) {
        this.pointsFromLongestRoute = pointsFromLongestRoute;
    }
}
