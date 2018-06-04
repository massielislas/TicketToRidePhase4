
package Model;


import java.util.ArrayList;

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
    Result addPlayerToGame(String userName, Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID);
    Result createNewGame(Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID);
    Result startGame(String ID);
    Result sendChat(String username, String message, String gameID);
    Result discardCards(String username, String gameID, Double cardOne, Double cardTwo);
}
