package Model;

/**
 * Created by Topper on 5/16/2018.
 */

public class CommandExecuter {
    private static final CommandExecuter instance = new CommandExecuter();
    private CommandExecuter(){};
    public static CommandExecuter getInstance(){
        return instance;
    }
    public void ExecuteCommands(Command[] commandsToExecute)
    {
        for(Command c:commandsToExecute)
        {
            c.Execute();
        }
    }
}
