package Models;

import resultsClasses.GameResult;
import resultsClasses.GameStartResult;
import resultsClasses.LoginRegisterResult;
import resultsClasses.Result;

/**
 * Created by Topper on 5/15/2018.
 */

public class TicketToRideFacade implements ITicketToRide{
    @Override
    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
        if(TicketToRideServer.getInstance().verifyUserPass(username,password))
        {
            return new LoginRegisterResult(true,null);
        }
        else
        {
            return new LoginRegisterResult(false, "invalidInputException");
        }
    }

    @Override
    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port) {
        if(TicketToRideServer.getInstance().addUserPass(username,password))
        {
            return new LoginRegisterResult(true, null);
        }
        else
        {
            return new LoginRegisterResult(false, "usernameTakenException");
        }
    }

    @Override
    public GameResult createNewGame(Game game) {
        //if(TicketToRideServer.getInstance().)
        return null;
    }

    @Override
    public GameStartResult startGame(Game game) {
        return null;
    }

    @Override
    public Result addPlayerToGame(UserPass username, Game game) {
        return null;
    }
}
