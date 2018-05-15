package Models;

import resultsClasses.GameResult;
import resultsClasses.LoginRegisterResult;
import resultsClasses.Result;

/**
 * Created by Master_Chief on 5/15/2018.
 */

public class TicketToRideFacade implements ITicketToRide {

    private static final TicketToRideFacade instance = new TicketToRideFacade();
    private Command comm;

    public TicketToRideFacade getInstance() { return instance; }

    public LoginRegisterResult register(UserPass name, UserPass password) {
        return new LoginRegisterResult();
    }

    public LoginRegisterResult login(UserPass name, UserPass password){
        return new LoginRegisterResult();
    }

    public GameResult createGame(int numPlayers){
        return new GameResult(false, "stub");
    }

    public GameResult joinGame() {
        return new GameResult(false, "stub");
    }

    public Result addPlayerToGame(UserPass username, Game game) {
        return new Result(false, "stub");
    }

    public Result startGame(Game game){
        return new Result(false, "stub");
    }

}
