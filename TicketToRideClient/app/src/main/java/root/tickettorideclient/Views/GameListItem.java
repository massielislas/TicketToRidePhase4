package root.tickettorideclient.Views;

/**
 * Created by Massiel on 5/14/2018.
 */

public class GameListItem {

    public GameListItem (String gameId, String playersJoined, String maxPlayers) {
        this.gameId = gameId;
        this.playersJoined = playersJoined;
        this.maxPlayers = maxPlayers;
    }

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

    public String getMaxPlayers() { return maxPlayers; }

    public void setMaxPlayers(String maxPlayers) { this.maxPlayers = maxPlayers;}

    String gameId;
    String playersJoined;
    String maxPlayers;
}
