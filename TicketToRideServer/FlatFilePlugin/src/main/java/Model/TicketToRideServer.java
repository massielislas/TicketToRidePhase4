
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.InGameModels.Player;
import Results.Result;

/**
 * Created by Topper on 5/15/2018.
 */

class TicketToRideServer {
    private static final TicketToRideServer instance = new TicketToRideServer();

    private List<Game> games;
    private Map<UserPass,UserPass> userPasswordMap;
    static TicketToRideServer getInstance(){
        return instance;
    }

    private TicketToRideServer() {
        games = new ArrayList<>();
        userPasswordMap = new HashMap<>();
    }

    void setGames(List<Game> games) {
        this.games = games;
    }

    public void setUserPasswordMap(Map<UserPass, UserPass> userPasswordMap) {
        this.userPasswordMap = userPasswordMap;
    }

    //Check whether the Username and Password supplied match and correspond to a user
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
    //
    boolean doesUserExist(UserPass userName) {

        if (userPasswordMap.keySet().contains(userName)) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean doesGameExist(Game toCheck) {
        if (games.contains(toCheck)) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean addUserPass(UserPass username, UserPass password) {
        //If the User already exists, then return false, otherwise put the pair into the map
        if(!userPasswordMap.containsKey(username)) {
            userPasswordMap.put(username, password);
            return true;
        }
        else {
            return false;
        }
    }

    Result addPlayerToGame(Game game, UserPass user) {

        for (Game find : games) {
            if (game.equals(find)) {
                game = find;
                if (game.addPlayerToGame(user)) {
                    return new Result(true, "Successfully joined game");
                }
                else {
                    return new Result(false, "Game is full");
                }
            }
        }
        return new Result(false, "That game doesn't exist!");
    }

    public void addGameToQueue(Game toAdd) {
        games.add(toAdd);
    }

    public boolean startGame(Game game) {
        if (game.getCurrentPlayers() == game.getPlayerCount()) {
            return true;
        }
        else {
            games.add(game);
            return false;
        }
    }

    public Game getSpecificActiveGame(String ID) {
        for (Game g : games) {
            if (g.getID().equals(ID)) {
                return g;
            }
        }
        return null;
    }
    public void activateGame(Game game){
        for (Game g : games) {
            if (g.equals(game)) {
                g.setGameActive(true);
            }
        }
    }

    public Game getInactiveGame(String ID) {
        for (Game g : games) {
            if (g.getID().equals(ID)) {
                return g;
            }
        }
        return null;
    }

    public Game findGameByUser(UserPass user) {
        for (Game g : games) {
            Player p = g.getPlayer(user);
            if (p != null) {
                return g;
            }
        }
        return null;
    }
}
