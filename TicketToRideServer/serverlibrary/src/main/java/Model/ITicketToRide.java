package Model;

import Results.GameResult;
import Results.GameStartResult;
import Results.LoginRegisterResult;
import Results.Result;

/**
 * Created by Topper on 5/14/2018.
 */

public interface ITicketToRide {
    LoginRegisterResult registerUser(String username, String password, String host, String port);
    LoginRegisterResult loginUser(String username, String password, String host, String port);
    Result addPlayerToGame(String userName, int playerCount, int currentPlayers, int gameNumber, String ID);
    GameResult createNewGame(int playerCount, int currentPlayers, int gameNumber, String ID);
    GameStartResult startGame(int playerCount, int currentPlayers, int gameNumber, String ID);

}

