package clientModel;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;
import communication.ClientCommunicator;
import communication.Encoder;

public class TicketToRideProxy implements ITicketToRide {

    ClientCommunicator client = ClientCommunicator.getClient();
    UserData userData = UserData.getUserData();

    @Override
    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "UserPass", "Host", "Port"};
        Object[] methodArguments = {username, password, host, port};
        Command command = new Command("TicketToRideFacade", "getInstance",
                "loginUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host.data + ":" + port.data + "/command");

            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);

            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "UserPass", "Host", "Port"};
        Object[] methodArguments = {username, password, host, port};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "registerUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host.data + ":" + port.data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public Result addPlayerToGame(UserPass username, Game game) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "Game"};
        Object[] methodArguments = {username, game};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "addPlayerToGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public GameResult createNewGame(Game game) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"Game"};
        Object[] methodArguments = {game};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "createNewGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, GameResult.class);
            return (GameResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public GameStartResult startGame(Game game) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"Game"};
        Object[] methodArguments = {game};
        Command command = new Command("TicketToRideFacade", "getInstance",
                "startGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);

        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, GameStartResult.class);
            return (GameStartResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }
}
