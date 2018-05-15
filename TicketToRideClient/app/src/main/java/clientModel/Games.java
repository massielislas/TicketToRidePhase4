package clientModel;

import java.util.Observable;
import java.util.TreeMap;

class Games extends Observable
{
    TreeMap<Integer, Game> gameList = new TreeMap<>();

    void addGame(Game game)
    {
        gameList.put(game.getGameNumber(), game);
        setChanged(); //set change has occurred
        notifyObservers(gameList); //notify observers we have a change and give them new gameList
        clearChanged(); //no longer have a change!
    }

    TreeMap<Integer, Game> getGameList()
    {
        return gameList;
    }
}

