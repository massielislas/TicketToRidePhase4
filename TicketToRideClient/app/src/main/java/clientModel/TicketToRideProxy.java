package clientModel;

import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;

public class TicketToRideProxy implements ITicketToRide {

    @Override
    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port) {

    }

    @Override
    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
    }

    @Override
    public Result addPlayerToGame(UserPass username, Game game) {

    }

    @Override
    public GameResult createNewGame(int numPlayers) {

    }

    @Override
    public GameStartResult startGame(Game game) {

    }
}
