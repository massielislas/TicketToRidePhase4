package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;

import Communication.Encoder;
import Model.InGameModels.Chat;
import Model.InGameModels.Player;
import Model.InGameModels.Route;
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
    Poller poller = Poller.getInstance();
    Chat chat = Chat.getInstance();

    public static GameFacade getInstance() {return instance;}
    public Result createGame(int playerCount)
    {
        int gameNumber = gameList.getGameList().size() + 1;
        Game game = new Game(playerCount, 0, gameNumber);
        Result result =  proxy.createNewGame(game.getPlayerCount(), game.getCurrentPlayers(), game.getGameNumber(), game.getID());
        //System.out.println("FIRST: " + game.getID());
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
        gameList.addPlayer(gameList.getGameList().get(ID));
        //Game game = gameList.getGameList().get(ID);
        //game.addPlayer();
    }
    public Result joinGame(String gameID)
    {
        //System.out.println("Second: " + gameID);
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

    public Result rejoinGame()
    {
        poller.setLastCommand(0);
        Result result = proxy.rejoinGame(userData.getUsername().getNameOrPassword());

        return result;
    }

    public void restoreClientGame(String restoreClientString)
    {
        RestoreClientInfo info = (RestoreClientInfo) Encoder.Decode(restoreClientString, RestoreClientInfo.class);
        String color = null;
        switch(info.getTurnNumber().intValue()) //set color
        {
            case 1: {
                color = "Blue";
                break;
            }
            case 2: {
                color = "Yellow";
                break;
            }
            case 3: {
                color = "Green";
                break;
            }
            case 4: {
                color = "Red";
                break;
            }
            case 5: {
                color = "Purple";
                break;
            }
        }
        Player currentPlayer = new Player(userData.getUsername(), info.getTurnNumber().intValue(), color);
        userData.setCurrentPlayer(currentPlayer);
        Game game = new Game(info.getPlayerCount().intValue(), info.getPlayerCount().intValue(), info.getID());
        userData.getUserData().setCurrentGame(game);
        UpdateInfo updateInfo = (UpdateInfo) Encoder.Decode(info.getJsonUpdateInfo(), UpdateInfo.class);
        updateGame(updateInfo);
        userData.getCurrentGame().setCities(info.getCities());
        setChat(info.getChat());
    }

    private void setChat(List<String> chatToAdd)
    {
        for (String message: chatToAdd)
        {
            chat.addChatMessage(message);
        }
    }

    private void updateGame(UpdateInfo update)
    {
        userData.getCurrentGame().setFaceUpTrainDeck(update.getCurrentFaceUpCards());
        userData.getCurrentGame().setDestDeckSize(update.getDestDeckSize());
        userData.getCurrentGame().setOtherPlayers(update.getPlayerInfo());
        userData.getCurrentGame().setTrainDeckSize(update.getTrainDeckSize());
        userData.getCurrentGame().setGameComplete(update.isGameComplete());

        userData.getCurrentPlayer().setTrainPiecesLeft(update.getPiecesLeft());
        userData.getCurrentPlayer().setCurrentScore(update.getPoints());
        if (update.getGameRoutes() != null) {
            checkForRouteColorChange(Arrays.asList(update.getGameRoutes()));
            userData.getCurrentGame().setRoutes(Arrays.asList(update.getGameRoutes()));
        }

        if (update.getPlayerRoutes() != null) {
            checkForRouteColorChange(Arrays.asList(update.getPlayerRoutes()));
            userData.getCurrentPlayer().setRoutesClaimed(Arrays.asList(update.getPlayerRoutes()));
        }
        if (update.getHand() != null) {
            userData.getCurrentPlayer().setTrainCards(update.getHand());
        }
        userData.getCurrentPlayer().setDestCards(update.getDestHand());
    }

    private void checkForRouteColorChange(List<Route> routes)
    {
        for (Route route: routes)
        {
            if (route.isDouble()) {
                if (route.isDoubleClaimed()) {
                    String playerName = route.getDoubleClaimant();
                    String playerColor = userData.getCurrentGame().getPlayerColorByUsername(playerName);
                    route.setDoubleColor(playerColor);
                }
            }
            if (route.isClaimed())
            {
                String playerName = route.getClaimant();
                String playerColor = userData.getCurrentGame().getPlayerColorByUsername(playerName);
                route.setColor(playerColor);
            }
        }
    }

    public void addObserver(Observer o)
    {
        gameList.addAnObserver(o);
    }

    public void deRegisterObserver(Observer o)
    {
        gameList.removeAnObserver(o);
    }

    public ArrayList<GameListItem> getGames () {
        return Games.games.makeArray();
    }
}

