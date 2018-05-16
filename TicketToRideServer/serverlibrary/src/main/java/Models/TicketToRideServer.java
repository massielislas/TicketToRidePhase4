package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import resultsClasses.Result;

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

    private TicketToRideServer() {
        activeGames = new ArrayList<>();
        notYetActiveGames = new ArrayList<>();
        userPasswordMap = new TreeMap<>();
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
        if (notYetActiveGames.contains(toCheck)) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean addUserPass(UserPass username, UserPass password)
    {
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

        for (Game find : notYetActiveGames) {
            if (game.equals(find)) {
                game = find;

                if (game.addPlayerToGame(user)) {
                    return new Result(true, "Successfully joined game");
                }
                else {
                    return new Result(false, "Game was full");
                }
            }
        }
        return new Result(false, "That game doesn't exist!");
    }

    public void addGameToQueue(Game toAdd) {
        notYetActiveGames.add(toAdd);
    }
}