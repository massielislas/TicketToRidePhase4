package clientModel;

import java.util.Observer;

import clientResult.GameStartResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class WaitingFacade
{
    Game currentGame;
    TicketToRideProxy proxy;

    public GameStartResult startGame()
    {
        return proxy.startGame(currentGame);
    }

    public void addObserver(Observer o)
    {
        currentGame.addAnObserver(o);
    }
}