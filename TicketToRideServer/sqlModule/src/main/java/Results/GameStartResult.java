package Results;


import Model.Game;

/**
 * Created by Master_Chief on 5/14/2018.
 */

public class GameStartResult extends Result {
    private Game toReturn;

    public GameStartResult(Game toReturn) {
        this.toReturn = toReturn;
    }

    public GameStartResult(boolean success, String message) {
        super(success, message);
        toReturn = null;
    }
}
