package Model;

import java.util.ArrayList;
import java.util.Observer;
import Results.GameResult;
import Results.Result;
import root.tickettorideclient.Views.GameListItem;

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
        Game game = new Game(playerCount, 0, gameNumber);
        GameResult result =  proxy.createNewGame(game.getPlayerCount(), game.getCurrentPlayers(), game.getGameNumber(), game.getID());
        if (result.isSuccess())
        {
            UserData.getUserData().setCurrentGame(game);
           // gameList.getGameList().put(result.getToReturn().getGameNumber(), result.getToReturn());
        }
        return result;
    }
    public void addGame(Double playerCount, String ID) //called from ClientFacade
    {
        int gameNumber = gameList.getGameList().size()+1;
        Game game = new Game(playerCount.intValue(), 0, gameNumber, ID);
        gameList.addGame(game);
    }
    public void addPlayer(String ID) //called from ClientFacade
    {
        Game game = gameList.getGameList().get(ID);
        game.addPlayer();
    }
    public Result joinGame(String gameID)
    {
        System.out.println("Game: " + gameID);
        Game game = gameList.getGameList().get(gameID);
        if (game == null) System.out.println("uh oh!");
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

    public ArrayList<GameListItem> getGames () {
        return Games.games.makeArray();
    }
}

