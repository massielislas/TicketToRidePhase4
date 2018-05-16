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

    public TicketToRideFacade getInstance(){ return instance; }

    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port){
        return new LoginRegisterResult(false, "Stub");
    }

    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
        return new LoginRegisterResult(false, "Stub");
    }

    public Result addPlayerToGame(UserPass username, Game game) {
        return new Result();
    }

    public GameResult createNewGame(int numPlayers) {
        return new GameResult(false, "Stub");
    }

    public GameStartResult startGame(Game game) {
        return new GameStartResult();
    }
}
