package Results;

import Model.Command;

/**
 * Created by Topper on 5/16/2018.
 */

public class PollResult extends Result{
    public PollResult(boolean success,String message, Command[] commands){
        super.setSuccess(success);
        super.setMessage(message);
        this.commands = commands;
    }
    private Command[] commands;
    public Command[] getCommands() {
        return commands;
    }
}
