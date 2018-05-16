package clientModel;

import java.util.Observer;

class UserData
{
    static public UserData userData = new UserData();

    UserPass username;

    Game currentGame;

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
