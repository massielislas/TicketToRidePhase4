package Model;

public class UserData
{
    static public UserData userData = new UserData();

    UserPass username;

    Game currentGame;

    Host host;

    Port port;

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
