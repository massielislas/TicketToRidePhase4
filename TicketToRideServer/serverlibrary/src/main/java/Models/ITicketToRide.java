package Models;

import resultsClasses.GameResult;
import resultsClasses.GameStartResult;
import resultsClasses.LoginRegisterResult;
import resultsClasses.Result;

/**
 * Created by Topper on 5/14/2018.
 */

public interface ITicketToRide {
    LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port);
    LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port);
    Result addPlayerToGame(UserPass username, Game game);
    GameResult createNewGame(int numPlayers);
    GameStartResult startGame(Game game);

}
