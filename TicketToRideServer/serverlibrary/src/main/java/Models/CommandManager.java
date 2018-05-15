package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Topper on 5/14/2018.
 */

public class CommandManager {

    private final static CommandManager instance = new CommandManager();
    private Map<UserPass,Command[]> commandMap;
    public static CommandManager getInstance() {
        return instance;
    }
    public void addCommands(UserPass username, Command[] commands) {
        if(commandMap.containsKey(username)) {
            Command[] previousCommands = commandMap.get(username);
            ArrayList<Command> newCommandList = new ArrayList<>(Arrays.asList(commands));
            ArrayList<Command> oldCommandList = new ArrayList<>(Arrays.asList(previousCommands));
            oldCommandList.addAll(newCommandList);
            commandMap.put(username, (Command[]) newCommandList.toArray());
        }
        else
        {
            commandMap.put(username,commands);
        }
    }

    public void getNewCommands(UserPass username, int lastCommand) {
        if(commandMap.containsKey(username)){
            Command[] allCommands = commandMap.get(username);
            Command[] toReturn = new Command[allCommands.length-lastCommand];
            System.arraycopy(allCommands,lastCommand,toReturn,0,allCommands.length-lastCommand);
        }
    }


}
