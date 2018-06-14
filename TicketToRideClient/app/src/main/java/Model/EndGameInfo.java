package Model;

import java.util.List;

import Model.InGameModels.PlayerShallow;

public class EndGameInfo {
    private String winner;
    private List<PlayerShallow> playerInfo;
    private String playerWithLongestRoute;
    private int pointsFromLongestRoute;

    public EndGameInfo(String winner, List<PlayerShallow> playerInfo, String playerWithLongestRoute, int pointsFromLongestRoute) {
        this.winner = winner;
        this.playerInfo = playerInfo;
        this.playerWithLongestRoute = playerWithLongestRoute;
        this.pointsFromLongestRoute = pointsFromLongestRoute;
    }

    public String getWinner() {
        return winner;
    }

    public List<PlayerShallow> getPlayerInfo() {
        return playerInfo;
    }

    public String getPlayerWithLongestRoute() {
        return playerWithLongestRoute;
    }

    public int getPointsFromLongestRoute() {
        return pointsFromLongestRoute;
    }
}
