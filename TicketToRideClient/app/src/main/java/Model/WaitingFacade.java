package Model;

import java.util.Observer;

import Results.GameStartResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class WaitingFacade
{
    Game currentGame;
    TicketToRideProxy proxy = new TicketToRideProxy();
    Games gameList = Games.getGames();
    UserData userData = UserData.getUserData();

    public WaitingFacade()
    {
        currentGame = userData.getCurrentGame();
    }

    public GameStartResult startGame()
    {
        return proxy.startGame(currentGame);
    }
    public void addObserver(Observer o)
    {
        currentGame.addAnObserver(o);
    }
}