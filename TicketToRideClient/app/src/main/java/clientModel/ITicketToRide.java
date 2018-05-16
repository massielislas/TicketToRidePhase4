package clientModel;

import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;
import clientModel.UserPass;
import clientModel.Host;
import clientModel.Port;
import clientModel.Game;

/**
 * Created by Topper on 5/14/2018.
 */

public interface ITicketToRide {
    LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port);
    LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port);
    Result addPlayerToGame(UserPass username, Game game);
    GameResult createNewGame(Game game);
    GameStartResult startGame(Game game);

}
