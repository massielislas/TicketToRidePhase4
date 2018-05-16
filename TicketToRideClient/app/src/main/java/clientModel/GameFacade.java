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
    Games gameList = Games.getGames();

    public GameResult createGame(Game game)
    {
<<<<<<< HEAD
        return proxy.createNewGame(game);
=======
        int gameNumber = gameList.getGameList().size() + 1;
        Game game = new Game(playerCount, 1, gameNumber);
        GameResult result =  proxy.createNewGame(game);
        if (result.isSuccess())
        {
            gameList.getGameList().put(game.getGameNumber(), game);
        }
        return result;
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668
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
}

