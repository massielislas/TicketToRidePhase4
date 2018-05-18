package Model;

import Results.GameResult;
import Results.GameStartResult;
import Results.LoginRegisterResult;
import Results.Result;

/**
 * Created by Topper on 5/14/2018.
 */

public interface ITicketToRide {
    LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port);
    LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port);
    Result addPlayerToGame(UserPass username, Game game);
    GameResult createNewGame(Game newGame);
    GameStartResult startGame(Game game);

}

