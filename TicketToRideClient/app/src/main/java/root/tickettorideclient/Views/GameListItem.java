package root.tickettorideclient.Views;

/**
 * Created by Massiel on 5/14/2018.
 */

public class GameListItem {
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayersJoined() {
        return playersJoined;
    }

    public void setPlayersJoined(String playersJoined) {
        this.playersJoined = playersJoined;
    }

    String gameId;
    String playersJoined;
}
