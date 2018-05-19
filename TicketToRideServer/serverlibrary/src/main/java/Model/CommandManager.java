package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Results.PollResult;

/**
 * Created by Topper on 5/14/2018.
 */

public class CommandManager {

    private final static CommandManager instance = new CommandManager();
    private Map<UserPass,Command[]> commandMap;
    private CommandManager(){
        commandMap = new HashMap<>();
    }
    public static CommandManager getInstance() {
        return instance;
    }

    public void addCommand(UserPass username, Command command) {
        if(commandMap.containsKey(username)) {
            Command[] commands = commandMap.get(username);
            ArrayList<Command> commandList = new ArrayList<>(Arrays.asList(commands));
            commandList.add(command);
            commandMap.put(username, (Command[]) commandList.toArray(new Command[commandList.size()]));
        }
        else
        {
            Command[] newCommandArray = {command};
            commandMap.put(username,newCommandArray);
        }
    }

    public PollResult getNewCommands(String user, Double lastCommandDouble) {
        int lastCommand = lastCommandDouble.intValue();
        UserPass username = new UserPass(user);
        if(commandMap.containsKey(username)){
            Command[] allCommands = commandMap.get(username);
            Command[] toReturn = new Command[allCommands.length-lastCommand];
            System.arraycopy(allCommands,lastCommand,toReturn,0,allCommands.length-lastCommand);
            return new PollResult(true,"good",toReturn);
        }
        else {
            commandMap.put(username,new Command[0]);
            return new PollResult(true, "userAdded", new Command[0]);
        }
    }
    public void addCommandMultipleUsers(List<UserPass> userList, Command command){
        for(UserPass user:userList) {
            addCommand(user,command);
        }
    }

    public void addCommandAllUsers(Command command)
    {
        for(UserPass key: commandMap.keySet())
        {
            addCommand(key, command);
        }
    }
}
