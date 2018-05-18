package Model;

import java.util.ArrayList;

/**
 * Created by zachgormley on 5/13/18.
 */

public class ClientFacade
{
    public void executeCommands(ArrayList<Command> commands)
    {
        for(Command c: commands){
            c.Execute();
        }
    }

}