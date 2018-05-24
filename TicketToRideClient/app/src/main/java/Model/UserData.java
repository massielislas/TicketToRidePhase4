package Model;

public class UserData
{
    static public UserData userData = new UserData();

    UserPass username;

    Game currentGame;

    Player currentPlayer;

    Host host;

    Port port;

    public static void setUserData(UserData userData) {
        UserData.userData = userData;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public static UserData getUserData() {
        return userData;
    }

    public UserPass getUsername() {
        return username;
    }

    public void setUsername(UserPass username) {
        this.username = username;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }


}
