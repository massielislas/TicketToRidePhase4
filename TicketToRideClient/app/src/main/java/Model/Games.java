package Model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import root.tickettorideclient.Views.GameListItem;

class Games extends Observable
{
    static public Games games = new Games();

    public static Games getGames() {
        return games;
    }

    TreeMap<String, Game> gameList = new TreeMap<>();

    void addGame(Game game)
    {
        gameList.put(game.getID(), game);
        setChanged(); //set change has occurred
        notifyObservers(makeArray()); //notify observers we have a change and give them new gameList
        clearChanged(); //no longer have a change!
    }

    void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    TreeMap<String, Game> getGameList()
    {
        return gameList;
    }

    ArrayList<GameListItem> makeArray()
    {
        ArrayList<GameListItem> array = new ArrayList<GameListItem>();
        for (Map.Entry<String, Game> entry : gameList.entrySet()) {
            GameListItem item = new GameListItem(Integer.toString(entry.getValue().getGameNumber()), Integer.toString(entry.getValue().getCurrentPlayers()), Integer.toString(entry.getValue().getPlayerCount()),
                    entry.getValue().getGameNumber() + "");
            array.add(item);
           // System.out.println("Key: " + entry.getValue().getID() + "\n : " +  )
        }

        return array;
    }

    /*public ArrayList<GameListItem> getGameItems () {

        //TODO: REMOVE ME
        //for TESTING purposes only
        if (gameList.size() == 0) {
            Game game = new Game(5,3,1,"testGame");
            games.gameList.put("testGame", game);
        }
        return makeArray();
    }*/

    //TESTING
    //public Games(){
    //   gameList = new TreeMap<>();
    //    Game game = new Game(5,3,1,"testGame");
    //    games.addGame(game);
   // }
}

