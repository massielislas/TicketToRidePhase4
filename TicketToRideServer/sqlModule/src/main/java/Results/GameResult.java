package Results;


import Model.Game;

/**
 * Created by Master_Chief on 5/14/2018.
 */

public class GameResult extends Result {

    private Game toReturn;

    public Game getToReturn() {
        return toReturn;
    }

    public GameResult(Game game)
    {
        this.toReturn = game;
        super.setSuccess(true);
    }

    public GameResult(boolean success, String Error)
    {
        super(success, Error);
        toReturn = null;
    }
}
