package Models;

import java.util.Map;

/**
 * Created by Topper on 5/14/2018.
 */

public class CommandManager {
    private final static CommandManager instance = new CommandManager();
    private Map<UserPass,Command[]> commandMap;
    public static CommandManager getInstance()
    {
        return instance;
    }


}
