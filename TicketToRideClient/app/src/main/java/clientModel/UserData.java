package clientModel;

public class UserData
{
    UserPass username;

    Game currentGame;

    public UserData(UserPass username) {this.username = username;}

    public void setGame(Game game){currentGame = game;}
}
