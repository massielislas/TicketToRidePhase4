package clientModel;

class UserData
{
    UserPass username;

    Game currentGame;

    UserData(UserPass username) {this.username = username;}

    void setGame(Game game){currentGame = game;}
}
