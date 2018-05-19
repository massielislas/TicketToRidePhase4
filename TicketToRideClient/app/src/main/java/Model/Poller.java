package Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import Communication.ClientCommunicator;
import Communication.Encoder;
import Results.LoginRegisterResult;
import Results.PollResult;

/**
 * Created by Topper on 5/16/2018.
 */

public class Poller {
    private static final Poller instance = new Poller();
    private Timer mTimer;
    private boolean running;
    UserPass username;
    Port port;
    Host host;
    Integer lastCommand;

    private Poller(){}
    public static Poller getInstance()
    {
        return instance;
    }
    public void run()
    {
        username = UserData.getUserData().getUsername();
        port = UserData.getUserData().getPort();
        host = UserData.getUserData().getHost();
        lastCommand = 0;
        running = true;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Poll();
            }
        },0, 3000);
    }
    private void Poll()
    {
        if(running) {
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String","java.lang.Double"};
            Object[] methodArguments = {username.getNameOrPassword(),lastCommand};

            Command command = new Command("Model.CommandManager", "getInstance",
                    "getNewCommands", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            String jsonStr = Encoder.Encode(command);
            try
            {
                URL url = new URL("http://" + host.data + ":" + port.data + "/command");
                Object[] objects = new Object[3];
                objects[0] = url;
                objects[1] = jsonStr;
                objects[2] = "";
                String json = ClientCommunicator.getClient().post(objects);
                if (json == null) return;
                PollResult result =(PollResult) Encoder.Decode(json, PollResult.class);
                ClientFacade.getInstance().executeCommands(result.getCommands());
            }
            catch (MalformedURLException exception)
            {
                System.out.println("Invalid URL!");
            }
            // ClientCommunicator.SINGLETON().testSend();
        }
    }
    public void stop()
    {
        System.out.println("DONE!");
        running=false;
        mTimer.cancel();
        mTimer.purge();
    }
}
