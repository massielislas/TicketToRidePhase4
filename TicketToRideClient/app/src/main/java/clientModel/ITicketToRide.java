
package clientModel;


import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;

/**
 * Created by Topper on 5/14/2018.
 */

public interface ITicketToRide {
    LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port);
    LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port);
    Result addPlayerToGame(UserPass username, Game game);
<<<<<<< HEAD
    GameResult createNewGame(Game newGame);
=======
    GameResult createNewGame(Game game);
>>>>>>> integration
    GameStartResult startGame(Game game);
}

