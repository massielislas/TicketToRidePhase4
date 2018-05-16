package Models;

import java.util.List;
import java.util.Map;

/**
 * Created by Topper on 5/15/2018.
 */

class TicketToRideServer {
    private static final TicketToRideServer instance = new TicketToRideServer();

    private List<Game> activeGames;
    private List<Game> notYetActiveGames;
    private Map<UserPass,UserPass> userPasswordMap;

    static TicketToRideServer getInstance(){
        return instance;
    }

    private TicketToRideServer() {}

    boolean verifyUserPass(UserPass username, UserPass password){
        if(userPasswordMap.containsKey(username)) {
            if(userPasswordMap.get(username).equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    boolean addUserPass(UserPass username, UserPass password)
    {
        if(!userPasswordMap.containsKey(username))
        {
            userPasswordMap.put(username, password);
            return true;
        }
        else
        {
            return false;
        }
    }
}