package root.tickettorideclient.Views;

/**
 * Created by Massiel on 5/14/2018.
 */

public class GameListItem {

    String gameId;
    String playersJoined;
    String maxPlayers;

    public String getListNumber() {
        return listNumber;
    }

    public void setListNumber(String listNumber) {
        this.listNumber = listNumber;
    }

    String listNumber;

    public GameListItem(){

    }


    public GameListItem(String gameId, String playersJoined, String maxPlayers, String listNumber) {
        this.gameId = gameId;
        this.playersJoined = playersJoined;
        this.maxPlayers = maxPlayers;
        this.listNumber = listNumber;
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
}
