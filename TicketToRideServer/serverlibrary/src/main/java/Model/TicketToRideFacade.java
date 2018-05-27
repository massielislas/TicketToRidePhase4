package Model;

import java.util.ArrayList;
import Model.InGameModels.Player;
import Results.GameResult;
import Results.GameStartResult;
import Results.LoginRegisterResult;
import Results.Result;

/**
 * Created by Lance on 5/15/2018.
 */

public class TicketToRideFacade implements ITicketToRide {

    private static final TicketToRideFacade instance = new TicketToRideFacade();
    private TicketToRideServer Server = TicketToRideServer.getInstance();

    public static TicketToRideFacade getInstance(){ return instance; }

    public LoginRegisterResult registerUser(String username, String password, String host, String port){
        //Check if the user already exists in the system
        UserPass uName = new UserPass(username);
        UserPass pWord = new UserPass(password);
        if (Server.doesUserExist(uName)) {
            return new LoginRegisterResult(false, "That User already exists!");
        }
        //If they don't, add them to the Map of players that currently exist
        else {
            Server.addUserPass(uName, pWord);
            CommandManager.getInstance().addAllCommandsNewUser(username);
            return new LoginRegisterResult(true);
        }
    }

    public LoginRegisterResult loginUser(String username, String password, String host, String port) {

        UserPass uName = new UserPass(username);
        UserPass pWord = new UserPass(password);
        //Check if the user exists, if they don't, throw an error
        if (!Server.doesUserExist(uName)) {
            return new LoginRegisterResult(false, "That User doesn't exist!");
        }
        //If The username or password don't match, throw an error
        if (!Server.verifyUserPass(uName, pWord)) {
            return new LoginRegisterResult(false, "UserName or Password are incorrect");
        }
        //Otherwise return true and log them into the App
        else {
            CommandManager.getInstance().addAllCommandsNewUser(username);
            return new LoginRegisterResult(true);
        }
    }

    public Result addPlayerToGame(String userPass, Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
        return addPlayerToGame(userPass, (Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
    }

    public Result addPlayerToGame(String userPass, Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {
        //Check if the game with the corresponding ID exists
        Game game = Server.getSpecificGame(ID);
        UserPass uName = new UserPass(userPass);

        if (game == null) {
            return new Result(false, "Invalid Game ID");
        }
        //Check if the user with the corresponding name exists
        if (!Server.doesUserExist(uName)) {
            return new Result(false, "Invalid User");
        }
        //Then attempt to add the player to the game, if the game is full, respond accordingly
        else {
            Result check = Server.addPlayerToGame(game, uName);
            if(check.isSuccess())
            {
                String[] instanceParamTypeNames = new String[0];
                Object[] instanceMethodArgs = new Object[0];
                String[] methodParamTypeNames = {"java.lang.String"};
                Object[] methodArguments = {ID};
                Command command = new Command("Model.GameFacade", "getInstance",
                        "addPlayer", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                        methodArguments);
                CommandManager.getInstance().addCommandAllUsers(command);

                if (game.getCurrentPlayers() == game.getPlayerCount()) {
                    startGame(game.getID());
                }
            }
            return check;
        }
    }

    //do we need this?!?!
    public GameResult createNewGame(Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
        return createNewGame((Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
    }

    public GameResult createNewGame(Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {

        Game newGame = new Game(playerCount, currentPlayers, gameNumber, ID);

        if (Server.doesGameExist(newGame)) {
            return new GameResult(false, "That game already exists!");
        }
        else {
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.Double", "java.lang.String"};
            Object[] methodArguments = {playerCount, ID};
            Command command = new Command("Model.GameFacade", "getInstance",
                    "addGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandAllUsers(command);
            Server.addGameToQueue(newGame);
            return new GameResult(newGame);
        }
    }

//    public GameStartResult startGame (Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
//        return startGame((Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
//    }

    public GameStartResult startGame(String ID) {

        Game game = Server.getSpecificGame(ID);

        if (game == null) {
            return new GameStartResult(false, "That game doesn't exist!");
        }
        else {
            if (!Server.startGame(game)) {
                return new GameStartResult(false, "That game can't be started without" +
                        " enough players");
            }
            else {
                initializeHands(game);
                return new GameStartResult(game);
            }
        }
    }

    private void initializeHands(Game game) {
        for (Player p : game.getPlayerList()) {
            SinglePlayerStartInfo initPack = game.dealStartingHand(p);
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"Model.SinglePlayerStartInfo"};
            Object[] methodArguments = {initPack};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "setStartInfo", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommand(p.getUserName(),command);
        }
    }

    public Result sendChat(String userName, String msg, String gameID) {
        Game toChat = Server.getSpecificGame(gameID);
        if (toChat != null) {
            toChat.addChat(msg, userName);
            //Command for Chat
            String totalMessage = userName + ": " + msg;
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String"};
            Object[] methodArguments = {totalMessage};
            Command command = new Command("Model.Chat", "getInstance",
                    "addChatMessage", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandMultipleUsers(toChat.getUserList(),command);
            //
            return new Result(true, "");
        }
        else {
            return new Result(false, "Invalid GameID in sendChat, ServerSide");
        }
    }

    public Result discardDestCards(String username, String gameID, ArrayList<Integer> cardIDs) {
        Game game = Server.getSpecificGame(gameID);
        if (game != null) {
            //Command for Chat
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String", "java.lang.Double"};
            Object[] methodArguments = {username, cardIDs.size()};
            Command command = new Command("Model.Chat", "getInstance",
                    "discardCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandMultipleUsers(game.getUserList(), command);
            //
            return new Result(true, "removed " + cardIDs.size() + " destination cards from " +
                    username + "'s hand");
        }
        //
        else {
            return new Result(false, "Something failed in discardDestCards in TicketToRideFacade");
        }
    }
}
