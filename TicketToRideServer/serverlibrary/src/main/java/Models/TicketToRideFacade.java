package Models;

import resultsClasses.GameResult;
import resultsClasses.GameStartResult;
import resultsClasses.LoginRegisterResult;
import resultsClasses.Result;

/**
 * Created by Lance on 5/15/2018.
 */

public class TicketToRideFacade implements ITicketToRide {

    private static final TicketToRideFacade instance = new TicketToRideFacade();
    private TicketToRideServer Server = TicketToRideServer.getInstance();

    public static TicketToRideFacade getInstance(){ return instance; }

    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port){
        //Check if the user already exists in the system
        if (Server.doesUserExist(username)) {
            return new LoginRegisterResult(false, "That User already exists!");
        }
        //If they don't, add them to the Map of players that currently exist
        else {
            Server.addUserPass(username, password);
            return new LoginRegisterResult(true);
        }
    }

    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
        //Check if the user exists, if they don't, throw an error
        if (!Server.doesUserExist(username)) {
            return new LoginRegisterResult(false, "That User doesn't exist!");
        }
        //If The username or password don't match, throw an error
        if (!Server.verifyUserPass(username, password)) {
            return new LoginRegisterResult(false, "UserName or Password are incorrect");
        }
        //Otherwise return true and log them into the App
        else {
            return new LoginRegisterResult(true);
        }
    }

    public Result addPlayerToGame(UserPass username, Game game) {
        //Check if the game with the corresponding ID exists
        if (!Server.doesGameExist(game)) {
            return new Result(false, "Invalid Game ID");
        }
        //Check if the user with the corresponding name exists
        if (!Server.doesUserExist(username)) {
            return new Result(false, "Invalid User");
        }
        //Then attempt to add the player to the game, if the game is full, respond accordingly
        else {
            return Server.addPlayerToGame(game, username);
        }
    }

    public GameResult createNewGame(Game newGame) {
        return new GameResult(false, "Stub");
    }

    public GameStartResult startGame(Game game) {
        return new GameStartResult();
    }
}
