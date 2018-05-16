package clientModel;

import java.util.Observer;

import clientResult.GameResult;
import clientResult.Result;

/**
 * Created by zachgormley on 5/13/18.
 */

public class GameFacade
{
    TicketToRideProxy proxy;
    UserData userData = UserData.getUserData();
    Games gameList = Games.getGames();

    public GameResult createGame(int playerCount)
    {
        int gameNumber = gameList.getGameList().size() + 1;
        Game game = new Game(playerCount, 1, gameNumber);
        GameResult result =  proxy.createNewGame(game);
        if (result.isSuccess())
        {
            gameList.getGameList().put(result.getToReturn().getGameNumber(), result.getToReturn());
        }
        return result;
    }
    public Result joinGame(int gameNumber)
    {
        Game game = gameList.getGameList().get(gameNumber);
        Result result = proxy.addPlayerToGame(userData.getUsername(), game);
        if (result.isSuccess())
        {
            game.addPlayer();
            userData.setCurrentGame(game);
        }
        return result;
    }
    public void addObserver(Observer o)
    {
        gameList.addAnObserver(o);
    }
}

