package clientModel;

import clientResult.GameResult;
import clientResult.Result;

/**
 * Created by zachgormley on 5/13/18.
 */

public class GameFacade
{
    TicketToRideProxy proxy;
    UserData userData = UserData.getUserData();

    public GameResult createGame(int playerCount)
    {
        return proxy.createNewGame(playerCount);
    }
    public Result joinGame(int gameID, int currentPlayers)
    {
        Game game = new Game(gameID, currentPlayers);
        Result result = proxy.addPlayerToGame(userData.getUsername(), game);
        if (result.isSuccess())
        {
            userData.setCurrentGame(game);
        }
        return result;
    }
}

