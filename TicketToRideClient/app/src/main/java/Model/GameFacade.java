package Model;

import java.util.Observer;

import Results.GameResult;
import Results.Result;

/**
 * Created by zachgormley on 5/13/18.
 */

public class GameFacade
{
    private static final GameFacade instance = new GameFacade();
    TicketToRideProxy proxy = new TicketToRideProxy();
    UserData userData = UserData.getUserData();
    Games gameList = Games.getGames();

    public static GameFacade getInstance() {return instance;}
    public GameResult createGame(int playerCount)
    {
        int gameNumber = gameList.getGameList().size() + 1;
        Game game = new Game(playerCount, 1, gameNumber);
        GameResult result =  proxy.createNewGame(game.getPlayerCount(), game.getCurrentPlayers(), game.getGameNumber(), game.getID());
        if (result.isSuccess())
        {
           // gameList.getGameList().put(result.getToReturn().getGameNumber(), result.getToReturn());
        }
        return result;
    }

    public void addGame(Game game) //called from ClientFacade
    {
        gameList.getGameList().put(game.getGameNumber(), game);
    }
    public void addPlayer(Game game) //called from ClientFacade
    {
        game.addPlayer();
    }
    public Result joinGame(int gameNumber)
    {
        Game game = gameList.getGameList().get(gameNumber);
        Result result = proxy.addPlayerToGame(userData.getUsername().getNameOrPassword(), game.getPlayerCount(), game.getCurrentPlayers(), game.getGameNumber(), game.getID());
        if (result.isSuccess())
        {
            //game.addPlayer();
            userData.setCurrentGame(game);
        }
        return result;
    }
    public void addObserver(Observer o)
    {
        gameList.addAnObserver(o);
    }
}

