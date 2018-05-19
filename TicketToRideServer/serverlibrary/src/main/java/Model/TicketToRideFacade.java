package Model;

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
        Game game = new Game(playerCount, currentPlayers, gameNumber, ID);
        UserPass uName = new UserPass(userPass);

        if (!Server.doesGameExist(game)) {
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
            }
            return Server.addPlayerToGame(game, uName);
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

    public GameStartResult startGame (Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
        return startGame((Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
    }

    public GameStartResult startGame(Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {

        Game game = new Game(playerCount, currentPlayers, gameNumber, ID);

        if (!Server.doesGameExist(game)) {
            return new GameStartResult(false, "That game doesn't exist!");
        }
        else {
            if (!Server.startGame(game)) {
                return new GameStartResult(false, "That game can't be started without" +
                        " enough players");
            }
            else {
                String[] instanceParamTypeNames = new String[0];
                Object[] instanceMethodArgs = new Object[0];
                String[] methodParamTypeNames = {"java.lang.String"};
                Object[] methodArguments = {ID};
                Command command = new Command("Model.GameFacade", "getInstance",
                        "startGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                        methodArguments);
                CommandManager.getInstance().addCommandAllUsers(command);
                return new GameStartResult(game);
            }
        }
    }
}
