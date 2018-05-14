package clientModel;

import clientModel.Command;
import clientModel.Game;
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
        Command command = new Command();
        return proxy.dostuff(command);
    }
}