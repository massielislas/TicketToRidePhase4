package clientResult;


import clientModel.Game;

/**
 * Created by Master_Chief on 5/14/2018.
 */

public class GameResult extends Result {

    private Game toReturn;

    public GameResult(Game game)
    {
        this.toReturn = game;
    }

    public GameResult(boolean success, String Error)
    {
        super(success, Error);
        toReturn = null;
    }
}
