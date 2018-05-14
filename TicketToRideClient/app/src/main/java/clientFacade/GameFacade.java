package clientFacade;

import clientModel.Command;
import clientModel.Game;
import clientResult.GameResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class GameFacade
{
    TicketToRideProxy proxy;

    public GameResult createGame()
    {
        Command command = new Command();
        return proxy.dostuff(command);
    }
    public GameResult joinGame(Game game)
    {
        Command command = new Command();
        return proxy.dostuff(command);
    }
}

