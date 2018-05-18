package Model;

import java.util.Observer;

import Results.GameStartResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class WaitingFacade
{
    private static final WaitingFacade instance = new WaitingFacade();

    public static WaitingFacade getInstance() {return instance;}

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
        return proxy.startGame(currentGame.getPlayerCount(), currentGame.getCurrentPlayers(), currentGame.getGameNumber(), currentGame.getID());
    }
    public void addObserver(Observer o)
    {
        currentGame.addAnObserver(o);
    }
}