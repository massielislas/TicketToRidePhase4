package clientModel;

/**
 * Created by zachgormley on 5/13/18.
 */

public class GameFacade
{
    TicketToRideProxy proxy;

    public GameResult createGame(int playerCount)
    {
        return proxy.createNewGame(playerCount);
    }
    public GameResult joinGame(int gameID)
    {
        Command command = new Command();
        Game game(gameID) = new Game();
        return proxy.addPlayerToGame(gameID);
    }
}

