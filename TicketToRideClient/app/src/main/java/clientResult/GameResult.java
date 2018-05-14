package clientResult;

import clientModel.Game;

public class GameResult implements Result
{
    boolean result = false;
    String message = "not set";

    Game currentGame;// = new Game();

    public Game getGame(){return currentGame;}

    public void setGame(Game game){currentGame = game;}

    public boolean isValid(){return result;}

    public void setResult(boolean result){this.result = result;}

    public String getMessage(){return message;}

    public void setMessage(String toSet){message = toSet;}
}
