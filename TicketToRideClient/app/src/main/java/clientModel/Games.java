package clientModel;

import java.util.ArrayList;
import java.util.HashMap;
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

    TreeMap<Integer, Game> gameList = new TreeMap<>();

    void addGame(Game game)
    {
        gameList.put(game.getGameNumber(), game);
        setChanged(); //set change has occurred
        notifyObservers(makeArray()); //notify observers we have a change and give them new gameList
        clearChanged(); //no longer have a change!
    }

    void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    TreeMap<Integer, Game> getGameList()
    {
        return gameList;
    }

    private ArrayList<GameListItem> makeArray()
    {
        ArrayList<GameListItem> array = new ArrayList<GameListItem>();
        for (Map.Entry<Integer, Game> entry : gameList.entrySet()) {
            GameListItem item = new GameListItem(entry.getKey().toString(), Integer.toString(entry.getValue().getCurrentPlayers()),
                    Integer.toString(entry.getValue().getPlayerCount()));
        }
        return array;
    }
}

