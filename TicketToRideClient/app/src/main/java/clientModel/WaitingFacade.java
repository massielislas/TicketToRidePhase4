package clientModel;

<<<<<<< HEAD
=======
import java.util.Observer;

>>>>>>> integration
import clientResult.GameStartResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class WaitingFacade
{
    Game currentGame;
    TicketToRideProxy proxy = new TicketToRideProxy();
    Games gameList = Games.getGames();

    public WaitingFacade(int gameID)
    {
        currentGame = gameList.getGameList().get(gameID);
    }
    public GameStartResult startGame()
    {
        return proxy.startGame(currentGame);
    }

    public void addObserver(Observer o)
    {
        currentGame.addAnObserver(o);
    }
}